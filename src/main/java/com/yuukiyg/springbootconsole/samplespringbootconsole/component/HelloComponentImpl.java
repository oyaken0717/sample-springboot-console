package com.yuukiyg.springbootconsole.samplespringbootconsole.component;

import org.springframework.stereotype.Component;

/**
 *
 * @author YuukiYg
 *
 */
@Component
public class HelloComponentImpl implements HelloComponent{

	@Override
	public void sayHello() {
		System.out.println("Hello World!");
	}
}
