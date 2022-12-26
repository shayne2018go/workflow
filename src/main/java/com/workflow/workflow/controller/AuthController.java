package com.workflow.workflow.controller;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.workflow.mapper.UserMapper;
import com.workflow.workflow.po.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
public class AuthController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ModelAndView register(@RequestParam("username") String username,
      @RequestParam("password") String password, Map<String, Object> map) {
    Integer isExist = this.userMapper.selectCount(new QueryWrapper<User>().eq("username", username));
    if (isExist > 0) {
      map.put("error", "用户已经存在");
      return new ModelAndView("register");
    }
    User user = new User();
    user.setUsername(username);
    user.setPassword(this.passwordEncoder.encode(password));
    this.userMapper.insert(user);
    return new ModelAndView("redirect:/login");
  }

  @PostMapping("/login")
  public ModelAndView login(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    System.out.println(333);
    System.out.println(username);
    System.out.println(password);
    return new ModelAndView("redirect:/");
  }
}
