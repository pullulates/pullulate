package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.common.constants.Constant;
import top.pullulate.common.enums.Sex;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.entity.PulUserDept;
import top.pullulate.system.entity.PulUserRole;
import top.pullulate.system.mapper.PulUserDeptMapper;
import top.pullulate.system.mapper.PulUserMapper;
import top.pullulate.system.mapper.PulUserRoleMapper;
import top.pullulate.system.service.IPulUserService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulUserViewVo;
import top.pullulate.web.data.vo.system.PulUserVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   用户服务接口实现类
 * @Author: pullulates
 * @Date: 2020/6/12 0012 19:18
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulUserServiceImpl extends ServiceImpl<PulUserMapper, PulUser> implements IPulUserService {

    private final TokenUtils tokenUtils;

    private final PulUserRoleMapper userRoleMapper;

    private final PulUserDeptMapper userDeptMapper;

    /**
     * 根据用户主键查询用户信息
     *
     * @param userId    用户主键
     * @return  用户信息
     */
    @Override
    public PulUser getUserInfoByUserId(String userId) {
        if (StrUtil.isBlank(userId)) {
            log.warn("获取用户信息服务，用户标识为空");
            return null;
        }
        return getById(userId);
    }

    /**
     * 根据手机号码查询用户信息
     *
     * @param phone    手机号码
     * @return  用户信息
     */
    @Override
    public PulUser getUserInfoByPhone(String phone) {
        if (StrUtil.isBlank(phone)) {
            log.warn("获取用户信息服务，手机号码为空");
            return null;
        }
        PulUser user = getOne(Wrappers.<PulUser>query().lambda().eq(PulUser::getPhone, phone));
        return user;
    }

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName    用户名称
     * @return  用户信息
     */
    @Override
    public PulUser getUserInfoByUserName(String userName) {
        if (StrUtil.isBlank(userName)) {
            log.warn("获取用户信息服务，用户名称为空");
            return null;
        }
        PulUser user = getOne(Wrappers.<PulUser>query().lambda().eq(PulUser::getUserName, userName));
        return user;
    }

    /**
     * 获取系统用户分页数据
     *
     * @param pulUserVo 查询参数
     * @param page  分页参数
     * @return  分页数据
     */
    @Override
    public IPage<List<PulUserViewVo>> getUserPage(PulUserVo pulUserVo, Page page) {
        return baseMapper.selectUserPage(pulUserVo, page);
    }

    /**
     * 保存用户信息
     *
     * @param userVo    用户信息
     * @return
     */
    @Override
    public P saveUser(PulUserVo userVo) {
        PulUser user = BeanUtil.toBean(userVo, PulUser.class);
        boolean valid = IdcardUtil.isValidCard(user.getIdcard());
        if (!valid) {
            return P.error("非法的证件号码！");
        }
        int count = count(Wrappers.<PulUser>lambdaQuery()
                .eq(PulUser::getUserName, user.getUserName())
                .or()
                .eq(PulUser::getIdcard, user.getIdcard())
                .or()
                .eq(PulUser::getPhone, user.getPhone()));
        if (count > 0) {
            return P.error("用户已存在，请检查用户名称、证件号码或手机号码是否重复！");
        }
        LocalDate birth = DateUtil.toLocalDateTime(IdcardUtil.getBirthDate(user.getIdcard())).toLocalDate();
        String sex = Sex.getSexFromIdcard(user.getIdcard());
        user.setBirth(birth);
        user.setSex(sex);
        user.setPassword(new BCryptPasswordEncoder().encode(Constant.DEFAULT_PASSWORD));
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy(tokenUtils.getUserName());
        return P.p(save(user));
    }

    /**
     * 修改用户信息
     *
     * @param userVo    用户信息
     * @return
     */
    @Override
    public P updateUser(PulUserVo userVo) {
        PulUser user = BeanUtil.toBean(userVo, PulUser.class);
        boolean valid = IdcardUtil.isValidCard(user.getIdcard());
        if (!valid) {
            return P.error("非法的证件号码！");
        }
        int count = count(Wrappers.<PulUser>lambdaQuery()
                .ne(PulUser::getUserId, user.getUserId())
                .and(wrapper -> wrapper
                        .eq(PulUser::getUserName, user.getUserName())
                        .or()
                        .eq(PulUser::getIdcard, user.getIdcard())
                        .or()
                        .eq(PulUser::getPhone, user.getPhone()))
                );
        if (count > 0) {
            return P.error("用户已存在，请检查用户名称、证件号码或手机号码是否重复！");
        }
        LocalDate birth = DateUtil.toLocalDateTime(IdcardUtil.getBirthDate(user.getIdcard())).toLocalDate();
        String sex = Sex.getSexFromIdcard(user.getIdcard());
        user.setBirth(birth);
        user.setSex(sex);
        user.setUpdateAt(LocalDateTime.now());
        user.setUpdateBy(tokenUtils.getUserName());
        return P.p(updateById(user));
    }

    /**
     * 修改用户状态
     *
     * @param userVo    用户信息
     * @return
     */
    @Override
    public P updateUserStatus(PulUserVo userVo) {
        PulUser user = BeanUtil.toBean(userVo, PulUser.class);
        boolean result = update(user, Wrappers.<PulUser>lambdaUpdate().eq(PulUser::getUserId, user.getUserId()));
        return result ? P.success() : P.error();
    }

    /**
     * 删除用户
     *
     * @param userId    用户主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P deleteUser(String userId) {
        userDeptMapper.delete(Wrappers.<PulUserDept>lambdaQuery().eq(PulUserDept::getUserId, userId));
        userRoleMapper.delete(Wrappers.<PulUserRole>lambdaQuery().eq(PulUserRole::getUserId, userId));
        removeById(userId);
        return P.success();
    }
}
