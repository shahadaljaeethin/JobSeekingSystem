package com.example.JSS.Service;

import com.example.JSS.Model.JobPost;
import com.example.JSS.Model.User;
import com.example.JSS.Repository.JobPostRepository;
import com.example.JSS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    //-

    public String addJobPost(JobPost post,Integer id_user){
        User user = userRepository.getReferenceById(id_user);
        if(user==null) return "user not found";
        if(!user.getRole().equalsIgnoreCase("employer")) return "role not allowed";
        if(post.getPostingDate()==null) post.setPostingDate(LocalDateTime.now()); //if user didnt write it set to now
        jobPostRepository.save(post);
    return "added";
    }
    public List<JobPost> getAll(){return jobPostRepository.findAll();}


    public String updateJobPost(Integer id, JobPost info,Integer userID){
    JobPost post = jobPostRepository.getById(id);
    if(post==null) return "Job post not found";

    User user = userRepository.getById(userID);
    if(user==null) return "user not found";
    if(!user.getRole().equalsIgnoreCase("employer")) return "role not allowed";


    post.setTitle(info.getTitle());
    post.setDescription(info.getDescription());
    post.setPostingDate(info.getPostingDate());
    post.setSalary(info.getSalary());
    post.setLocation(info.getLocation());

    jobPostRepository.save(post);
    return "Job-post updated";

    }


    public String deletePost(Integer id,Integer user){
        JobPost post = jobPostRepository.getById(id);
        if(post==null) return "job post not found";

        User user1 = userRepository.getReferenceById(user);
        if(user1==null) return "user not found";
        if(!user1.getRole().equalsIgnoreCase("employer")) return "role not allowed";


        jobPostRepository.delete(post);
        return "deleted";
    }
}
