package spring.boot.lap11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.lap11.Model.Post;
import spring.boot.lap11.Service.PostService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(201).body("Post added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@Valid @RequestBody Post post, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.updatePost(post, id);
        return ResponseEntity.status(200).body("Post updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body("Post deleted successfully");
    }

    @GetMapping("/getByUserId")
    public ResponseEntity getPostsByUserId(@RequestParam Integer userId) {
        return ResponseEntity.status(200).body(postService.getPostsByUserId(userId));
    }

    @GetMapping("/getByTitle")
    public ResponseEntity getPostByTitle(@RequestParam String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/getByDateBefore")
    public ResponseEntity getPostsBeforeDate(@RequestParam LocalDate date) {
        return ResponseEntity.status(200).body(postService.getPostsBeforeDate(date));
    }
    @GetMapping("/findByTitleAndDate")
    public ResponseEntity<List<Post>> findByTitleAndDate(@RequestParam String title, @RequestParam String date) {
        LocalDate publishDate = LocalDate.parse(date);
        List<Post> posts = postService.findByTitleAndDate(title, publishDate);
        return ResponseEntity.status(200).body(posts);
    }
    @GetMapping("/findAllByTitleContaining")
    public ResponseEntity<List<Post>> findAllByTitleContaining(@RequestParam String keyword) {
        List<Post> posts = postService.findAllByTitleContaining(keyword);
        return ResponseEntity.status(200).body(posts);
    }
    @GetMapping("/findAllByCategoryName/{categoryName}")
    public ResponseEntity<List<Post>> findAllByCategoryName(@PathVariable String categoryName) {
        List<Post> posts = postService.findAllByCategoryName(categoryName);
        return ResponseEntity.status(200).body(posts);
    }
}
