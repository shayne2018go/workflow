package com.workflow.workflow.controller;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

  @Autowired
  private ProcessRuntime processRuntime;

  @GetMapping("/test")
  public Page<ProcessDefinition> test() {

    return this.processRuntime.processDefinitions(Pageable.of(0, 100));
  }
}
