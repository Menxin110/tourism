package com.springboot.tourism.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tourism.security.filter.CustomUsernamePasswordAuthFilter;
import com.springboot.tourism.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author oneda
 * @version 菜鸟
 */

@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

//    /**
//     * 全局认证管理器
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception{
//
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 开启默认创建登录信息表
//        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }

    /**
     * 做权限认证
     * 登录页配置
     *
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // 向过滤器链的指定位置添加过滤器
//        httpSecurity.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/index.html").permitAll()
                .and().authorizeRequests()
                .antMatchers("/", "/user/login").permitAll()
                // 允许静态资源访问
                .antMatchers("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.ico").permitAll()
                .anyRequest().authenticated()
                // 添加自动登录配置
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 2) // 设置有效时长
                .userDetailsService(userDetailsService)
                // 关闭csrf防护
                .and().csrf().disable();
        // 设置X-Frame-Options响应头来解决这个问题
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    CustomUsernamePasswordAuthFilter customAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthFilter filter = new CustomUsernamePasswordAuthFilter();
        // 自定义成功登录处理
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                JsonData jsonData = new JsonData();
                jsonData.setMsg("登录成功！");
                out.write(new ObjectMapper().writeValueAsString(jsonData));
                out.flush();
                out.close();
            }
        });
        // 自定义登录失败处理
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                JsonData jsonData = new JsonData();
                jsonData.setMsg("登录失败！");
                out.write(new ObjectMapper().writeValueAsString(jsonData));
                out.flush();
                out.close();
            }
        });

        // 配置全局认证管理器
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }
}
