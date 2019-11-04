package com.yuukiyg.springbootconsole.samplespringbootconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.yuukiyg.springbootconsole.samplespringbootconsole.component.HelloComponent;

/**
 *
 * @author YuukiYg
 *
 */
@SpringBootApplication
public class SampleSpringbootConsoleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SampleSpringbootConsoleApplication.class, args);
		HelloComponent service = context.getBean(HelloComponent.class);
		service.sayHello();
	}

}
