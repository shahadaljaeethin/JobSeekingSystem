package com.example.JSS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "fill the name")
    @Size(min = 5, max=20, message = "name must be between 5-20 character")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name must be in letters")
    @Column(columnDefinition = "varchar(20)")
    private String name;

    @NotEmpty(message = "enter the email")
    @Email(message = "write valid email")
    @Column(columnDefinition = "varchar(40) unique")
    private String email;

    @NotEmpty(message = "enter the password")
    @Column(columnDefinition = "varchar(90)") //I will save it as hash
    private String password;

    @NotNull(message = "enter the age")
    @Positive(message = "age must be in positive")
    @Min(value = 21, message = "minimum age is 21")
    @Column(columnDefinition = "int")
    private Integer age;


    @NotEmpty(message = "enter the role")
    @Pattern(regexp = "^(EMPLOYER|JOB_SEEKER)$",message = "role can be job seeker or employer only")
    @Column(columnDefinition = "varchar(10)")
    private String role;


}
