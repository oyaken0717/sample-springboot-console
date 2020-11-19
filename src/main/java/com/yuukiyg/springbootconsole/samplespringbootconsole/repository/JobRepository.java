package com.yuukiyg.springbootconsole.samplespringbootconsole.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.yuukiyg.springbootconsole.samplespringbootconsole.domain.Job;

/**
 * 求人の情報を取得するレポジトリ.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class JobRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
    public void insert(List<Job> joblist) {
    	StringBuilder sql = new StringBuilder();
    	
    	sql.append("INSERT INTO jobs ");
    	sql.append(" (site_name, company_name, job_type, coding_languages, location, phone_number, business_details, url, published ) ");
    	sql.append("VALUES ");
    	sql.append(" (:siteName, :companyName, :jobType, :codingLanguages, :location, :phoneNumber, :businessDetails, :url, :published ) ");
    	
        template.batchUpdate(sql.toString(),
        		joblist.stream()
                        .map(job ->
                                new MapSqlParameterSource()
                                        .addValue("siteName", job.getSiteName())
                                        .addValue("companyName", job.getCompanyName())
                                        .addValue("jobType", job.getJobType())
                                        .addValue("codingLanguages", job.getCodingLanguages())
                                        .addValue("location", job.getLocation())
                                        .addValue("phoneNumber", job.getPhoneNumber())
                                        .addValue("businessDetails", job.getBusinessDetails())
                                        .addValue("url", job.getUrl())
                                        .addValue("published", job.getPublished())
                        ) 
                        .toArray(SqlParameterSource[]::new)
        );
    }	
}
