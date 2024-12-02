package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-system/app")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobApplication() {
        List<JobApplication> allJobApplication = jobApplicationService.getAllJobApplications();
        return ResponseEntity.status(200).body(allJobApplication);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyJobApplication(@RequestBody @Valid JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Integer isApply = jobApplicationService.applyForJob(jobApplication);
        if (isApply == 3) {
            return ResponseEntity.status(200).body(new ApiResponse("Apply Success"));
        }
        if (isApply == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Only JOB_SEEKER user can apply"));
        }
        if (isApply == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Job post Id not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Id not found"));
    }

    @DeleteMapping("/withdraw/{userId}/{jobAppId}")
    public ResponseEntity<?> withdrawJobApplication(@PathVariable Integer userId, @PathVariable Integer jobAppId) {
        Boolean isWithdraw= jobApplicationService.withdrawJobApplication(userId,jobAppId);
        if (isWithdraw == true) {
            return ResponseEntity.status(200).body(new ApiResponse("Withdraw Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Withdraw Failed"));
    }



}
