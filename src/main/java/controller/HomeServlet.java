/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tranh
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String[] sothich = request.getParameterValues("vehicle");

        String like1 = request.getParameter("fav_language");

        String select = request.getParameter("cars");

        String gioithieu = request.getParameter("w3review");

        String birthday = request.getParameter("birthday");
        String user = "admin";
        String pass = "admin";

        request.setAttribute("favorite", sothich);
        request.setAttribute("birthday", birthday);
        request.setAttribute("w3review", gioithieu);
        request.setAttribute("sex", select);
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        request.getRequestDispatcher("result.jsp").forward(request, response);
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
//            if (user.contains(username) && pass.contains(password)) {
//                out.println("dang nhap thanh cong");
//            } else {
//                out.println("dang nhap that bai");
//            }
            out.println("<h1>username: " + username + "<h1/>");
            out.println("<h1>password: " + password + " <h1/>");
            for (String element : sothich) {
                out.println("<h1>so thich: " + element + " <h1/>");
            }
            out.println("<h1>like: " + like1 + "<h1/>");
            out.println("<h1>Giới tính: " + select + "<h1/>");
            out.println("<h1>Giới thiệu: " + gioithieu + "<h1/>");
            out.println("<h1>Ngày sinh: " + birthday + "<h1/>");

            out.println("</body>");
            out.println("</html>");
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
