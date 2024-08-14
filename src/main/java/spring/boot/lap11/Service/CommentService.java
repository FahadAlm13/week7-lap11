package spring.boot.lap11.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.lap11.ApiResponse.ApiException;
import spring.boot.lap11.Model.Comment;
import spring.boot.lap11.Model.Post;
import spring.boot.lap11.Model.User;
import spring.boot.lap11.Repository.CommentRepository;
import spring.boot.lap11.Repository.PostRepository;
import spring.boot.lap11.Repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        User user = userRepository.findUsersByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (post == null) {
            throw new ApiException("Post not found");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment, Integer id) {
        Comment comment1 = commentRepository.findCommentByCommentId(id);
        User user = userRepository.findUsersByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());
        if (comment1 == null) {
            throw new ApiException("Comment not found");
        }
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (post == null) {
            throw new ApiException("Post not found");
        }

        comment1.setContent(comment.getContent());
        comment1.setUserId(comment.getUserId());
        comment1.setPostId(comment.getPostId());
        comment1.setCommentDate(comment.getCommentDate());

        commentRepository.save(comment1);
    }

    public void deleteComment(Integer id) {
        Comment comment1 = commentRepository.findCommentByCommentId(id);
        if (comment1 == null) {
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(comment1);
    }

    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> findAllByUserIdAndPostId(Integer userId, Integer postId) {
        User user = userRepository.findUsersByUserId(userId);
        Post post = postRepository.findPostByPostId(postId);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        if (post == null) {
            throw new ApiException("Post id not found");
        }
        return commentRepository.findAllByUserIdAndPostId(userId, postId);
    }
}
