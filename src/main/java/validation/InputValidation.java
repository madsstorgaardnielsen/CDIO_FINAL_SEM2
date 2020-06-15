package validation;

import dto.*;

import java.text.DecimalFormat;

public class InputValidation {
    private static InputValidation instance;

    static {
        try {
            instance = new InputValidation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputValidation() {
    }

    public static InputValidation getInstance() {
        return instance;
    }

    public boolean userValidation(UserDTO user, String role) {
        //TODO: further validation
        if (!user.isActive())
            return false;
        if (!user.getRole().equals(role)) {
            return false;
        }
        return true;
    }

    public boolean ingredientInputValidation(IngredientDTO ingredientDTO) {
        int id = ingredientDTO.getIngredientID();

        String name = ingredientDTO.getIngredientName();

        if (id < 1 || id > 99999999) {
            return false;
        } else if (name.length() < 1 || name.length() > 20) {
            return false;
        } else
            return true;
    }

    public boolean ingredientBatchInputValidation(IngredientBatchDTO ingredientBatchDTO) {
        int batchId = ingredientBatchDTO.getIngredientBatchId();
        int ingredientId = ingredientBatchDTO.getIngredientId();
        double amount = ingredientBatchDTO.getAmount();
        String doubleToText = Double.toString(Math.abs(amount));
        int integerLength = doubleToText.indexOf('.');
        int decimalLength = doubleToText.length()-integerLength-1;


        if (batchId < 1 || batchId > 99999999) {
            return false;
        } else if (ingredientId < 1 || ingredientId > 99999999) {
            return false;
        } else if (decimalLength != 4) {
            return false;
        }
        return true;
    }

    public boolean recipeInputValidation(RecipeDTO recipeDTO) {
        int ID = recipeDTO.getRecipeID();
        String recipeName = recipeDTO.getRecipeName();


        if (ID < 1 || ID > 99999999) {
            return false;
        } else if (recipeName.length() < 2 || recipeName.length() > 20) {
            return false;
        } else {
            return true;
        }
    }

    //TODO
    public boolean recipeComponentInputValidation(RecipeComponentDTO recipeDTO) {
        return false;
    }

    public boolean addUserInputValidation(UserDTO userDTO) {
        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();
        String initials = userDTO.getInitials();
        String role = userDTO.getRole();

        if (firstName.length() < 2 || firstName.length() > 20) {
            return false;
        } else if (lastName.length() < 2 || lastName.length() > 20) {
            return false;
        } else if (initials.length() < 2 || initials.length() > 4) {
            return false;
        } else
            return role.equals("Admin") || role.equals("Laborant") || role.equals("Farmaceut") || role.equals("Produktionsleder");
    }

    public boolean productBatchInputValidation(ProductBatchDTO productBatchDTO) {
        int productBatchId = productBatchDTO.getProductBatchId();
        int recipeId = productBatchDTO.getRecipeId();
        int status = productBatchDTO.getStatus();
        int userId = productBatchDTO.getUserId();
        int ingredientBatchId = productBatchDTO.getIngredientBatchId();
        double tara = productBatchDTO.getTaraSum();
        double netto = productBatchDTO.getNettoSum();

        String doubleToTextTara = Double.toString(Math.abs(tara));
        int integerLengthTara = doubleToTextTara.indexOf('.');
        int decimalLengthTara = doubleToTextTara.length()-integerLengthTara-1;

        String doubleToTextNetto = Double.toString(Math.abs(netto));
        int integerLengthNetto = doubleToTextNetto.indexOf('.');
        int decimalLengthNetto = doubleToTextNetto.length()-integerLengthNetto-1;


        if (productBatchId < 1 || productBatchId > 99999999) {
            return false;
        } else if (recipeId < 1 || recipeId > 99999999) {
            return false;
        } else if (status < 1 || status > 3) {
            return false;
        } else if (userId < 1 || userId > 99999999) {
            return false;
        } else if (ingredientBatchId < 1 || ingredientBatchId > 99999999) {
            return false;
        } else if (decimalLengthNetto != 4) {
            return false;
        } else if (decimalLengthTara != 4) {
            return false;
        }
        return true;
    }

    public boolean updateUserInputValidation(UserDTO userDTO) {
        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();
        String initials = userDTO.getInitials();
        String role = userDTO.getRole();

        if (!firstName.equals("null")) {
            if (firstName.length() < 2 || firstName.length() > 20)
                return false;
        }

        if (!lastName.equals("null")) {
            if (lastName.length() < 2 || lastName.length() > 20)
                return false;
        }

        if (!initials.equals("null")) {
            if (initials.length() < 2 || initials.length() > 4)
                return false;
        }

        if (!role.equals("null")) {
            return role.equals("Admin") || role.equals("Laborant") || role.equals("Farmaceut") || role.equals("Produktionsleder");
        }
        return true;
    }
}
