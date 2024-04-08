import controller.ApplicationController;
import controller.UserController;
//import exceptions.exceptions.ApplicationNotFoundException;
import exceptions.exceptions;
import model.Application;

import java.util.Scanner;
public class OwnerActions {

    //function to add a new user
    public static void addUser() throws exceptions.InvalidInputException {
        UserController userController = new UserController();

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Enter role: ");
            String role = scanner.nextLine();
            boolean ifCreated = userController.registerUser(username, password, role);
            if (ifCreated) {
                System.out.println("User created successfully!");
                break;
            } else {
                System.out.println("User creation failed, Try again!");
            }
        }
    }

    //CRUD operations on application
    // Function to perform CRUD operations in the Application table
    public static void crudOperationsInApplicationTable() throws exceptions.ApplicationNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ApplicationController applicationController = new ApplicationController();

        while (true) {
            // Present CRUD options
            System.out.println("CRUD Options:");
            System.out.println("1. Create Application");
            System.out.println("2. Read Application by Name");
            System.out.println("3. Update Application");
            System.out.println("4. Delete Application");
            System.out.println("5. Exit");

            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Perform CRUD operation based on user's choice
            switch (choice) {
                case 1:
                    // Create Application
                    createApplication(applicationController, scanner);
                    break;
                case 2:
                    System.out.println("Enter application name:");
                    String Name = scanner.nextLine();
                    // Read Application by Name
                    UserActions.searchTheApplication(Name);
                    break;
                case 3:
                    // Update Application
                    updateTheApplications(applicationController, scanner);
                    break;
                case 4:
                    // Delete Application
                    deleteApplicationbyID(applicationController, scanner);
                    break;
                case 5:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Function to create a new application
    private static void createApplication(ApplicationController applicationController, Scanner scanner) {
        // Get the maximum application ID
        int maxAppId = applicationController.getMaxAppId();

        System.out.println("Enter application name:");
        String name = scanner.nextLine();

        System.out.println("Enter application description:");
        String description = scanner.nextLine();

        System.out.println("Enter application release date (YYYY-MM-DD):");
        String releaseDate = scanner.nextLine();

        System.out.println("Enter application version:");
        String version = scanner.nextLine();

        System.out.println("Enter application ratings:");
        double ratings = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter application genre:");
        String genre = scanner.nextLine();

        // Create a new Application object with the provided details
        Application newApplication = new Application(maxAppId+1, name, description, releaseDate, version, ratings, genre);

        // Add the new application to the application table
        applicationController.createApplication(newApplication);
    }

    // Function to update an existing application
    private static void updateTheApplications(ApplicationController applicationController, Scanner scanner) throws exceptions.ApplicationNotFoundException {
        System.out.println("Enter application ID:");
        int app_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Get the application details by ID
        Application application = applicationController.getApplicationById(app_id);


        if (application == null) {
            System.out.println("No application found with the ID: " + app_id);
        } else {
            System.out.println("Enter new application name:");
            String name = scanner.nextLine();

            System.out.println("Enter new application description:");
            String description = scanner.nextLine();

            System.out.println("Enter new application release date (YYYY-MM-DD):");
            String releaseDate = scanner.nextLine();

            System.out.println("Enter new application version:");
            String version = scanner.nextLine();

            System.out.println("Enter new application ratings:");
            double ratings = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter new application genre:");
            String genre = scanner.nextLine();

            // Create a new Application object with the updated details
            Application updatedApplication = new Application(app_id, name, description, releaseDate, version, ratings, genre);

            // Update the application in the application table
            applicationController.updateApplication(updatedApplication);
        }
    }

    // Function to delete an application by ID
    private static void deleteApplicationbyID(ApplicationController applicationController, Scanner scanner) {
        System.out.println("Enter application ID:");
        int app_id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Get the application details by ID
        Application application = applicationController.getApplicationById(app_id);

        if (application == null) {
            System.out.println("No application found with the ID: " + app_id);
        } else {
            // Delete the application from the application table
            applicationController.deleteApplication(app_id);
        }
    }

}
