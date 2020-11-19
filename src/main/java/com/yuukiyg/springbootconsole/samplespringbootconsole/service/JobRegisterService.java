package com.yuukiyg.springbootconsole.samplespringbootconsole.service;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuukiyg.springbootconsole.samplespringbootconsole.domain.Job;
import com.yuukiyg.springbootconsole.samplespringbootconsole.repository.JobRepository;

@Service
@Transactional
public class JobRegisterService {
	
	public void insert(List<Job> jobList,ConfigurableApplicationContext context) {
		JobRepository jobRepository = context.getBean(JobRepository.class);
		jobRepository.insert(jobList);
	}
}
