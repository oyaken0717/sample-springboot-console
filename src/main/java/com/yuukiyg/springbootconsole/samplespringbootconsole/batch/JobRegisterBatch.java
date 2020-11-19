package com.yuukiyg.springbootconsole.samplespringbootconsole.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuukiyg.springbootconsole.samplespringbootconsole.SampleSpringbootConsoleApplication;
import com.yuukiyg.springbootconsole.samplespringbootconsole.domain.Job;
import com.yuukiyg.springbootconsole.samplespringbootconsole.service.EnService;
import com.yuukiyg.springbootconsole.samplespringbootconsole.service.JobRegisterService;

/**
 * 求人登録のバッチ.
 * 
 * @author oyamadakenji
 *
 */
@Component
public class JobRegisterBatch {

	/**
	 * 求人登録.
	 * 
	 */
	@Scheduled(cron = "0 50 12 * * 4", zone = "Asia/Tokyo")
	public void jobRegister(ConfigurableApplicationContext context) {

		EnService enService = context.getBean(EnService.class);
		JobRegisterService jobRegisterService = context.getBean(JobRegisterService.class);

		List<Job> enJobList = enService.searchJob();
		List<List<Job>> allSiteList = Arrays.asList(enJobList);
		List<Job> allJobList = new ArrayList<>();
		for (List<Job> siteList : allSiteList) {
			allJobList.addAll(siteList);
		}
		jobRegisterService.insert(allJobList,context);
	
	}
}