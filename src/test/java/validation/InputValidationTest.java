package validation;

import dto.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {

    @Test
    void ingredientInputValidation() {
        InputValidation iv = new InputValidation();
        IngredientDTO ingredientDTO = new IngredientDTO();

        ingredientDTO.setIngredientID(1);
        ingredientDTO.setIngredientName("aaa");
        ingredientDTO.setIngredientSupplier("aaa");
        assertTrue(iv.ingredientInputValidation(ingredientDTO));

        ingredientDTO.setIngredientID(-1);
        ingredientDTO.setIngredientSupplier("a");
        ingredientDTO.setIngredientName("a");
        assertFalse(iv.ingredientInputValidation(ingredientDTO));

        ingredientDTO.setIngredientSupplier("aaaaaaaaaaaaaaaaaaaaa");
        ingredientDTO.setIngredientID(100000000);
        ingredientDTO.setIngredientName("aaaaaaaaaaaaaaaaaaaa");
        assertFalse(iv.ingredientInputValidation(ingredientDTO));

    }

    @Test
    void ingredientBatchInputValidation() {
        InputValidation iv = new InputValidation();
        IngredientBatchDTO ingredientBatchDTO = new IngredientBatchDTO();
        ingredientBatchDTO.setIngredientBatchId(2);
        ingredientBatchDTO.setIngredientId(2);
        ingredientBatchDTO.setAmount(5.2222);
        assertTrue(iv.ingredientBatchInputValidation(ingredientBatchDTO));

        ingredientBatchDTO.setIngredientBatchId(-1);
        ingredientBatchDTO.setIngredientId(-1);
        ingredientBatchDTO.setAmount(2.333);
        assertFalse(iv.ingredientBatchInputValidation(ingredientBatchDTO));

        ingredientBatchDTO.setIngredientBatchId(100000000);
        ingredientBatchDTO.setIngredientId(100000000);
        ingredientBatchDTO.setAmount(5);
        assertFalse(iv.ingredientBatchInputValidation(ingredientBatchDTO));


    }

    @Test
    void addUserInputValidation() {
        InputValidation iv = new InputValidation();
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("aa");
        userDTO.setLastName("aa");
        userDTO.setInitials("aa");
        userDTO.setRole("Admin");
        iv.addUserInputValidation(userDTO);

        userDTO.setFirstName("a");
        userDTO.setLastName("a");
        userDTO.setInitials("a");
        userDTO.setRole("a");
        iv.addUserInputValidation(userDTO);

        userDTO.setFirstName("aaaaaaaaaaaaaaaaaaaaa");
        userDTO.setLastName("aaaaaaaaaaaaaaaaaaaaa");
        userDTO.setInitials("aaaaa");
        userDTO.setRole("a");
        iv.addUserInputValidation(userDTO);

    }

    @Test
    void productBatchInputValidation() {
        InputValidation iv = new InputValidation();
        ProductBatchDTO pbd = new ProductBatchDTO();
        ProductBatchComponentDTO pbcd = new ProductBatchComponentDTO();

        pbd.setProductBatchId(1);
        pbd.setIngredientBatchId(1);
        pbd.setRecipeId(1);
        pbd.setUserId(1);
        pbd.setStatus(1);
        pbcd.setTara(4.4444);
        pbcd.setNetto(4.4444);
        iv.productBatchInputValidation(pbd);

        pbd.setProductBatchId(-1);
        pbd.setIngredientBatchId(-1);
        pbd.setRecipeId(-1);
        pbd.setUserId(-1);
        pbd.setStatus(-1);
        pbcd.setTara(5.333);
        pbcd.setNetto(5.333);
        iv.productBatchInputValidation(pbd);

        pbd.setProductBatchId(1000000000);
        pbd.setIngredientBatchId(1000000000);
        pbd.setRecipeId(1000000000);
        pbd.setUserId(1000000000);
        pbd.setStatus(4);
        pbcd.setTara(-4.333);
        pbcd.setNetto(-4.333);
        iv.productBatchInputValidation(pbd);
    }

    @Test
    void recipeInputValidation() {
        InputValidation iv = new InputValidation();
        RecipeDTO rp = new RecipeDTO();

        rp.setRecipeID(5000);
        rp.setRecipeName("skateboard");
        rp.setIngredientID(8);
        rp.setNonNetto(6.0);
        rp.setTolerance(7.0);

        iv.recipeInputValidation(rp);
    }
}