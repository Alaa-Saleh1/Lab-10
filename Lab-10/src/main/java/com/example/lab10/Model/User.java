package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "age > 21 ")
@Check(constraints = "role= 'JOB_SEEKER' or role= 'EMPLOYER'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4 , max = 30,message = "name must be more than 4 characters")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "name must contain only characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @Email(message = "email must be valid format")
    @NotEmpty(message = "email cannot be empty")
    @Column(columnDefinition = "varchar(60) not null unique")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(18) not null")
    private String password;

    @NotNull(message = "age cannot be null")
    @Positive(message = "age must be number")
    @Column(columnDefinition = "int not null")
    @Min(value = 21,message = "age must be more than 21")
    private Integer age;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "role must be either \"JOB_SEEKER\" or \"EMPLOYER\" only.")
    @Column(columnDefinition = "varchar(10)")
    private String role;
}
