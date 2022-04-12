/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerAdmin;

import DAO.CategoryDAO;
import DAO.ProductsDAO;
import DAO.UserDAO;
import DTO.CategoryDTO;
import DTO.ProductsDTO;
import DTO.UserDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GlobalFunc;
import utils.PasswordHelper;

/**
 *
 * @author tranh
 */
@WebServlet(name = "AdminUsersController", urlPatterns = {"/AdminUsersController"})
public class AdminUsersController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String method = request.getMethod();
            System.out.println("method " + method);
            if (method.equals("GET")) {

                HttpSession session = request.getSession();
                UserDAO userDAO = new UserDAO();

                List<UserDTO> usersList = new ArrayList<UserDTO>();

                usersList = userDAO.getList();

                request.setAttribute("usersList", usersList);
                System.out.println("userlist" + usersList);
                session.setAttribute("view", "Products.jsp");
                request.getRequestDispatcher("Users.jsp").forward(request, response);

            } else if (method.equals("PUT")) {

                //chuc nang update
                String body = GlobalFunc.parseBody(request);
                Gson g = new Gson();
                UserDTO users = g.fromJson(body, UserDTO.class);
                String pass = PasswordHelper.encrypt(users.getPassword());
                String user = users.getUsername();
                System.out.println("pass " + pass);

                String email = users.getEmail();
                String role = users.getRole();

                HashMap<String, Object> person
                        = new HashMap<String, Object>();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (!user.equals("") && !pass.equals("")) {
                    UserDTO dto = new UserDTO(user, pass, email, role);
                    System.out.println("dto "+dto);
                    UserDAO dao = new UserDAO();
                    boolean isCreate = dao.update(users);
                    System.err.println("isCreate" + isCreate);
                    if (isCreate) {
                        person.put("message", "cập nhật thành công");
                        // lay thong tin category vừa tạo
                        UserDTO detail = dao.getDetailById(users.getId());
                        person.put("data", detail);
                        String json = new Gson().toJson(person);
                        response.getWriter().write(json);
                        return;
                    }

                    String json = new Gson().toJson(person);
                    response.getWriter().write(json);
                    return;
                }

            } else if (method.equals("DELETE")) {
                System.out.println("Delete");
                // chuc nang delete
                String body = GlobalFunc.parseBody(request);
                Gson g = new Gson();
                HashMap<String, Object> person = new HashMap<String, Object>();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                UserDTO users = g.fromJson(body, UserDTO.class);
                int id = users.getId();

                UserDAO dao = new UserDAO();
                boolean isDelete = dao.delete(id);
                System.err.println("isDelete" + isDelete);
                if (isDelete) {
                    person.put("message", "Delete thành công");
                    // lay thong tin category vừa tạo
                    String json = new Gson().toJson(person);
                    person.put("message", "Delete thành công");
                    response.getWriter().write(json);
                    return;
                }

                String json = new Gson().toJson(person);
                response.getWriter().write(json);
                return;

            } else {
                String body = GlobalFunc.parseBody(request);
                Gson g = new Gson();
                UserDTO users = g.fromJson(body, UserDTO.class);
                String user = users.getUsername();
                String pass = users.getPassword();
                String email = users.getEmail();
                String role = users.getRole();
                String password_hard = PasswordHelper.encrypt(pass);
                HashMap<String, Object> person
                        = new HashMap<String, Object>();

                if (!user.equals("") && !pass.equals("")) {
                    UserDTO dto = new UserDTO(user, password_hard, email, role);
                    UserDAO dao = new UserDAO();
                    int isCreate = dao.create(dto);
                    if (isCreate > 0) {
                        UserDTO detail = dao.getDetailById(isCreate);

                        person.put("message", "tạo thành công");
                        person.put("data", detail);

                        String json = new Gson().toJson(person);
                        response.getWriter().write(json);
                    }
                    person.put("message", "tạo thành công");
                    String json = new Gson().toJson(person);
                    response.getWriter().write(json);
                } else {
                    person.put("message", "invalid data");
                    response.setStatus(400);
                    String json = new Gson().toJson(person);
                    response.getWriter().write(json);

                }

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
