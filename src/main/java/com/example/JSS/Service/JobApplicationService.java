package com.example.JSS.Service;

import com.example.JSS.Model.JobApplication;
import com.example.JSS.Model.JobPost;
import com.example.JSS.Model.User;
import com.example.JSS.Repository.JobApplicationRepository;
import com.example.JSS.Repository.JobPostRepository;
import com.example.JSS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;
    //-

    public List<JobApplication> getAll(){
        return jobApplicationRepository.findAll();
    }

    public String applyJob(JobApplication job,Integer userID){
        JobPost post = jobPostRepository.getById(job.getJobPostId());
        if(post==null) return "failed applying, job-post not found";
        User user = userRepository.getById(job.getUserId());
        if(user==null) return "user not found";
        if(!user.getRole().equals("JOB_SEEKER")) return "role not allowed";
        if(userID!=user.getId()) return "you can't apply for others";
        //-
        jobApplicationRepository.save(job);
        return "Added";
    }

    public String withdrawJob(Integer id,Integer user){
        JobApplication job = jobApplicationRepository.getById(id);
        if(job==null) return "Job Appication not found";

        User user1 = userRepository.getById(user);
        if(user1==null) return "User not found";
        if(!user1.getRole().equals("JOB_SEEKER")) return "role not allowed";
        if(job.getUserId()!=user) return "this is not current user's application, you can't withdraw this application";

        jobApplicationRepository.delete(job);
        return "deleted";
    }


}
