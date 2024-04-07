package test;
import controller.UserController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationTest {
    @Test
    public void testValidUserLogin(){
        //test case to check if user is authenticated
        //given
        UserController userController = new UserController();
        String username = "RamAdmin";
        String password = "12OwnerLogin";
        //when
        try{
            boolean isAuthenticated = userController.loginUser(username, password);
            //then
            assertTrue(isAuthenticated, "User is authenticated");
            System.out.println("User is authenticated");
        } catch (Exception e) {
            System.out.println("User is not authenticated");
        }
    }

    @Test
    public void InValidUserLogin(){
        //test case to check if user is not authenticated
        //given
        UserController userController = new UserController();
        String username = "Admin";
        String password = "12Owner";
        //when
        boolean isAuthenticated = userController.loginUser(username, password);
        //then
        assertFalse(isAuthenticated, "User is not authenticated");
        System.out.println("User is not authenticated");
    }
}
