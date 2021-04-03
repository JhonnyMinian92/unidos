package Servlets;

import clases.Roles;
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
@WebServlet(name = "cliente", urlPatterns = {"/persona"})
public class cliente extends HttpServlet {

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
            String id = request.getParameter("txtid");
            String usuario = request.getParameter("txtusuarioced");
            //datos a guardar
            String cedula = request.getParameter("txtcicli"); 
            String nombres = request.getParameter("txtnomcli");
            String apellidos = request.getParameter("txtapelcli");
            String razon = request.getParameter("txtracli");
            String telefono = request.getParameter("txttfcli");
            String celular = request.getParameter("txtcecli");
            String mail = request.getParameter("txtmailcli");
            String ciudad = request.getParameter("txtciucli"); /* combo ciudad */
            System.out.println("ciudad: "+ciudad);
            String direccion = request.getParameter("txtdircli");
            String referencia = request.getParameter("txtrefcli");
            String oficio = request.getParameter("txttracli");
            String iva = request.getParameter("txtivacli");
            String cuenta = request.getParameter("txtcuencli");
            String banco = request.getParameter("txtbancli");
            String clave = request.getParameter("txtclavecli");
            String foto = request.getParameter("txfotocli");
            
            String query = "";
            String userquery = "";
            
            //buscar id de roles
            Roles ro = new Roles();
            String[][] cargos = new String[20][2];
            int idrol = 0;
            cargos = ro.ListarRoles();
            
            if(Integer.parseInt(id) == 1){ 
                query = "INSERT INTO cliente(ced_cliente,nom_cliente,apel_cliente,razon_cliente,telf_cliente,cel_cliente,email_cliente,ciudad_cliente,direc_cliente,ref_cliente,trab_cliente,iva_cliente,estado_cliente, cuenta_cliente, banco_cliente) VALUES('"+cedula+"','"+nombres+"','"+apellidos+"','"+razon+"','"+telefono+"','"+celular+"','"+mail+"','"+ciudad+"','"+direccion+"','"+referencia+"','"+oficio+"','"+iva+"','1','"+cuenta+"','"+banco+"')"; 
                for(int i=1; i< cargos.length; i++){
                    if(cargos[i][0] != null && cargos[i][1].equals("Cliente")){
                            idrol = Integer.parseInt(cargos[i][0]);
                    }
                }
                userquery = "INSERT INTO usuario(ced_usuario,clave_usuario,id_rol,foto_usuario) VALUES('"+cedula+"','"+clave+"','"+idrol+"','"+foto+"')";
            }
            
            CRUD cr = new CRUD();
            if(cr.Registrar(query)){ 
                CRUD cm = new CRUD();
                if(cm.Registrar(userquery)){
                    response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+usuario);
                }
                else { response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+usuario+"&resp=error"); }
            }
            else { response.sendRedirect("/unidos/ventanas/prinadmin.jsp?user="+usuario+"&resp=error"); }
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
