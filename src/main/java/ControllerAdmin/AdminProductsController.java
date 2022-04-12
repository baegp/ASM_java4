/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerAdmin;

import DAO.CategoryDAO;
import DAO.ProductsDAO;
import DTO.CategoryDTO;
import DTO.ProductsDTO;
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

/**
 *
 * @author tranh
 */
@WebServlet(name = "AdminProductsController", urlPatterns = {"/AdminProductsController"})

public class AdminProductsController extends HttpServlet {

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

            /* TODO output your page here. You may use following sample code. */
            String method = request.getMethod();
            System.out.println("method " + method);
            if (method.equals("GET")) {

                HttpSession session = request.getSession();
                ProductsDAO dao = new ProductsDAO();
                CategoryDAO catDao = new CategoryDAO();

                List<ProductsDTO> list = new ArrayList<ProductsDTO>();
                List<CategoryDTO> catList = new ArrayList<CategoryDTO>();

                list = dao.getList();
                catList = catDao.getList();

                request.setAttribute("list", list);
                request.setAttribute("catList", catList);

                System.out.println("list " + list.size());
                session.setAttribute("view", "Products.jsp");
                request.getRequestDispatcher("Products.jsp").forward(request, response);

            } else if (method.equals("PUT")) {

                //chuc nang update
                String body = GlobalFunc.parseBody(request);
                Gson g = new Gson();
                ProductsDTO cat = g.fromJson(body, ProductsDTO.class);
                String name = cat.getName();
                String des = cat.getDescription();
                float price = cat.getPrice();

                String image = cat.getImage();
                int idCat = cat.getCategoryId();

                HashMap<String, Object> person
                        = new HashMap<String, Object>();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (!name.equals("") && !des.equals("")) {
                    ProductsDTO dto = new ProductsDTO(name, des, price, image, idCat);
                    ProductsDAO dao = new ProductsDAO();
                    boolean isCreate = dao.update(cat);
                    System.err.println("isCreate" + isCreate);
                    if (isCreate) {
                        person.put("message", "cập nhật thành công");
                        // lay thong tin category vừa tạo
                        ProductsDTO detail = dao.getDetailById(cat.getId());
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

                ProductsDTO cat = g.fromJson(body, ProductsDTO.class);
                int id = cat.getId();

                ProductsDAO dao = new ProductsDAO();
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
                ProductsDTO cat = g.fromJson(body, ProductsDTO.class);
                String name = cat.getName();
                String des = cat.getDescription();
                float price = cat.getPrice();
                String image = cat.getImage();
                int idCat = cat.getCategoryId();
                HashMap<String, Object> person
                        = new HashMap<String, Object>();
                if (!name.equals("") && !des.equals("")) {
                    ProductsDTO dto = new ProductsDTO(name, des, price, image, idCat);
                    ProductsDAO dao = new ProductsDAO();
                    int isCreate = dao.create(dto);
                    if (isCreate > 0) {
                        ProductsDTO detail = dao.getDetailById(isCreate);

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
