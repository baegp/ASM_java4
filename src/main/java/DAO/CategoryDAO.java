/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import utils.DBProvider;

/**
 *
 * @author tranh
 */
public class CategoryDAO {

    Connection connection = DBProvider.getConnection();

    public int create(CategoryDTO cat) {
        boolean result = false;
        int id = 0;

        try {
            String sql = "INSERT INTO category(name, description, image,id) VALUES(?,?,?,NULL)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cat.getName());
            pst.setString(2, cat.getDescription());
            pst.setString(3, cat.getImage());

            if (pst.executeUpdate() > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);

                }
            }
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public CategoryDTO getDetailById(int id) {
        CategoryDTO cat = new CategoryDTO();
        try {
            String sql = "SELECT * FROM category where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                cat.setId(rst.getInt("id"));
                cat.setName(rst.getString("name"));
                cat.setDescription(rst.getString("description"));
                cat.setImage(rst.getString("image"));
                return cat;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CategoryDTO> getList() {
        List<CategoryDTO> ListCat = new ArrayList<CategoryDTO>();
        try {
            String sql = "SELECT * FROM category";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                CategoryDTO category = new CategoryDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4));
                ListCat.add(category);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(CategoryDTO cat) {
        boolean result = false;
        try {
            String sql = "UPDATE category set name =?, description =?, image=? where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cat.getName());
            pst.setString(2, cat.getDescription());
            pst.setString(3, cat.getImage());
            pst.setInt(4, cat.getId());

            int ketqua = pst.executeUpdate();
            if (ketqua > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            String sql = "delete from category where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int ketqua = pst.executeUpdate();
            if (ketqua > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
