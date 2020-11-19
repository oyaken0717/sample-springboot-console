package com.yuukiyg.springbootconsole.samplespringbootconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.yuukiyg.springbootconsole.samplespringbootconsole.batch.JobRegisterBatch;

/**
 *
 * @author YuukiYg
 *
 */
@SpringBootApplication  // ポイント1: @SpringBootApplicationアノテーションをつける。
@EnableScheduling
public class SampleSpringbootConsoleApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SampleSpringbootConsoleApplication.class, args);// ポイント2: DIコンテナ（アプリケーションコンテキスト）を取得する。
		JobRegisterBatch jobRegisterBatch = context.getBean(JobRegisterBatch.class);
		jobRegisterBatch.jobRegister(context);
	}
}