import controller.UserController;
import exceptions.exceptions;

import java.util.Scanner;

public class LoginThread extends Thread{
    private UserController userController;
    private static String username;
    private String userRole;

    public LoginThread(UserController userController){
        this.userController = userController;
    }

    @Override
    public void run(){
        int count = 1;
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username: ");
            username = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();

            //authenticate user
            boolean isAuthenticated = userController.loginUser(username, password);
            if(isAuthenticated){
                try {
                    userRole = userController.getUserRole(username);
                    System.out.println("Welcome " + userRole + ", " + username + "!");
                } catch (exceptions.UserNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            else{
                System.out.println("Invalid username or password. Please try again!");
                System.out.println("Attempt(s) " + count + " of 3");
                count ++;
            }
            if(count > 3){
                System.out.println("You have exceeded the maximum number of attempts. Exiting the app!");
                System.exit(0);
            }
        }

    }

    public String getUserRole(){
        return userRole;
    }
}