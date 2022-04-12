/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class UserDAO {

    Connection connection = DBProvider.getConnection();

    public boolean createUS(UserDTO user) {
        boolean result = false;
        try {

            String sql = "INSERT INTO users(username, password, email,role,id) VALUES(?,?,?,?,NULL)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getRole());
            int ketqua = pst.executeUpdate();
            if (ketqua > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public int create(UserDTO users) {
        boolean result = false;
        int id = 0;

        try {
            String sql = "INSERT INTO users(username, password, email,role,id) VALUES(?,?,?,?,NULL)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, users.getUsername());
            pst.setString(2, users.getPassword());
            pst.setString(3, users.getEmail());
            pst.setString(4, users.getRole());

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

    public boolean isUserExist(String username) {
        boolean result = false;
        try {

            String sql = "SELECT * FROM users where username =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public String checkLogin(String username, String password) {

        try {

            String sql = "SELECT * FROM users where username =? and password= ?  ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                return rst.getString(5);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "false";

    }

    public List<UserDTO> getList() {
        List<UserDTO> ListUsers = new ArrayList<UserDTO>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                UserDTO category = new UserDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
                ListUsers.add(category);
            }
            return ListUsers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(UserDTO users) {
        boolean result = false;
        try {
            String sql = "UPDATE users set username =?, password =?,email=?, role=? where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, users.getUsername());
            pst.setString(2, users.getPassword());
            pst.setString(3, users.getEmail());
            pst.setString(4, users.getRole());
            pst.setInt(5, users.getId());

            int ketqua = pst.executeUpdate();
            if (ketqua > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public UserDTO getDetailById(int id) {
        UserDTO users = new UserDTO();
        try {
            String sql = "SELECT * FROM users where id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                users.setId(rst.getInt("id"));
                users.setUsername(rst.getString("username"));
                users.setPassword(rst.getString("password"));
                users.setEmail(rst.getString("email"));
                users.setRole(rst.getString("role"));

                return users;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
        public boolean delete(int id) {
        boolean result = false;
        try {
            String sql = "delete from users where id =?";
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
