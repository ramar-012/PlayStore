import controller.UserController;
import exceptions.exceptions;

import java.util.Scanner;


public class App {
    public static int choice;
    public static String newPassword;
    public static UserController userController = new UserController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ask if user want to sign in or sign up, if sign in, then invoke login thread
        System.out.println("Do you wish to sign in or sign up?");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("Enter your choice: ");
        int signInChoice = Integer.parseInt(scanner.nextLine());
        switch (signInChoice) {
            case 1:
                break;
            case 2:
                //register user by taking input
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                while(true) {
                    System.out.println("Enter your password: ");
                    newPassword = scanner.nextLine();
                    System.out.println("Re-enter your password: ");
                    String rePassword = scanner.nextLine();
                    if(newPassword.equals(rePassword)) {
                        break;
                    }
                    System.out.println("Passwords do not match. Please try again.");
                }

                System.out.println("Enter your role: ");
                String role = scanner.nextLine();
                userController.registerUser(name, newPassword, role);
                break;
            default:
                System.out.println("Invalid choice. Exiting the app!");
                System.exit(0);
        }
        signIn();
    }

    public static void signIn() {
        Scanner scanner = new Scanner(System.in);
        //create login thread
        System.out.println("Signing In...");
        LoginThread loginThread = new LoginThread(userController);
        loginThread.start();

        try {
            //wait for login thread to complete
            loginThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String userRole = loginThread.getUserRole();

        //present menu depending on user role
        while (true) {

            switch (userRole) {
                case "user":
                    HandleUsers.displayUserMenu();
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                case "owner":
                    HandleUsers.displayOwnerMenu();
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                case "tester":
                    HandleUsers.handleTester();
                    break;
                default:
                    System.out.println("Invalid user role. Exiting the app!");
                    System.exit(0);
            }

            //perform actions based on user's choice
            if(userRole.equals("user") || userRole.equals("owner")) {
                try {
                    HandleUsers.UserAndOwnerActions(userRole, choice, scanner);
                } catch (exceptions.InvalidInputException | exceptions.CategoryNotFoundException |
                         exceptions.ApplicationNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
