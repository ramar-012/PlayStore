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
        String username = "TestUser";
        String password = "TestPassword";
        String role = "user";
        //when
        userController.registerUser(username, password, role);
    }

    @Test
    public void testAppCreation(){
        //given details for app creation
        String appName = "TestApp2";
        String appDescription = "TestAppDescription2";
        String releaseDate = "2022-09-01";
        String version = "1.2";
        double ratings = 4.4;
        String genre = "TestGenre";
        int maxAppID = applicationController.getMaxAppId();
        Application app = new Application(maxAppID+1, appName, appDescription, releaseDate, version, ratings, genre);

        //when
        applicationController.createApplication(app);
        //then
        assertNotNull(app, "App created successfully");
        System.out.println("App created successfully");

    }

}
