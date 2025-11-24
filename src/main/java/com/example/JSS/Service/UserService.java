package com.example.JSS.Service;

import com.example.JSS.Model.JobApplication;
import com.example.JSS.Model.User;
import com.example.JSS.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JobApplicationService jobApplicationService;

//CRUDs))===================================================================

    public void addUser(User user){
        user.setPassword(hashPass(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public boolean updateUser(Integer id,User newInfo){

        User user = userRepository.getById(id);
        if(user==null) return false;

        user.setName(newInfo.getName());
        user.setEmail(newInfo.getEmail());
        user.setPassword(hashPass(newInfo.getPassword()));
        user.setAge(newInfo.getAge());
        user.setRole(newInfo.getRole());

        userRepository.save(user);
        return true;

    }

    public boolean deleteUser(Integer id){

        User user = userRepository.getById(id);
        if(user==null) return false;
        //remove all applications of this user
        List<JobApplication> applications = jobApplicationService.getAll();
        for(JobApplication job:applications)if(job.getUserId()==user.getId())jobApplicationService.withdrawJob(job.getId(),user.getId());


        userRepository.delete(user);
        return true;

    }



//=========================================================================

    public String hashPass(String password){
        try {
            //this method return the password as hashed for security database :)
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }



}
