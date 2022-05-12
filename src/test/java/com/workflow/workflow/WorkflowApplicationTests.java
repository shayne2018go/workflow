package com.workflow.workflow;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
class WorkflowApplicationTests {

	@Autowired
	private ProcessRuntime processRuntime;

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
		System.out.println(111);
		Page<ProcessDefinition> defines = this.processRuntime.processDefinitions(Pageable.of(0, 100));
		System.out.println(defines);
		assertEquals("Hello World", "Hello World");
	}

}
