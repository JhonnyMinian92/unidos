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
@WebServlet(name = "datos", urlPatterns = {"/empresa"})
public class datos extends HttpServlet {

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
            /* Inicio. */
            String ruc = request.getParameter("txtruc");
            String nombre = request.getParameter("txtnombre");
            String razon = request.getParameter("txtrazon");
            String direccion = request.getParameter("txtdir");
            String telefono = request.getParameter("txtfono");
            String email = request.getParameter("txtmail");
            String logo = request.getParameter("txtfoto");
            String banco = request.getParameter("txtbanco");
            String cuenta = request.getParameter("txtcuenta");
            String user = request.getParameter("user");

            CRUD cr = new CRUD();
            String query = "UPDATE empresa SET ruc_empresa='"+ruc+"', nom_empresa ='"+nombre+"', razon_empresa='"+razon+"', dir_empresa='"+direccion+"', telf_empresa='"+telefono+"', email_empresa='"+email+"', logo_empresa='"+logo+"', cuenta_empresa='"+cuenta+"', banco_empresa='"+banco+"'";
            if(cr.Registrar(query)){ response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+user); }
            else { response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+user+"&resp=error"); }
        /* Fin. */
            
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
