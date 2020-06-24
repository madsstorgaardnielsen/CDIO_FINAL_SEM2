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

    void addIngredient(IngredientDTO ingredient);

    IngredientDTO updateIngredient(IngredientDTO ingredient);

    void deleteIngredient(int id);

    ArrayList<IngredientDTO> getAllIngredients();

    void getIngredientInfo(ResultSet rs, IngredientDTO ingredientDTO);

    IngredientDTO getIngredient(int ID);
}
