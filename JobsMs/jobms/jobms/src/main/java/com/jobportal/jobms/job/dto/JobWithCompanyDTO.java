package com.jobportal.jobms.job.dto;

import com.jobportal.jobms.job.Job;
import com.jobportal.jobms.job.external.Company;

public class JobWithCompanyDTO {
    //responsed : job & company jsons -> using job & company objects
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
