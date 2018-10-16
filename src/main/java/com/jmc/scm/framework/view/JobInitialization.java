package com.jmc.scm.framework.view;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.bdf2.job.model.JobDefinition;
import com.bstek.bdf2.job.service.IJobDataService;
import com.bstek.dorado.core.Configure;

/**
 * SMC job初始化类
 * 
 * 
 * @author yuanyuan
 * @version 1.0 上午11:28:28 2015-1-26
 * @since scm 1.0
 * 
 */
@Component
public class JobInitialization implements IJobDataService {

	@Override
	public List<JobDefinition> filterJobs(List<JobDefinition> jobs) {
		return jobs;
	}

	@Override
	public String getCompanyId() {
		return Configure.getString("sys.companyId");
	}

}
