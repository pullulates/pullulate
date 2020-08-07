package top.pullulate.core.security.handler;

import cn.hutool.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.HttpConstant;
import top.pullulate.utils.MessageUtils;
import top.pullulate.utils.ServletUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @功能描述:   权限控制异常
 * @Author: pullulate
 * @Date: 2020/6/12 0012 23:39
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Component
public class PullulateAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        response.setCharacterEncoding(HttpConstant.UTF8);
        ServletUtils.write(response, MessageUtils.get("no.permission"), HttpConstant.CONTENT_TYPE_JSON);
    }
}
