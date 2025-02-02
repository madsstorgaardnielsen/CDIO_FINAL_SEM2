package resources;

import dto.*;

import java.text.DecimalFormat;

/**
 * A Class used to validate input before it is send to the database, if the input isnt validated, the methods will return false.
 */
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
        int batchId = ingredientBatchDTO.getIngredientBatchId();
        int ingredientId = ingredientBatchDTO.getIngredientId();
        String amount = ingredientBatchDTO.getAmount();
        int integerLength = amount.indexOf('.');
        int decimalLength = amount.length() - integerLength - 1;

        if (batchId < 1 || batchId > 99999999) {
            return false;
        } else if (ingredientId < 1 || ingredientId > 99999999) {
            return false;
        } else if (decimalLength != 4) {
            return false;
        } else
            return nameValidation(ingredientBatchDTO.getSupplier());
    }

    public boolean nameValidation(String input) {
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean decimalValidation(String amount) {
        int integerLength = amount.indexOf('.');
        int decimalLength = amount.length() - integerLength - 1;
        return decimalLength == 4;
    }

/*    public boolean idValidation(int batchId) {
        return batchId >= 1 && batchId <= 99999999;
    }*/

    public boolean idValidation(int id) {
        return id >= 1 && id <= 99999999;
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
        } else if (!nameValidation(firstName)) {
            return false;
        } else if (!nameValidation(lastName)) {
            return false;
        } else if (!nameValidation(initials)) {
            return false;
        } else if (!(role.equals("Admin") || role.equals("Laborant") || role.equals("Farmaceut") || role.equals("Produktionsleder"))) {
            return false;
        }
        return true;
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
        double Netto = Double.parseDouble(batchComponentDTO.getNetto());
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
