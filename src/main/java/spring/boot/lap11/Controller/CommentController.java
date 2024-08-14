package spring.boot.lap11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.lap11.Model.Comment;
import spring.boot.lap11.Service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body("Comment added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@Valid @RequestBody Comment comment, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(comment, id);
        return ResponseEntity.status(200).body("Comment updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body("Comment deleted successfully");
    }

    @GetMapping("/getByPostId")
    public ResponseEntity getCommentsByPostId(@RequestParam Integer postId) {
        return ResponseEntity.status(200).body(commentService.getCommentsByPostId(postId));
    }
    @GetMapping("/findAllByUserIdAndPostId")
    public ResponseEntity<List<Comment>> findAllByUserIdAndPostId(@RequestParam Integer userId, @RequestParam Integer postId) {
        List<Comment> comments = commentService.findAllByUserIdAndPostId(userId, postId);
        return ResponseEntity.status(200).body(comments);
    }

}
