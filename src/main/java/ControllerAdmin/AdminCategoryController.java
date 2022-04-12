/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerAdmin;

import DAO.CategoryDAO;
import DTO.CategoryDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GlobalFunc;

/**
 *
 * @author tranh
 */
@WebServlet(name = "AdminCategoryController", urlPatterns = {"/AdminCategoryController"})
public class AdminCategoryController extends HttpServlet {

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
//                try {
                HttpSession session = request.getSession();
                CategoryDAO dao = new CategoryDAO();
                List<CategoryDTO> list = new ArrayList<CategoryDTO>();
                list = dao.getList();
                request.setAttribute("list", list);
                System.out.println("list " + list.size());
                session.setAttribute("view", "Category.jsp");
                request.getRequestDispatcher("Category.jsp").forward(request, response);
//                } catch (SQLException ex)  {
//                    Logger.getLogger(AdminCategoryController.class.getName()).log(Level.SEVERE,null);
//                }
            } else if (method.equals("PUT")) {

                //chuc nang update
                String body = GlobalFunc.parseBody(request);
                Gson g = new Gson();
                CategoryDTO cat = g.fromJson(body, CategoryDTO.class);
                String name = cat.getName();
                String des = cat.getDescription();
                String image = cat.getImage();
                HashMap<String, Object> person
                        = new HashMap<String, Object>();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (!name.equals("") && !des.equals("")) {
                    CategoryDTO dto = new CategoryDTO(name, des, image);
                    CategoryDAO dao = new CategoryDAO();
                    boolean isCreate = dao.update(cat);
                    System.err.println("isCreate" + isCreate);
                    if (isCreate) {
                        person.put("message", "cập nhật thành công");
                        // lay thong tin category vừa tạo
                        CategoryDTO detail = dao.getDetailById(cat.getId());
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

                CategoryDTO cat = g.fromJson(body, CategoryDTO.class);
                int id = cat.getId();

                CategoryDAO dao = new CategoryDAO();
                boolean isCreate = dao.delete(id);
                System.err.println("isDelete" + isCreate);
                if (isCreate) {
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
                CategoryDTO cat = g.fromJson(body, CategoryDTO.class);
                String name = cat.getName();
                String des = cat.getDescription();
                String image = cat.getImage();
                HashMap<String, Object> person
                        = new HashMap<String, Object>();
                if (!name.equals("") && !des.equals("")) {
                    CategoryDTO dto = new CategoryDTO(name, des, image);
                    CategoryDAO dao = new CategoryDAO();
                    int isCreate = dao.create(dto);
                    if (isCreate > 0) {
                        CategoryDTO detail = dao.getDetailById(isCreate);

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
