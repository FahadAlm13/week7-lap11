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
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotNull(message = "Category ID shouldn't be empty")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotEmpty(message = "Title shouldn't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "Content shouldn't be empty")
    @Column(columnDefinition = "text not null")
    private String content;

    @NotNull(message = "User ID shouldn't be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Publish date shouldn't be null")
    @Column(columnDefinition = "date not null")
    private LocalDate publishDate;
}
