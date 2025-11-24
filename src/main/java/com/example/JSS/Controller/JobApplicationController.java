package com.example.JSS.Controller;

import com.example.JSS.Api.ApiResponse;
import com.example.JSS.Model.JobApplication;
import com.example.JSS.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job system/job apply")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    //--
    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(200).body(jobApplicationService.getAll());
    }

    @PostMapping("/add/{userID}")
    public ResponseEntity<?> applyJob(@PathVariable Integer userID,@RequestBody @Valid JobApplication job, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());


        String message= jobApplicationService.applyJob(job,userID);
        if("Added".equals(message))
            return ResponseEntity.status(200).body(new ApiResponse("Job Applied."));
        return ResponseEntity.status(400).body(message);

    }
    @DeleteMapping("/withdraw/{user}/{id}")
    public ResponseEntity<?> withdrawJob(@PathVariable Integer id,@PathVariable Integer user){
      String message = jobApplicationService.withdrawJob(id,user);
      if(message.equals("deleted")) return ResponseEntity.status(200).body(new ApiResponse("withdraw the application successfully"));
      return ResponseEntity.status(400).body(new ApiResponse(message));

    }

}
