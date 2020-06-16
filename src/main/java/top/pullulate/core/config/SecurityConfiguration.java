package top.pullulate.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.pullulate.core.filter.PullulateAuthenticationTokenFilter;
import top.pullulate.core.handler.PullulateAuthenticationEntryPointHandler;
import top.pullulate.core.handler.LogoutSuccessHandler;
import top.pullulate.core.handler.PullulateAccessDeniedHandler;
import top.pullulate.core.service.UserDetailService;

/**
 * @功能描述:   spring security信息配置
 * @Author: pullulates
 * @Date: 2020/6/10 0010 11:57
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailService userDetailService;

    private final PullulateAccessDeniedHandler accessDeniedHandler;

    private final PullulateAuthenticationEntryPointHandler authenticationEntryPointHandler;

    private final LogoutSuccessHandler logoutSuccessHandler;

    private final PullulateAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 具体配置
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointHandler)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/captcha**").anonymous()
                .antMatchers("/druid/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("pullulate"));
//        System.out.println(new BCryptPasswordEncoder().matches());
    }
}
