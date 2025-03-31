package com.raghu.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    Job updateJobById(Long id, Job updatedJob);
}
