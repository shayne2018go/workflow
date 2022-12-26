package com.workflow.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.workflow.workflow.config.SecurityUtils;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WorkflowApplicationTests {

	@Autowired
	private ProcessRuntime processRuntime;

	@Autowired
	private TaskRuntime taskRuntime;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private SecurityUtils securityUtils;

	@Test
	void test() {
		// ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		// // 2、获取RunTimeService
		// RuntimeService runtimeService = processEngine.getRuntimeService();
		// // 3、根据流程定义Id启动流程
		// ProcessInstance processInstance = runtimeService
		// .startProcessInstanceByKey("myEvection");
		// // 输出内容
		// System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
		// System.out.println("流程实例id：" + processInstance.getId());
		// System.out.println("当前活动Id：" + processInstance.getActivityId());
		Page<ProcessDefinition> defines = this.processRuntime.processDefinitions(Pageable.of(0, 100));
		System.out.println(JSON.toJSON(defines));
		assertEquals("Hello World", "Hello World");
	}

	@Test
	void startLeave() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("appyler", "ethan");
		ProcessInstance instance = this.runtimeService.startProcessInstanceByKey("leave", variables);
		System.out.println(instance.getProcessInstanceId());
		System.out.println(instance.getProcessDefinitionId());
		System.out.println(instance.getId());
		System.out.println("-----------------");
	}

	@Test
	void toStep1() {
		// this.securityManager.
		this.securityUtils.logInAs("test");
		Page<Task> tasks = this.taskRuntime.tasks(Pageable.of(0, 100));
		String applyer = (String) this.taskService.getVariable("a0c3a575-d5c6-11ec-a62c-00155d263213", "appyler");
		List<org.activiti.engine.task.Task> userTasks = this.taskService.createTaskQuery().taskAssignee("test").list();
		List<org.activiti.engine.task.Task> user2Tasks = this.taskService.createTaskQuery().taskAssignee("test2").list();
		// this.runtimeService.

		System.out.println(userTasks);
		System.out.println("====");
		System.out.println(user2Tasks);
		System.out.println("====");
		System.out.println(tasks.getContent());
		System.out.println("====");
		System.out.println(applyer);
	}

}
