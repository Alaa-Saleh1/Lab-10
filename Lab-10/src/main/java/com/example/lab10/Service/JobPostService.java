package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;


    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public Boolean addJobPost(Integer userId, JobPost jobPost) {
        User user =  userRepository.getById(userId);
        if (user.getRole().equals("EMPLOYER")) {
            jobPostRepository.save(jobPost);
            return true;
        }
        return false;
    }

    public Integer updateJobPost(Integer userId, Integer postId, JobPost jobPost) {
        JobPost oldJobPost = jobPostRepository.getById(postId);
        if (oldJobPost == null) {
            return 0;
        }
        User user =  userRepository.getById(userId);
        if (user.getRole().equals("EMPLOYER")) {
            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setLocation(jobPost.getLocation());
            oldJobPost.setSalary(jobPost.getSalary());
            oldJobPost.setPostingDate(jobPost.getPostingDate());
            jobPostRepository.save(oldJobPost);
            return 1;
        }
        return 2;
    }

    public Integer deleteJobPost(Integer userId, Integer postId) {
        JobPost jobPost = jobPostRepository.getById(postId);
        if (jobPost == null) {
            return 0;
        }
        User user =  userRepository.getById(userId);
        if (user.getRole().equals("EMPLOYER")) {
            jobPostRepository.delete(jobPost);
            return 1;
        }
        return 2;
    }

}
