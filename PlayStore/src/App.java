import controller.UserController;
import exceptions.exceptions;

import java.util.Scanner;


public class App {
    public static int choice;

    public static void main(String[] args)
            throws exceptions.InvalidInputException,
            exceptions.ApplicationNotFoundException,
            exceptions.CategoryNotFoundException {
        UserController userController = new UserController();
        Scanner scanner = new Scanner(System.in);

        //create login thread
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
                HandleUsers.UserAndOwnerActions(userRole, choice, scanner);
            }
        }
    }

}
