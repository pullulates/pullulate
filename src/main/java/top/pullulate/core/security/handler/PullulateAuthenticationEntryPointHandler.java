package top.pullulate.core.security.handler;

import cn.hutool.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.HttpConstant;
import top.pullulate.utils.MessageUtils;
import top.pullulate.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @功能描述: 用户认证失败处理类
 * @Author: pullulate
 * @Date: 2020/6/10 0010 12:16
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Component
public class PullulateAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        response.setCharacterEncoding(HttpConstant.UTF8);
        ServletUtils.write(response, MessageUtils.get("exception.login.token.error"), HttpConstant.CONTENT_TYPE_JSON);
    }
}
