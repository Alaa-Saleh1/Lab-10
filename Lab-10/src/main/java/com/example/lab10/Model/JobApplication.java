package com.example.lab10.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "user Id cannot be null")
    @Column(nullable = false)
    private Integer userId;

    @NotNull(message = "job post Id cannot be null")
    @Column(nullable = false)
    private Integer jobPostId;
}
