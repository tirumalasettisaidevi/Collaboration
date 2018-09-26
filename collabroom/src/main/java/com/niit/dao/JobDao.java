package com.niit.dao;

import java.util.List;

import com.niit.entity.Job;

public interface JobDao {
	
	void addJob(Job job);
	List<Job> getAllJobs();
	Job getJob(int id);

}
