package com.workflow.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
  @GetMapping("")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView();
    System.out.println("123");
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @GetMapping("/login")
  public ModelAndView login() {
    ModelAndView modelAndView = new ModelAndView();
    System.out.println("222");

    modelAndView.setViewName("login");

    return modelAndView;
  }

  @GetMapping("/register")
  public ModelAndView registerView() {
    ModelAndView modelAndView = new ModelAndView();

    modelAndView.setViewName("register");

    return modelAndView;
  }
}
