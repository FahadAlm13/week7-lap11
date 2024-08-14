package spring.boot.lap11.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.lap11.ApiResponse.ApiException;
import spring.boot.lap11.Model.Category;
import spring.boot.lap11.Repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category, Integer id) {
        Category category1 = categoryRepository.findCategoryByCategoryId(id);
        if (category == null) {
            throw new ApiException("Category not found");
        }
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findCategoryByCategoryId(id);
        if (category == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(category);
    }

}
