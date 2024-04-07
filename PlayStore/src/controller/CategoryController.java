package controller;
import service.CategoryService;
import model.Category;
import java.util.List;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController() {
        this.categoryService = new CategoryService();
    }

    //get all categories
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    //region: extra methods
    //update category
    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }

    //create category
    public void createCategory(Category category) {
        categoryService.createCategory(category);
    }

    //get category by ID
    public Category getCategoryById(int category_id) {
        return categoryService.getCategoryById(category_id);
    }

    //delete category
    public void deleteCategory(int category_id) {
        categoryService.deleteCategory(category_id);
    }
    //region extra methods end

}
