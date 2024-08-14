package spring.boot.lap11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.lap11.Model.Post;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByPostId(Integer postId);

    List<Post> findByUserId(Integer userId);

    Post findByTitle(String title);

    List<Post> findByPublishDateBefore(LocalDate date);

    @Query("select p from Post p where p.title=?1 and p.publishDate=?2")
    List<Post> findByTitleAndDate(String title , LocalDate date);

    @Query("select p from Post p where p.title like %?1%")
    List<Post> findAllByTitleContaining(String keyword);

    @Query("select p from Post p join Category c on p.categoryId = c.categoryId where c.name=?1")
    List<Post> findAllByCategoryName(String categoryName);
}
