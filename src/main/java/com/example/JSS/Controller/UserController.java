package com.example.JSS.Controller;

import com.example.JSS.Api.ApiResponse;
import com.example.JSS.Model.User;
import com.example.JSS.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job system/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //--
    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        //--
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors)
    {
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        //--
        if(userService.updateUser(id,user))
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("User removed"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));


    }
}
