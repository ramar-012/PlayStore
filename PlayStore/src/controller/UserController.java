package controller;

import exceptions.exceptions;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController() {
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }

    //register a new user
    public void registerUser(String username, String password, String role){
        userService.registerUser(username, password, role);
        //System.out.println("User registered successfully!");
    }

    //Authenticate user login
    public boolean loginUser(String username, String password){
        return userService.loginUser(username, password);
    }

    //get user role
    public String getUserRole(String username) throws exceptions.UserNotFoundException {
        return userService.getUserRole(username);
    }

    //region: extra methods
    //Update user details
    public void updateUserDetails(){
        System.out.println("Updating user details");
        System.out.println("Enter user_id: ");
        int user_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter role: ");
        String role = scanner.nextLine();

        userService.updateUserDetails(user_id, username, password, role);
        System.out.println("User details updated successfully!");
    }

    //delete a user
    public void deleteUser(){
        System.out.println("Deleting a user by user_id");
        System.out.println("Enter user_id: ");
        int user_id = Integer.parseInt(scanner.nextLine());

        userService.deleteUser(user_id);
        System.out.println("User deleted successfully!");
    }

    //display all users
    public void displayAllUsers(){
        System.out.println("Displaying all users");
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No users found!");
        } else {
            for(User user : users){
                System.out.println(user);
            }
        }
    }
    //end region
}
