import exceptions.exceptions;
import test.OwnerOperationsTest;
import test.UserAuthenticationTest;
import test.UserOptionsTest;

import java.util.Arrays;
import java.util.Scanner;

public class HandleUsers {
    public static void displayUserMenu(){
        System.out.println("User Menu Options: ");
        for (String s : Arrays.asList("1. Search Application", "2. View Apps by Categories",
                "3. View Application Details", "4. Log Out")) {
            System.out.println(s);
        }
    }

    public static void displayOwnerMenu(){
        System.out.println("Owner Menu Options: ");
        for (String s : Arrays.asList("1. Register User", "2. Edit Applications", "3. Log Out")) {
            System.out.println(s);
        }
    }

    public static void UserAndOwnerActions(String userRole, int choice, Scanner scanner)
            throws exceptions.InvalidInputException,
            exceptions.CategoryNotFoundException,
            exceptions.ApplicationNotFoundException {

        switch (choice) {
            case 1:
                if (userRole.equals("user")) {
                    System.out.println("Enter application name: ");
                    String appName = scanner.nextLine();
                    UserActions.searchTheApplication(appName);
                } else if (userRole.equals("owner")) {
                    //register a new user
                    OwnerActions.addUser();
                }
                break;
            case 2:
                if (userRole.equals("user")) {
                    UserActions.ViewCategories();
                } else if (userRole.equals("owner")) {
                    //Perform CRUD in Application
                    OwnerActions.crudOperationsInApplicationTable();
                }
                break;
            case 3:
                if (userRole.equals("user")) {
                    System.out.println("Enter application ID: ");
                    int app_id = Integer.parseInt(scanner.nextLine());
                    UserActions.ViewApplicationsByID(app_id);
                } else if (userRole.equals("owner")) {
                    //Log out
                    LoginThread.handleLogout(scanner);
                    break;
                }
                break;
            case 4:
                //logout
                LoginThread.handleLogout(scanner);
                break;
            default:
                System.out.println("Invalid choice, Please try again!");
                break;
        }
    }

    public static void handleTester(){
        UserOptionsTest userOptionsTest = new UserOptionsTest();
        UserAuthenticationTest userAuthenticationTest = new UserAuthenticationTest();
        OwnerOperationsTest ownerOperationsTest = new OwnerOperationsTest();

        System.out.println("Tester Menu Options: ");
        for (String s : Arrays.asList("1. Test User Authentication", "2. Test User Options",
                "3. Test Owner Operations", "4. Log Out")) {
            System.out.println(s);
        }
        System.out.println("Enter functionality to test: ");


        Scanner testScanner = new Scanner(System.in);
        int testerChoice = Integer.parseInt(testScanner.nextLine());

        switch(testerChoice){
            case 1:
                System.out.println("Please select test case to run for Authentication: ");
                for(String s : Arrays.asList("1. Valid Login", "2. Invalid Login")) {
                    System.out.println(s);
                }
                System.out.println("Enter test case to run: ");
                //test case to run
                int authChoice = Integer.parseInt(testScanner.nextLine());
                if(authChoice == 1){
                    userAuthenticationTest.testValidUserLogin();
                } else if(authChoice == 2){
                    userAuthenticationTest.InValidUserLogin();
                }
                break;


            case 2:
                System.out.println("Please select test case to run for User Options: ");
                for (String s : Arrays.asList("1. Search App by Name", "2. View App Categories",
                        "3. View App by ID")) {
                    System.out.println(s);
                }
                System.out.println("Enter test case to run: ");
                //test case to run
                int userChoice = Integer.parseInt(testScanner.nextLine());
                if(userChoice == 1){
                    userOptionsTest.testSearchAppbyName();
                } else if(userChoice == 2){
                    userOptionsTest.testViewAppCategories();
                } else if(userChoice == 3){
                    userOptionsTest.testApplicationbyID();
                }
                break;


            case 3:
                System.out.println("Please select test case to run for Owner Operations: ");
                for (String s : Arrays.asList("1. Register User", "2. Create App")) {
                    System.out.println(s);
                }
                System.out.println("Enter test case to run: ");
                //test case to run
                int ownerChoice = Integer.parseInt(testScanner.nextLine());
                if(ownerChoice == 1){
                    ownerOperationsTest.testRegisterUser();
                } else if(ownerChoice == 2){
                    ownerOperationsTest.testAppCreation();
                }
                break;
            case 4:
                System.out.println("Logging out...");
                LoginThread.handleLogout(testScanner);
                break;
            default:
                System.out.println("Invalid choice, Please try again!");
                break;
        }
    }
}
