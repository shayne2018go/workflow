package com.workflow.workflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = { "com.gitee.sunchenbin.mybatis.actable.dao.*", "com.workflow.workflow.*" })
@ComponentScan(basePackages = { "com.gitee.sunchenbin.mybatis.actable.manager.*", "com.workflow.workflow.*" })
public class WorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}

}
