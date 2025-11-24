package com.example.JSS.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "enter user id")
    @Column(columnDefinition = "varchar(10)")
    private Integer userId;
    @NotNull(message = "enter job post id")
    @Column(columnDefinition = "varchar(10)")
    private Integer jobPostId;
}
