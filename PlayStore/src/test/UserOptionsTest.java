package test;
import controller.ApplicationController;
import controller.CategoryController;
import model.Application;
import model.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserOptionsTest {

    @Test
    public void testSearchAppbyName(){
        //test case to check if app is found by name
        //given
        ApplicationController appController = new ApplicationController();
        String appName = "Instagram";
        //when

        try{
            List<Application> app = appController.getApplicationByName(appName);
            assertNotNull(app, "App found by name");
            assertEquals(appName, app.get(0).getName());
            System.out.println("App found by name");
        } catch (Exception e){
            System.out.println("App not found by name");
        }
    }

    @Test
    public void testViewAppCategories(){
        //test case to check if app is found by genre
        //given
        CategoryController categoryController = new CategoryController();
        //when
        try{
            List<Category> app = categoryController.getAllCategories();
            assertNotNull(app, "App found by genre");
            System.out.println("Categories available");
        } catch (Exception e){
            System.out.println("No categories available");
        }
    }

    @Test
    public void testApplicationbyID(){
        ApplicationController appController = new ApplicationController();
        Application application = appController.getApplicationById(20);
        try{
            assertNotNull(application, "App found by ID");
            System.out.println("App found by ID");
        } catch (Exception e){
            System.out.println("App not found by ID");
        }
    }
}
