package dao.idao;

import dao.IngredientDAO;
import db.DBConnection;
import dto.IngredientDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IIngredientDAO {


    static IngredientDAO getInstance() {
        return null;
    }

    void addIngredient(IngredientDTO ingredient) throws SQLException, IOException;

    IngredientDTO updateIngredient(IngredientDTO ingredient) throws IOException, SQLException;

    void deleteIngredient(int id) throws IOException, SQLException;

    ArrayList<IngredientDTO> getAllIngredients() throws Exception;

    void getIngredientInfo(ResultSet rs, IngredientDTO ingredientDTO) throws SQLException;

    IngredientDTO getIngredient(int ID) throws Exception;
}
