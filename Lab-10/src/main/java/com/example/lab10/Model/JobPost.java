package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "salary > 0")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title cannot be null")
    @Size(min = 4,message = "title must be more than 4 characters")
    @Column(columnDefinition = "varchar(60) not null")
    private String title;

    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String description;

    @NotEmpty(message = "location cannot be empty")
    @Column(columnDefinition = "varchar(60) not null")
    private String location;

    @Positive(message = "salary must be positive number")
    @NotNull(message = "salary cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer salary;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE", insertable = false, updatable = false)
    private LocalDate postingDate;
}
