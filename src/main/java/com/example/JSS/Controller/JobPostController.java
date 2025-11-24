package com.example.JSS.Controller;

import com.example.JSS.Api.ApiResponse;
import com.example.JSS.Model.JobPost;
import com.example.JSS.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job system/job post")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> getALL(){return ResponseEntity.status(200).body(jobPostService.getAll());}

    @PostMapping("/add/{userID}")
    public ResponseEntity<?> addPost(@PathVariable Integer userID,@RequestBody @Valid JobPost post, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

       if( jobPostService.addJobPost(post,userID).equals("added"))
        return ResponseEntity.status(200).body(new ApiResponse("job post added"));
        return ResponseEntity.status(400).body(new ApiResponse("role not allowed to post"));

    }

    @PutMapping("/update/{userID}/{idPost}")
    public ResponseEntity<?> updatePost(@PathVariable Integer idPost,@PathVariable Integer userID,@RequestBody @Valid JobPost post,Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        String message = jobPostService.updateJobPost(idPost,post,userID);
        if(message.equalsIgnoreCase("Job-post updated"))
            return ResponseEntity.status(200).body(new ApiResponse(message));
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    @DeleteMapping("/delete/{user}/{post}")
    public ResponseEntity<?> deletePost(@PathVariable Integer user,@PathVariable Integer post)
    {

    String mes = jobPostService.deletePost(post,user);
    if(mes.equals("deleted"))
        return ResponseEntity.status(200).body(new ApiResponse("post deleted"));
    return ResponseEntity.status(400).body(new ApiResponse(mes));

    }
}
