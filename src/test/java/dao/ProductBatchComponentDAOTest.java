package dao;

import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductBatchComponentDAOTest {

    @Test
    void addComponentsByRecipe() throws SQLException, IOException {
        RecipeDTO test = new RecipeDTO(999,"Test");
        RecipeComponentDTO test1 = new RecipeComponentDTO(1,1,2.2222,3.3333);
        RecipeComponentDTO test2 = new RecipeComponentDTO(1,2,2.2222,3.3333);
        test.addToRecipeCompList(test1);
        test.addToRecipeCompList(test2);

        ProductBatchComponentDAO tester = new ProductBatchComponentDAO();
        tester.addComponentsByRecipe(test,35);
    }
}