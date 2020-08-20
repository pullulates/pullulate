package top.pullulate.core.safe.injection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.core.exception.SqlInjectException;
import top.pullulate.utils.ServletUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @功能描述:   恶意sql拦截
 * @Author: xuyong
 * @Date: 2020/8/20 11:34
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
@WebFilter(urlPatterns = "/**", filterName = "MaliciousSqlInterceptor")
public class MaliciousSqlInterceptor implements Filter {

    private static final String regx = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" +
            "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url = ServletUtils.getRequest().getRequestURI();
        Map parametersMap = servletRequest.getParameterMap();
        Iterator it = parametersMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String[] value = (String[]) entry.getValue();
            for (int i = 0; i < value.length; i++) {
                if (value[i] != null && value[i ].matches(this.regx)) {
                    throw new SqlInjectException("发现非法参数：" + value[i]);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
