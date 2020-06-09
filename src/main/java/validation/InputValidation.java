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
        if (!user.getRole().equals(role))
            return false;
        return true;
    }

    public boolean ingredientInputValidation(IngredientDTO ingredientDTO) {
        int id = ingredientDTO.getIngredientID();

        String name = ingredientDTO.getIngredientName();
        String supplier = ingredientDTO.getSupplier();

        if (id < 1 || id > 99999999) {
            return false;
        } else if (name.length() < 1 || name.length() > 20) {
            return false;
        } else return supplier.length() >= 1 && supplier.length() <= 20;
    }

    public boolean ingredientBatchInputValidation(IngredientBatchDTO ingredientBatchDTO) {
        DecimalFormat df = new DecimalFormat("#.0000");
        int batchId = ingredientBatchDTO.getIngredientBatchId();
        int ingredientId = ingredientBatchDTO.getIngredientId();
        double amount = ingredientBatchDTO.getAmount();
        int decimalLength = (df.format(amount).length() - 1);

        if (batchId < 1 || batchId > 99999999) {
            return false;
        } else if (ingredientId <= 1 || ingredientId >= 99999999) {
            return false;
        } else return decimalLength == 4;
    }

/*    public boolean receptInputValidation(RecipeDTO recipeDTO) {

    }*/

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
/*
    public boolean productBatchInputValidation(ProductBatchDTO productBatchDTO) {
        int productBatchId = productBatchDTO.getProductBatchId();
        int recipeId = productBatchDTO.getRecipeId();
        int status = productBatchDTO.getStatus();
        int userId = productBatchDTO.getUserId();
        int ingredientBatchId = productBatchDTO.getIngredientBatchId();
        double tara = productBatchDTO.getTara();
        double netto = productBatchDTO.getNetto();
        DecimalFormat df = new DecimalFormat("#.0000");

        int taraDecimalLength = (df.format(tara).length() - 1);
        int nettoDecimalLength = (df.format(netto).length() - 1);

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
        } else if (taraDecimalLength != 4) {
            return false;
        } else return nettoDecimalLength == 4;
    }
 */
}
