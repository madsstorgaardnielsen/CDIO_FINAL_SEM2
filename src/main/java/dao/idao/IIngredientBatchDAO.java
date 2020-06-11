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

    void addIngredientBatch(IngredientBatchDTO ingredientBatch) throws SQLException, IOException;

    void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws IOException, SQLException;

    void deleteIngredientBatch(int id) throws IOException, SQLException;


    ArrayList<IngredientBatchDTO> getAllIngredientBatch() throws Exception;

    void getIngredientBatchInfo(ResultSet rs, IngredientBatchDTO ingredientBatchDTO) throws SQLException;


    IngredientBatchDTO getIngredientBatch(int ID) throws Exception;
}
