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
@WebServlet(name = "usuario", urlPatterns = {"/info"})
public class usuario extends HttpServlet {

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
            String cedula = request.getParameter("txtcedula");
            String nombre = request.getParameter("txtnombre");
            String apellido = request.getParameter("txtapel");
            String razon = request.getParameter("txtrazon");
            String telefono = request.getParameter("txtfono");
            String celular = request.getParameter("txtcelu");
            String mail = request.getParameter("txtmail");
            String ciudad = request.getParameter("txtciu"); /* combo ciudad */
            System.out.print(ciudad);
            String direccion = request.getParameter("txtdir");
            String referencia = request.getParameter("txtrefe");
            String rol = request.getParameter("txtrol"); /* combo rol */
            System.out.print(" "+rol);
            
            CRUD cr = new CRUD();
            String query = "UPDATE administrador SET nom_admin = '"+nombre+"', apel_admin = '"+apellido+"', razon_admin = '"+razon+"', telf_admin = '"+telefono+"', cel_admin = '"+celular+"', email_admin = '"+mail+"', ciud_admin = '"+ciudad+"', dir_admin = '"+direccion+"', ref_admin = '"+referencia+"', cargo_admin = '"+rol+"' WHERE ced_admin = '"+cedula+"'";
            if(cr.Registrar(query)){ response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+cedula); }
            else { response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+cedula+"&resp=error"); }
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
