/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBProvider;

/**
 *
 * @author tranh
 */
public class ProductsDAO {

    Connection connection = DBProvider.getConnection();

    public int create(ProductsDTO cat) {
        boolean result = false;
        int id = 0;

        try {
            String sql = "INSERT INTO products(name, description, price,image,categoryid,id) VALUES(?,?,?,?,?,NULL)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cat.getName());
            pst.setString(2, cat.getDescription());
            pst.setFloat(3, cat.getPrice());
            pst.setString(4, cat.getImage());
            pst.setInt(5, cat.getCategoryId());

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

    public ProductsDTO getDetailById(int id) {
        ProductsDTO cat = new ProductsDTO();
        try {
            String sql = "SELECT * FROM products where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                cat.setId(rst.getInt("id"));
                cat.setName(rst.getString("name"));
                cat.setDescription(rst.getString("description"));
                cat.setPrice(rst.getFloat("price"));
                cat.setImage(rst.getString("image"));
                cat.setCategoryId(rst.getInt("categoryId"));

                return cat;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductsDTO> getListCat(int idCat) {
        List<ProductsDTO> List = new ArrayList<ProductsDTO>();
        try {
            String sql = "SELECT * FROM products where categoryid =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, idCat);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductsDTO product = new ProductsDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4), rst.getString(5), rst.getInt(6));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductsDTO> searchByName(String name) {
        List<ProductsDTO> List = new ArrayList<ProductsDTO>();
        try {
            String sql = "SELECT * FROM products where name =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductsDTO product = new ProductsDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4), rst.getString(5), rst.getInt(6));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductsDTO> getList() {
        List<ProductsDTO> List = new ArrayList<ProductsDTO>();
        try {
            String sql = "SELECT * FROM products";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductsDTO product = new ProductsDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getFloat(4), rst.getString(5), rst.getInt(6));
                List.add(product);
            }
            return List;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(ProductsDTO cat) {
        boolean result = false;
        try {
            String sql = "UPDATE products set name =?, description =?,price=?, image=?, categoryid=? where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cat.getName());
            pst.setString(2, cat.getDescription());
            pst.setFloat(3, cat.getPrice());
            pst.setString(4, cat.getImage());
            pst.setInt(5, cat.getCategoryId());
            pst.setInt(6, cat.getId());

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
            String sql = "delete from products where id =?";
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
