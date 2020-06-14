package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulUser;

/**
 * @功能描述:   用户服务接口
 * @Author: pullulates
 * @Date: 2020/6/12 0012 19:12
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface IPulUserService extends IService<PulUser> {

    /**
     * 根据用户主键查询用户信息
     *
     * @param userId    用户主键
     * @return  用户信息
     */
    PulUser getUserInfoByUserId(String userId);

    /**
     * 根据手机号码查询用户信息
     *
     * @param phone    手机号码
     * @return  用户信息
     */
    PulUser getUserInfoByPhone(String phone);

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName    用户名称
     * @return  用户信息
     */
    PulUser getUserInfoByUserName(String userName);
}
