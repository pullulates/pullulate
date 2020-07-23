package top.pullulate.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.common.validate.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.core.security.user.UserInfo;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.service.IPulUserService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulUserViewVo;
import top.pullulate.web.data.vo.system.PulUserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @功能描述:   用户前端控制器
 * @Author: pullulates
 * @Date: 2020/6/12 0012 19:06
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/users")
@RequiredArgsConstructor
public class PulUserController {

    private final IPulUserService userService;

    private final TokenUtils tokenUtils;

    /**
     * 获取当前登录用户的用户信息
     * 包含了角色、部门、菜单、权限信息
     *
     * @return  当前登录用户的用户信息
     */
    @GetMapping("/get")
    public P getCurrentLoginUserInfo(HttpServletRequest request) {
        UserInfo userInfo = tokenUtils.getUserInfo(request);
        return P.data(userInfo);
    }

    /**
     * 根据用户主键获取用户信息
     *
     * @param userId    用户主键
     * @return  用户信息
     */
    @GetMapping("/info/{userId}")
    public P getUserInfoByUserId(@PathVariable("userId") String userId) {
        PulUser pulUser = userService.getUserInfoByUserId(userId);
        return P.data(pulUser);
    }

    /**
     * 获取用户列表分页数据
     *
     * @param pulUserVo   查询参数
     * @return
     */
    @GetMapping
    public P getUserPage(PulUserVo pulUserVo, Page page) {
        IPage<List<PulUserViewVo>> pages = userService.getUserPage(pulUserVo, page);
        return P.data(pages);
    }

    /**
     * 保存用户信息
     *
     * @param userVo    用户信息
     * @return
     */
    @PostMapping
    @OperationRecord(title = "系统用户-保存用户")
    public P saveUser(@RequestBody @Validated(Save.class) PulUserVo userVo) {
        return userService.saveUser(userVo);
    }

    /**
     * 修改用户信息
     *
     * @param userVo    用户信息
     * @return
     */
    @PutMapping
    @OperationRecord(title = "系统用户-修改用户")
    public P updateUser(@RequestBody @Validated(Update.class) PulUserVo userVo) {
        return userService.updateUser(userVo);
    }

    /**
     * 修改用户状态
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping
    @OperationRecord(title = "系统用户-修改用户状态")
    public P updateUserStatus(@RequestBody @Validated(PatchStatus.class) PulUserVo userVo) {
        return userService.updateUserStatus(userVo);
    }

    /**
     * 删除用户
     *
     * @param userId    用户主键
     * @return
     */
    @DeleteMapping
    @OperationRecord(title = "系统用户-删除用户")
    public P deleteUser(String userId) {
        return userService.deleteUser(userId);
    }

    /**
     * 重置密码
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping("/password-reset")
    @OperationRecord(title = "系统用户-重置密码")
    public P resetPassword(@RequestBody @Validated(ResetPassword.class) PulUserVo userVo) {
        return userService.resetPassword(userVo);
    }

    /**
     * 修改密码
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping("/password")
    @OperationRecord(title = "系统用户-重置密码")
    public P updatePassword(@RequestBody @Validated(UpdatePassword.class) PulUserVo userVo) {
        return userService.updatePassword(userVo);
    }
}
