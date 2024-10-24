package com.jobportal.jobms.job.impl;

import com.jobportal.jobms.job.Job;
import com.jobportal.jobms.job.JobRepository;
import com.jobportal.jobms.job.JobService;
import com.jobportal.jobms.job.dto.JobWithCompanyDTO;
import com.jobportal.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>(); --> use jobrepository instead :
    JobRepository jobRepository;

    public JobServiceImpl (JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();       //finad all the jobs from job repo interface
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();




        //INTER SERVICE COMMUNICATION : USING RESTTEMPLATE : SYNCHRONUS : BLOCKING -> GET COMPANY MS DATA
//        RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);  //convert the res form the api into company class
//        System.out.println(company.getName());
//        System.out.println(company.getId());
//        return jobs; -> instead of list, use jobRepositry.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());  //convert list to stream(seq of el that can be processed in pipeline) -> chaining into a map of which takes a function(convertToDto) as an arg to apply to every obj in map
        //and for every job obj we have the response collected using collect() method into a new collection : with help of collectors.toList() -> collects as list  imp of the collection and then it is returned

        //**make rest template call to the comp obj for every job obj we have, to add the comp obj to every job obj
    }

    private JobWithCompanyDTO convertToDto(Job job) {
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        RestTemplate restTemplate = new RestTemplate();     //create rest template and a for loop so that every job in the list we can get the comp obj
            //add comp + job obj to the dto object -> dto obj will go into the list -> we'll return the list at last
            jobWithCompanyDTO.setJob(job);
            //make call to comp ms :
            Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);  //convert the res form the api into company class
//set the company obj into the company dto obj

        //LOGGING FOR DEBUGGING
        System.out.println("Fetching company for Job ID: " + job.getId() + " with Company ID: " + job.getCompanyId());
            jobWithCompanyDTO.setCompany(company);
            return jobWithCompanyDTO;
    }

    @Override
    public void createJob(@RequestBody Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);        //save, findAll, etc are methods from jpa.presistence package
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById (Long id) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) jobs.remove(job);
//            return true;
//        }
//        return false;
        try {
            jobRepository.deleteById(id);       //delete method provided by jpa
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
//        if (jobs.size() > 0) {
//            for (Job job : jobs) {
//                jobs.remove(job);
//            }
//            return true;
//        }
//        return false;
        jobRepository.deleteAll();
        return true;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) {
////                jobs.remove(job);
////                jobs.add(updatedJob);
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//        }
//        return false;
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
