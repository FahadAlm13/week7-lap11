package spring.boot.lap11.Model;

import jakarta.persistence.*;
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
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull(message = "User ID shouldn't be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Post ID shouldn't be empty")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotEmpty(message = "Content shouldn't be empty")
    @Column(columnDefinition = "text not null")
    private String content;

    @NotNull(message = "Comment date shouldn't be null")
    @Column(columnDefinition = "date not null")
    private LocalDate commentDate;
}
