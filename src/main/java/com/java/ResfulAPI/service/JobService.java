package com.java.ResfulAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.ResfulAPI.domain.Company;
import com.java.ResfulAPI.domain.Job;
import com.java.ResfulAPI.exception.ResourceNotFoundException;
import com.java.ResfulAPI.repository.CompanyRepository;
import com.java.ResfulAPI.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job createJob(Job job) {
        Long companyId = job.getCompany().getId();

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy company với id = " + companyId));

        job.setCompany(company);

        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy job với id = " + id));
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job updateJob(Long id, Job request) {
        Job currentJob = getJobById(id);

        currentJob.setTitle(request.getTitle());
        currentJob.setDescription(request.getDescription());
        currentJob.setLocation(request.getLocation());
        currentJob.setSalary(request.getSalary());

        Long companyId = request.getCompany().getId();

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy company với id = " + companyId));

        currentJob.setCompany(company);

        return jobRepository.save(currentJob);
    }

    public void deleteJob(Long id) {
        Job job = getJobById(id);
        jobRepository.delete(job);
    }
}