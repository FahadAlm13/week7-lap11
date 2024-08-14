package spring.boot.lap11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "Username shouldn't be empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotEmpty(message = "Password shouldn't be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @Email
    @NotEmpty(message = "Email shouldn't be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotNull(message = "Registration date shouldn't be null")
    @Column(columnDefinition = "date not null")
    private LocalDate registrationDate;
}
