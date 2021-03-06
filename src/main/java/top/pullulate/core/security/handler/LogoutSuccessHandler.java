package top.pullulate.core.security.handler;

import cn.hutool.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.HttpConstant;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.utils.MessageUtils;
import top.pullulate.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @功能描述:   用户登出处理
 * @Author: pullulate
 * @Date: 2020/6/10 0010 12:18
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Component
@RequiredArgsConstructor
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private final TokenUtils tokenUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpStatus.HTTP_OK);
        response.setCharacterEncoding(HttpConstant.UTF8);
        tokenUtils.deleteUserInfo(request);
        ServletUtils.write(response, MessageUtils.get("login.out.success"), HttpConstant.CONTENT_TYPE_JSON);
    }
}
