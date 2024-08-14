package spring.boot.lap11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.lap11.Model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentByCommentId(Integer commentId);

    List<Comment> findByPostId(Integer postId);

    @Query("select c from Comment c where c.userId = ?1 and c.postId = ?2")
    List<Comment> findAllByUserIdAndPostId(Integer userId, Integer postId);

}
