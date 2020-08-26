package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
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
import top.pullulate.common.constants.RSAConstant;
import top.pullulate.common.enums.Sex;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.entity.PulUserDept;
import top.pullulate.system.entity.PulUserRole;
import top.pullulate.system.mapper.PulUserMapper;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.system.service.IPulUserDeptService;
import top.pullulate.system.service.IPulUserRoleService;
import top.pullulate.system.service.IPulUserService;
import top.pullulate.utils.security.RSAUtils;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulUserViewVo;
import top.pullulate.web.data.vo.system.PulUserVo;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:   用户服务接口实现类
 * @Author: pullulate
 * @Date: 2020/6/12 0012 19:18
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulUserServiceImpl extends ServiceImpl<PulUserMapper, PulUser> implements IPulUserService {

    private final IPulUserRoleService userRoleService;

    private final IPulUserDeptService userDeptService;

    private final IPulRoleService roleService;

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
        userDeptService.remove(Wrappers.<PulUserDept>lambdaQuery().eq(PulUserDept::getUserId, userId));
        userRoleService.remove(Wrappers.<PulUserRole>lambdaQuery().eq(PulUserRole::getUserId, userId));
        removeById(userId);
        return P.success();
    }

    /**
     * 重置密码
     *
     * @param userVo    用户信息
     * @return
     */
    @Override
    public P resetPassword(PulUserVo userVo) {
        PulUser user = getById(userVo.getUserId());
        if (ObjectUtil.isNull(user)) {
            return P.error("用户不存在，请刷新页面后重试！");
        }
        if (willSuperman(userVo.getUserId())) {
            return P.error("不可重置管理员的密码！");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(Constant.DEFAULT_PASSWORD));
        return P.p(updateById(user));
    }

    /**
     * 修改密码
     *
     * @param userVo    用户信息
     * @return
     */
    @Override
    public P updatePassword(PulUserVo userVo) {
        PulUser user = getById(userVo.getUserId());
        if (willSuperman(user.getUserId())) {
            return P.error("不可修改管理员的密码！");
        }
        try {
            String oldPassword = RSAUtils.decryptByPrivateKey(RSAConstant.PRIVATE_KEY, userVo.getOldPassword());
            if (!oldPassword.equals(user.getPassword())) {
                return P.error("原密码错误！");
            }
            String password = RSAUtils.decryptByPrivateKey(RSAConstant.PRIVATE_KEY, userVo.getPassword());
            String encryptPassword = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encryptPassword);
        } catch (Exception e) {
            log.warn("用户：{}修改密码时无法解密，修改失败。异常信息：{}", user.getUserName(), e);
            return P.error("密码解密失败，请检查密码格式是否正确！");
        }
        return P.p(updateById(user));
    }

    /**
     * 权限分配
     *
     * @param userVo    分配信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P allocatePermission(PulUserVo userVo) {
        // 不允许修改超级管理员的角色信息
        if (willSuperman(userVo.getUserId())) {
            return P.error("不允许修改超级管理员！");
        }
        // 不允许设置其他人为超级管理员
        List<String> roleIds = Convert.toList(String.class, userVo.getRoleIds());
        List<PulRole> roles = roleService.listByIds(roleIds);
        boolean hasSuperman = roles.stream()
                .anyMatch(role -> Constant.ROLE_KEY_SUPREMAN.equals(role.getRoleKey()));
        if (hasSuperman) {
            return P.error("不允许设置其他人为超级管理员！");
        }
        // 删除已有的用户角色信息
        userRoleService.remove(Wrappers.<PulUserRole>lambdaQuery().eq(PulUserRole::getUserId, userVo.getUserId()));
        // 构建新的用户角色信息并保存
        List<PulUserRole> userRoles = roleIds.stream()
                .map(roleId -> new PulUserRole(userVo.getUserId(), roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
        // 保存新的部门信息
        userDeptService.remove(Wrappers.<PulUserDept>lambdaQuery().eq(PulUserDept::getUserId, userVo.getUserId()));
        userDeptService.save(new PulUserDept(userVo.getUserId(), userVo.getDeptId()));
        return P.success();
    }

    /**
     * 是否为管理员
     *
     * @param userId   用户主键
     * @return
     */
    private boolean willSuperman(String userId) {
        List<PulRole> roles = roleService.getUserRolesByUserId(userId);
        return roles.stream().anyMatch(role -> Constant.ROLE_KEY_SUPREMAN.equals(role.getRoleKey()));
    }
}
