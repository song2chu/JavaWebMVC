/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.mvc.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.mvc.dao.ProductDao;
import web.mvc.model.Product;

/**
 *
 * @author Praew
 */
public class EditProduct extends HttpServlet {

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
        // ดึงค่ามาเก็บในตัวแปร
        request.setCharacterEncoding("utf-8"); // set ภาษา
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        
        ProductDao dao = new ProductDao();
        Product p = new Product(id, name, price); // set model
        boolean result = dao.edit(p);
        
        if(result) {
           RequestDispatcher rd = request.getRequestDispatcher("ShowProduct");
           String confirm_alert = "<script> alert('แก้ไขข้อมูลสำเร็จ'); </script>";
           request.setAttribute("confirm_alert", confirm_alert);
           rd.forward(request, response);
        } else {
           RequestDispatcher rd = request.getRequestDispatcher("ShowProduct");
           String confirm_alert = "<script> alert('ไม่สามารถแก้ไขข้อมูลได้'); </script>";
           request.setAttribute("confirm_alert", confirm_alert);
           rd.forward(request, response);
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
