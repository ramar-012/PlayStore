import controller.ApplicationController;
import controller.CategoryController;
import exceptions.exceptions;
import model.Application;
import model.Category;

import java.util.List;
import java.util.Scanner;
public class UserActions {
    //search application by name
    public static void searchTheApplication(String appName){
        ApplicationController applicationController = new ApplicationController();
        List<Application> seacrhResults = applicationController.getApplicationByName(appName);
        if(seacrhResults.isEmpty()){
            System.out.println("No application found with the name: " + appName);
        } else {
            System.out.println("Search results: ");
            for(Application application: seacrhResults){
                System.out.println(application);
            }
        }
    }

    //function to view all categories and select apps based on it
    public static void ViewCategories() throws exceptions.CategoryNotFoundException{
        CategoryController categoryController = new CategoryController();
        ApplicationController applicationController = new ApplicationController();
        List<Category> categories = categoryController.getAllCategories();

        if(categories.isEmpty()){
            System.out.println("No categories found!");
        } else {
            System.out.println("Categories: ");
            for(Category category: categories){
                System.out.println(category);
            }
        }
        System.out.println("Enter category name to view apps: ");
        Scanner scanner = new Scanner(System.in);
        String categoryName = scanner.nextLine();
        List<Application> applications = applicationController.getApplicationByGenre(categoryName);
        if(applications.isEmpty()){
            System.out.println("No applications found in the category: " + categoryName);
        } else {
            System.out.println("Applications in the category: " + categoryName);
            for(Application application: applications){
                System.out.println(application);
            }
        }
    }

    //function to get applications by ID
    public static void ViewApplicationsByID(int app_id){
        ApplicationController applicationController = new ApplicationController();
        Application application = applicationController.getApplicationById(app_id);
        if(application == null){
            System.out.println("No application found with the ID: " + app_id);
        } else {
            System.out.println("Application details: ");
            System.out.println(application);
        }
    }

}
