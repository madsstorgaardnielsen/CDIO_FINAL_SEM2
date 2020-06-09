package validation;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchDTO;
import dto.UserDTO;
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
        ingredientBatchDTO.setAmountInStock(5.2222);
        assertTrue(iv.ingredientBatchInputValidation(ingredientBatchDTO));

        ingredientBatchDTO.setIngredientBatchId(-1);
        ingredientBatchDTO.setIngredientId(-1);
        ingredientBatchDTO.setAmountInStock(2.333);
        assertFalse(iv.ingredientBatchInputValidation(ingredientBatchDTO));

        ingredientBatchDTO.setIngredientBatchId(100000000);
        ingredientBatchDTO.setIngredientId(100000000);
        ingredientBatchDTO.setAmountInStock(5);
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

        pbd.setProductBatchId(1);
        pbd.setIngredientBatchId(1);
        pbd.setRecipeId(1);
        pbd.setUserId(1);
        pbd.setStatus(1);
        pbd.setTara(4.4444);
        pbd.setNetto(4.4444);
        iv.productBatchInputValidation(pbd);

        pbd.setProductBatchId(-1);
        pbd.setIngredientBatchId(-1);
        pbd.setRecipeId(-1);
        pbd.setUserId(-1);
        pbd.setStatus(-1);
        pbd.setTara(5.333);
        pbd.setNetto(5.333);
        iv.productBatchInputValidation(pbd);

        pbd.setProductBatchId(1000000000);
        pbd.setIngredientBatchId(1000000000);
        pbd.setRecipeId(1000000000);
        pbd.setUserId(1000000000);
        pbd.setStatus(4);
        pbd.setTara(-4.333);
        pbd.setNetto(-4.333);
        iv.productBatchInputValidation(pbd);
    }
}