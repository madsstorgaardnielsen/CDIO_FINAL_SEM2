package dao.idao;

import dao.IngredientBatchDAO;
import db.DBConnection;
import dto.IngredientBatchDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientBatchDAO {


    static IngredientBatchDAO getInstance() {
        return null;
    }

    void addIngredientBatch(IngredientBatchDTO ingredientBatch);

    void updateIngredientBatch(IngredientBatchDTO ingredientBatch);

    void deleteIngredientBatch(int id);


    ArrayList<IngredientBatchDTO> getAllIngredientBatch();

    void getIngredientBatchInfo(ResultSet rs, IngredientBatchDTO ingredientBatchDTO);


    IngredientBatchDTO getIngredientBatch(int ID);
}
