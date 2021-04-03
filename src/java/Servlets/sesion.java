/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controlador.CRUD;
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
@WebServlet(name = "sesion", urlPatterns = {"/login"})
public class sesion extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* Inicio */
            
            String usuario = request.getParameter("txt_usuario");
            String clave = request.getParameter("txt_clave");
            CRUD cr = new CRUD();
            String rol = cr.Autenticacion(usuario, clave);
            if(rol != ""){
                if(rol.equals("Administrador")){
                    response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+usuario);
                }
                if(rol.equals("Cliente")){
                    response.sendRedirect("/unidos/ventanas/princliente.jsp?user="+usuario);
                }
                if(rol.equals("Secretaria")){
                    response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+usuario);
                }
                if(rol.equals("Chofer")){
                    response.sendRedirect("/unidos/ventanas/princhofer.jsp?user="+usuario);
                }
            } else { response.sendRedirect("index.jsp?respuesta=error");}
            
            /* Fin */
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
