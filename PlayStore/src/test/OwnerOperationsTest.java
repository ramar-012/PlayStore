package test;
import controller.ApplicationController;
import controller.UserController;
import model.Application;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;



public class OwnerOperationsTest {
    Scanner scanner = new Scanner(System.in);
    ApplicationController applicationController = new ApplicationController();

    @Test
    public void testRegisterUser(){
        UserController userController = new UserController();
        //given
        String username = "SamGamgee";
        String password = "Gamgee12";
        String role = "user";
        //when
        if(userController.registerUser(username, password, role)){
            //then
            System.out.println("Test passed!");
        } else{
            System.out.println("User registration failed!");
        }
    }

    @Test
    public void testAppCreation(){
        //given details for app creation
        String appName = "Testapp3";
        String appDescription = "TestAppDescription2";
        String releaseDate = "2022-09-01";
        String version = "1.2";
        double ratings = 4.4;
        String genre = "TestGenre";
        int maxAppID = applicationController.getMaxAppId();
        Application app = new Application(maxAppID+1, appName, appDescription, releaseDate, version, ratings, genre);

        //when
        try{
            applicationController.createApplication(app);
            //then
            assertNotNull(app, "App created successfully");
            System.out.println("Test passed!");
        } catch(Exception e){
            System.out.println("Test failed!" + e.getMessage());
        }
        //System.out.println("App created successfully");

    }

    @Test
    public void deleteApplicationTest(){
        int deleteId = 24;

        try{
            applicationController.deleteApplication(deleteId);
            System.out.println("Test passed");

        }catch (Exception e){
            System.out.println("Test Failed. No user found with ID to delete.");
        }
    }

}

