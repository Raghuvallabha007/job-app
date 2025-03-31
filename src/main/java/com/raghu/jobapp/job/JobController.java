package com.raghu.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Saved Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job != null ?
                ResponseEntity.ok(job) :
                ResponseEntity.status(404).body("Not found!");
    }

    @DeleteMapping("job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        return deleted ?
                ResponseEntity.ok("Job deleted successfully") :
                ResponseEntity.status(404).body("not found");
    }

    @PutMapping("job/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job job = jobService.updateJobById(id, updatedJob);
        return job != null ?
                ResponseEntity.ok(job) :
                ResponseEntity.status(404).body("no job is present with id: {} " + id);
    }
}
