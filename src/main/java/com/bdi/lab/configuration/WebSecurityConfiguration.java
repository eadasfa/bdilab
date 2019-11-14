package com.bdi.lab.configuration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author:Yankaikai
 * @Description:安全配置类:包含用户登录，密码哈希（加盐），Ajax 化接口响应
 * @Date:Created in 2018/11/1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用csrf，不太安全
                .exceptionHandling()
                // 自定义认证响应，失败返回401
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .mvcMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin() // 表单登录接口
                .and()
                .logout() // 退出登录
                .logoutSuccessHandler(new AjaxLogoutSuccessHandler()) // 退出登录成功
                .permitAll().and()
                .httpBasic()
                .and()
                .sessionManagement()
                .maximumSessions(100)  //设置同一时间一个账号只能一次登录
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/login");
    }
    /**
     * 自定义登录认证响应，失败返回401
     */
    private class  AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            httpServletResponse.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
        }
    }
    /**
     * 登录失败处理类
     */
    private class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            httpServletResponse.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
        }
    }
    /**
     * 登出成功处理类
     */
    private class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                Authentication authentication
        ) throws IOException, ServletException {
            // 退出登录成功响应204
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());

        }
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
