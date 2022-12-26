package com.workflow.workflow.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.workflow.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.workflow.workflow.po.User dbUser = this.userMapper
        .selectOne(new QueryWrapper<com.workflow.workflow.po.User>().eq("username", username));

    User user = new User(dbUser.getUsername(), dbUser.getPassword(), true, true, true, true,
        AuthorityUtils.createAuthorityList("admin"));

    return user;
  }

}
