package com.jobportal.jobms.job;

import com.jobportal.jobms.job.dto.JobWithCompanyDTO;

import java.util.*;

public interface JobService {       //interface : for loose coupling & modularity
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean deleteAll();
    boolean updateJob(Long id, Job updatedJob);
}
