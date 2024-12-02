package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-system/post")
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostService.getAllJobPosts();
        return ResponseEntity.status(200).body(jobPosts);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addJobPost(@PathVariable Integer userId, @RequestBody @Valid JobPost jobPost , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isAdded = jobPostService.addJobPost(userId, jobPost);
        if (isAdded) {
            return ResponseEntity.status(201).body(new ApiResponse("Job post added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employee can add job posts"));
    }

    @PutMapping("/update/{userId}/{postId}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer userId, @PathVariable Integer postId, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Integer isUpdeted = jobPostService.updateJobPost(userId, postId, jobPost);
        if (isUpdeted == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
        }
        if (isUpdeted == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employee can update job posts"));
    }

    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer userId, @PathVariable Integer postId) {
        Integer isDeleted = jobPostService.deleteJobPost(userId, postId);
        if (isDeleted == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
        }
        if (isDeleted == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only employee can deleted job posts"));
    }

}
