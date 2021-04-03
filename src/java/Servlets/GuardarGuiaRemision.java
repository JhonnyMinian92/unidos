/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import clases.Vehiculo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lakeo
 */
@WebServlet(name = "GuardarGuiaRemision", urlPatterns = {"/guguia"})
public class GuardarGuiaRemision extends HttpServlet {

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
        
        String fecha = request.getParameter("fecha");
        String destino = request.getParameter("destino");
        String persona = request.getParameter("persona");
        String nombre = request.getParameter("nombre");
        int zona = Integer.parseInt(request.getParameter("zona"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int peso = Integer.parseInt(request.getParameter("peso"));
        int tamano = Integer.parseInt(request.getParameter("tamano"));
        int motivo = Integer.parseInt(request.getParameter("motivo"));
        String ruc = request.getParameter("ruc");
        String cliente = request.getParameter("cliente");
        String placa = request.getParameter("placa");
        String chofer = request.getParameter("chofer");
        String total = request.getParameter("total");
        
        int id = new Vehiculo().GuardarGuiaRemision(fecha, destino, persona, nombre, zona, cantidad, peso, tamano, motivo, ruc, cliente, placa, chofer, total);
        String datos = ""+id+"";
        String json = new Gson().toJson(datos);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
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
