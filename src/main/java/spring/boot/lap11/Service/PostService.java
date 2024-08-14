package spring.boot.lap11.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.lap11.ApiResponse.ApiException;
import spring.boot.lap11.Model.Category;
import spring.boot.lap11.Model.Post;
import spring.boot.lap11.Model.User;
import spring.boot.lap11.Repository.CategoryRepository;
import spring.boot.lap11.Repository.PostRepository;
import spring.boot.lap11.Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        User user = userRepository.findUsersByUserId(post.getUserId());
        Category category = categoryRepository.findCategoryByCategoryId(post.getCategoryId());
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (category == null) {
            throw new ApiException("Category not found");
        }
        postRepository.save(post);
    }
    public void updatePost(Post post,Integer id) {
        Post post1 = postRepository.findPostByPostId(id);
        User user = userRepository.findUsersByUserId(post.getUserId());
        Category category = categoryRepository.findCategoryByCategoryId(post.getCategoryId());
        if (post == null ){
            throw new ApiException("Post not found");
        }
        if (user == null){
            throw new ApiException("User not found");
        }
        if (category == null){
            throw new ApiException("Category not found");
        }
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setCategoryId(post.getCategoryId());
        post1.setUserId(post.getUserId());
        post1.setPublishDate(post.getPublishDate());

        postRepository.save(post1);
    }
    public void deletePost(Integer id) {
        Post post = postRepository.findPostByPostId(id);
        if (post == null){
            throw new ApiException("Post not found");
        }
        postRepository.delete(post);
    }
    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> getPostsBeforeDate(LocalDate date) {
        return postRepository.findByPublishDateBefore(date);
    }
    public List<Post> findByTitleAndDate(String title, LocalDate date) {
        return postRepository.findByTitleAndDate(title, date);
    }
    public List<Post> findAllByTitleContaining(String keyword) {
        return postRepository.findAllByTitleContaining(keyword);
    }
    public List<Post> findAllByCategoryName(String categoryName) {
        return postRepository.findAllByCategoryName(categoryName);
    }
}
