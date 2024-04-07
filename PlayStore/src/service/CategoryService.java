package service;
import dao.CategoryDAO;
import model.Category;
import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }
     //create category
    public void createCategory(Category category) {
        categoryDAO.createCategory(category);
    }

    //get category by ID
    public Category getCategoryById(int category_id) {
        return categoryDAO.getCategoryById(category_id);
    }

    //get all categories
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    //update category
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    //delete category
    public void deleteCategory(int category_id) {
        categoryDAO.deleteCategory(category_id);
    }

}
