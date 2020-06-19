package validation;

import dto.*;

import java.text.DecimalFormat;

public class InputValidation {
    private static InputValidation instance;

    static {
        instance = new InputValidation();
    }

    public InputValidation() {
    }

    public static InputValidation getInstance() {
        return instance;
    }

    public boolean userValidation(UserDTO user, String role) {
        if (!user.isActive())
            return false;

        String userRole = user.getRole();

        if (userRole.equals("Admin"))
            return userRole.equals(role);


        if (userRole.equals("Farmaceut"))
            return role.equals("Farmaceut") || role.equals("Produktionsleder") || role.equals("Laborant");


        if (userRole.equals("Produktionsleder"))
            return role.equals("Produktionsleder") || role.equals("Laborant");


        if (userRole.equals("Laborant"))
            return userRole.equals(role);

        return false;
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
        DecimalFormat fourDecimals = new DecimalFormat("#.0000");
        int batchId = ingredientBatchDTO.getIngredientBatchId();
        int ingredientId = ingredientBatchDTO.getIngredientId();
        String amount = ingredientBatchDTO.getAmount();
        //String doubleToText = Double.toString(Math.abs(amount));
        int integerLength = amount.indexOf('.');
        int decimalLength = amount.length() - integerLength - 1;


        if (batchId < 1 || batchId > 99999999) {
            return false;
        } else if (ingredientId < 1 || ingredientId > 99999999) {
            return false;
        } else if (decimalLength != 4) {
            return false;
        }
        return true;
    }

    public boolean ingredientAmountValidation(String amount) {
        int integerLength = amount.indexOf('.');
        int decimalLength = amount.length() - integerLength - 1;
        return decimalLength == 4;
    }

    public boolean idInputValidation(int batchId) {
        return batchId >= 1 && batchId <= 99999999;
    }

    public boolean ingredientIdValidation(int ingredientId ) {
        return ingredientId >= 1 && ingredientId <= 99999999;
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


    public boolean recipeComponentInputValidation(RecipeComponentDTO recipeDTO) {
        int recipeID = recipeDTO.getRecipeID();
        int ingredientId = recipeDTO.getIngredientID();
        double nonNetto = recipeDTO.getNonNetto();
        double tolerance = recipeDTO.getTolerance();

        String doubleToTextnonNetto = Double.toString(Math.abs(nonNetto));
        int integerLengthnonNetto = doubleToTextnonNetto.indexOf('.');
        int decimalLengthnonNetto = doubleToTextnonNetto.length() - integerLengthnonNetto - 1;

        String doubleToTextTolerance = Double.toString(Math.abs(tolerance));
        int integerLengthTolerance = doubleToTextTolerance.indexOf('.');
        int decimalLengthTolerance = doubleToTextTolerance.length() - integerLengthTolerance - 1;

        if (recipeID < 1 || recipeID > 99999999) {
            return false;
        } else if (ingredientId < 1 || ingredientId > 99999999) {
            return false;
        } else if (decimalLengthnonNetto != 4) {
            return false;
        } else if (decimalLengthTolerance != 4) {
            return false;
        }
        return true;
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
        int recipeId = productBatchDTO.getRecipeId();
        int userId = productBatchDTO.getUserId();

        if (recipeId < 1 || recipeId > 99999999) {
            return false;
        } else if (userId < 1 || userId > 99999999) {
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

    public boolean validateAfvejning2(ProductBatchComponentDTO batchComponentDTO, ProductBatchComponentDTO productBatchComponentDTO) {
        double Netto = batchComponentDTO.getNetto();
        double amount = productBatchComponentDTO.getAmount();
        double tolerance = productBatchComponentDTO.getTolerance() * 0.01;
        double upperbound = amount + (amount * tolerance);
        double lowerbound = amount - (amount * tolerance);
        return Netto <= upperbound && Netto >= lowerbound;
    }

    public boolean validateIngredientBatch(IngredientBatchDTO batch, ProductBatchComponentDTO componentDTO) {
        int ingredient = componentDTO.getIngredientID();
        return ingredient == batch.getIngredientId();
    }
}
