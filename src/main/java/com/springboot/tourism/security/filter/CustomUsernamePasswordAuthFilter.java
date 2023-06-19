package com.springboot.tourism.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author oneda
 * @version 菜鸟
 */

public class CustomUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/user/login", "POST");

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
//            ObjectMapper mapper = new ObjectMapper();
//            UsernamePasswordAuthenticationToken authRequest = null;
//            try (InputStream is = request.getInputStream()) {
//                Map<String,String> authenticationBean = mapper.readValue(is, Map.class);
//                authRequest = new UsernamePasswordAuthenticationToken(
//                        authenticationBean.get("username"), authenticationBean.get("password"));
//            } catch (IOException e) {
//                e.printStackTrace();
//                authRequest = new UsernamePasswordAuthenticationToken(
//                        "", "");
//            } finally {
//                setDetails(request, authRequest);
//                return this.getAuthenticationManager().authenticate(authRequest);
//            }
//        }
//        else {
//            return super.attemptAuthentication(request, response);
//        }

        //判断请求方法是否是post
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            //判断请求头的content是否是json格式。
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                Map<String, String> map;
                UsernamePasswordAuthenticationToken authRequest = null;
                try {
                    //转换输入的json数据为map格式
                    map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                    //获取username的值
                    String username = map.get(getUsernameParameter());//getUsernameParameter：username
                    //获取password
                    String password = map.get(getPasswordParameter());//getPasswordParameter：password
                    //设置值
                    authRequest = new UsernamePasswordAuthenticationToken(username, password);
                } catch (IOException e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken("", "");
                } finally {
                    //发送给UserDetailsService信息
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }
            }
            //直接调用父类处理key和value这种formData类型
            return super.attemptAuthentication(request, response);
        }
    }
}
