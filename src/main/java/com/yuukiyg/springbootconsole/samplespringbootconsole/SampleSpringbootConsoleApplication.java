package com.yuukiyg.springbootconsole.samplespringbootconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.yuukiyg.springbootconsole.samplespringbootconsole.service.HelloService;

/**
 *
 * @author YuukiYg
 *
 */
@SpringBootApplication  // ポイント1: @SpringBootApplicationアノテーションをつける。
public class SampleSpringbootConsoleApplication {

	public static void main(String[] args) {

		// ポイント2: DIコンテナ（アプリケーションコンテキスト）を取得する。
		ConfigurableApplicationContext context = SpringApplication.run(SampleSpringbootConsoleApplication.class, args);

		// ポイント3: 取得したDIコンテナから、使いたいJavaBeanを取得する。
		HelloService service = context.getBean(HelloService.class);

		// ポイント4: 取得したJavaBeanを使用する。
		service.sayHello();

	}
}