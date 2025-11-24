package com.example.JSS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "enter title")
    @Size(min=5,max=20,message = "size must be between 5-20 character")
    @Column(columnDefinition = "varchar(20)")
    private String title;

    @NotEmpty(message = "enter job description")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @NotEmpty(message = "enter job location")
    @Column(columnDefinition = "varchar(30)")
    private String location;

    @NotNull(message = "enter salary")
    @Positive(message = "salary must be positive")
    @Column(columnDefinition = "int")
    private Integer salary;

    @PastOrPresent(message = "posting should be from today or past")
    private LocalDateTime postingDate;

}
