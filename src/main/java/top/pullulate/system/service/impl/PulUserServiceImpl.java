package top.pullulate.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.mapper.PulUserMapper;
import top.pullulate.system.service.IPulUserService;

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
public class PulUserServiceImpl extends ServiceImpl<PulUserMapper, PulUser> implements IPulUserService {

    /**
     * 根据用户主键查询用户信息
     *
     * @param userId    用户主键
     * @return  用户信息
     */
    @Override
    public PulUser getUserInfoByUserId(String userId) {
        if (StrUtil.isBlank(userId)) {
            log.error("获取用户信息服务，用户标识为空");
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
            log.error("获取用户信息服务，手机号码为空");
            return null;
        }
        PulUser user = getOne(Wrappers.<PulUser>query()
                        .lambda().eq(PulUser::getPhone, phone));
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
            log.error("获取用户信息服务，用户名称为空");
            return null;
        }
        PulUser user = getOne(Wrappers.<PulUser>query()
                .lambda().eq(PulUser::getUserName, userName));
        return user;
    }
}
