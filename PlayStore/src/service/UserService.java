package service;

import dao.UserDAO;
import model.User;

import java.util.List;
public class UserService {
    private final UserDAO userDAO;
    public UserService() {
        this.userDAO = new UserDAO();
    }

    //register a new user
    public boolean registerUser(String username, String password, String role){
        boolean ifCreated = false;
        int maxUserId = userDAO.getMaxUserId();
        User user = new User(maxUserId+1, username, password, role);
        ifCreated = userDAO.createUser(user);
        return ifCreated;
    }

    //Authenticate user login
    public boolean loginUser(String username, String password){
        return userDAO.checkUserCredentials(username, password);
    }

    //get user role
    public String getUserRole(String username){
        return userDAO.getUserRole(username);
    }

    //Update user details
    public void updateUserDetails(int user_id, String username, String password, String role){
        User user = new User(user_id, username, password, role);
        userDAO.updateUser(user);
    }

    //delete a user
    public void deleteUser(int user_id){
        userDAO.deleteUser(user_id);
    }

    //get user by id
    public User getUserById(int user_id){
        return userDAO.getUserById(user_id);
    }

    //get all users
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

}
