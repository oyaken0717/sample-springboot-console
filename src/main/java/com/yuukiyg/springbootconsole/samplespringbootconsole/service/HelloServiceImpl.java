package com.yuukiyg.springbootconsole.samplespringbootconsole.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author YuukiYg
 *
 */
@Component
public class HelloServiceImpl implements HelloService{

	@Override
	public void sayHello() {
		System.out.println("Hello World!");
	}

}
