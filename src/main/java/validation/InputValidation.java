package validation;

import dto.UserDTO;

public class InputValidation {
    private  static InputValidation instance;

    static {
        try {
            instance = new InputValidation();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public InputValidation(){
    }

    public static InputValidation getInstance(){
        return instance;
    }

    public boolean userValidation(UserDTO user, String role){
        //TODO: further validation
        if (!user.isActive())
            return false;
        if (!user.getRole().equals(role))
            return false;
        return true;
    }
}
