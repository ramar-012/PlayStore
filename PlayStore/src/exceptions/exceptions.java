package exceptions;

public class exceptions {

    //User not found exception
    public static class UserNotFoundException extends Exception{
        public UserNotFoundException(String message){
            super(message);
        }
    }

    //application not found exception
    public static class ApplicationNotFoundException extends Exception{
        public ApplicationNotFoundException(String message){
            super(message);
        }
    }

    //category not found exception
    public static class CategoryNotFoundException extends Exception{
        public CategoryNotFoundException(String message){
            super(message);
        }
    }


    //InvalidInputException
    public static class InvalidInputException extends Exception{
        public InvalidInputException(String message){
            super(message);
        }
    }
}
