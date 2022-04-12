/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CategoryDAO;
import DAO.ProductsDAO;
import DTO.CategoryDTO;
import DTO.ProductsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tranh
 */
@WebServlet(name = "HomePageController", urlPatterns = {"/HomePageController"})
public class HomePageController extends HttpServlet {

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
            ProductsDAO ProDAO = new ProductsDAO();
            CategoryDAO catDAO = new CategoryDAO();

            List<CategoryDTO> listCat = new ArrayList<CategoryDTO>();
            listCat = catDAO.getList();
            request.setAttribute("listCat", listCat);

            String id = request.getParameter("id");
            String idCat = request.getParameter("idCat");
            if (id != null) {
                ProductsDTO detail = ProDAO.getDetailById(Integer.parseInt(id));

                CategoryDTO catDetail = catDAO.getDetailById(detail.getCategoryId());

                request.setAttribute("detail", detail);
                request.setAttribute("catDetail", catDetail);

                request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
                out.print("detail" + detail);
            } else if (idCat != null) {
                List<ProductsDTO> listProCat = ProDAO.getListCat(Integer.parseInt(idCat));
                request.setAttribute("listProCat", listProCat);
                System.out.println("list category " + listProCat);
                request.getRequestDispatcher("ListCategory.jsp").forward(request, response);
            } else {
                List<ProductsDTO> listPro = new ArrayList<ProductsDTO>();
                listPro = ProDAO.getList();
                request.setAttribute("listPro", listPro);

                request.getRequestDispatcher("HomePage.jsp").forward(request, response);
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
        String search = request.getParameter("searchName");

        ProductsDAO ProDAO = new ProductsDAO();
        List<ProductsDTO> listSearch = new ArrayList<ProductsDTO>();
        listSearch = ProDAO.searchByName(search);
        System.out.println("lits search " + listSearch);
        request.setAttribute("listSearch", listSearch);
        request.getRequestDispatcher("Search.jsp").forward(request, response);

//        processRequest(request, response);
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
