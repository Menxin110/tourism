package com.springboot.tourism.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.tourism.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author oneda
 * @version 菜鸟
 */

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    // username 参数，是根据用户的输入提交过来的
    // 在此去根据username，查询数据库，在逻辑正确后，包装security中的User后返回
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登入的用户名" + username);

        QueryWrapper<com.springboot.tourism.pojo.User>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        com.springboot.tourism.pojo.User user = userMapper.selectOne(queryWrapper);

        if (user == null){

            throw new RuntimeException("输入的用户名有误！");
        }

        System.out.println(user);

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), auth);
    }
}
