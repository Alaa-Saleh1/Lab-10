package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final UserRepository userRepository;

    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public Integer applyForJob(JobApplication jobApplication) {
        User user = userRepository.getById(jobApplication.getUserId());
        JobPost jobPost = jobPostRepository.getById(jobApplication.getJobPostId());
        if (user == null) {
            return 0;
        }
        if (jobPost == null) {
            return 1;
        }
        if (user.getRole().equals("EMPLOYER")){
            return 2;
        }

        jobApplicationRepository.save(jobApplication);
        return 3;

    }

    public Boolean withdrawJobApplication(Integer userId, Integer jobApplicationId) {
        JobApplication jobApplication = jobApplicationRepository.getById(jobApplicationId);
        if (jobApplication == null) {
            return false;
        }
        if (jobApplication.getUserId().equals(userId)) {
            jobApplicationRepository.delete(jobApplication);
            return true;
        }
        return false;
    }
}
