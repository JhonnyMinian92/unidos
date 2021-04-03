package org.apache.jsp.ventanas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import clases.Ciudad;
import clases.Persona;
import clases.EmpresaFuncion;
import java.sql.ResultSet;
import controlador.CRUD;

public final class prinadmin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Cooperativa de Transporte \"Unidos\"</title>\n");
      out.write("        <script>\n");
      out.write("            function frmcuenta(){\n");
      out.write("                document.getElementById(\"frmcuenta\").style.display = \"block\";\n");
      out.write("                document.getElementById(\"frmempresa\").style.display = \"none\";\n");
      out.write("            }\n");
      out.write("            function frmempresa(){\n");
      out.write("                document.getElementById(\"frmempresa\").style.display = \"block\";\n");
      out.write("                document.getElementById(\"frmcuenta\").style.display = \"none\";\n");
      out.write("            }\n");
      out.write("            function salir(){\n");
      out.write("                window.location=\"http://localhost:8080/unidos/\";\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/estilos.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"../css/font-awesome.css\">\n");
      out.write("        <script src=\"js/jquery-3.2.1.js\"></script>\n");
      out.write("        <script src=\"js/script.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/administrador.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <section class=\"barra\">\n");
      out.write("            <h1>Sistema de Transporte de Carga Pesada</h1>\n");
      out.write("            ");

                Persona per = new Persona();
                String[] dato = new String[13];
                dato = per.ListarAdministrador(request.getParameter("user"));
            
      out.write("\n");
      out.write("            <h2>Bienvenido ");
      out.print( dato[4] );
      out.write(" </h2>\n");
      out.write("        </section>\n");
      out.write("        <nav class=\"botonera\">\n");
      out.write("            <div>\n");
      out.write("                <button onclick=\"frmcuenta();\">Mi Cuenta</button>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button onclick=\"frmempresa();\">Datos de la Empresa</button>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button>Gestion de Personas</button>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button>Gestion de Vehiculo</button>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button>Gestion de Fletes</button>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button onclick=\"salir();\">Salir</button>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        \n");
      out.write("        <section id=\"frmempresa\" class=\"form_wrap\" style=\"display:none;\">\n");
      out.write("            ");

                EmpresaFuncion emp = new EmpresaFuncion();
                String[] base = new String[10];
                base = emp.ListarEmpresa("SELECT * FROM empresa");
            
      out.write("\n");
      out.write("            \n");
      out.write("            <section class=\"cantact_info\">\n");
      out.write("                <section class=\"info_title\" style=\"padding-top: 20px;\">\n");
      out.write("                    <h2>COOPERATIVA \"UNIDOS\"</h2>\n");
      out.write("                </section>\n");
      out.write("            </section>\n");
      out.write("            \n");
      out.write("            <form method=\"post\" action=\"../empresa\" class=\"form_contact\">\n");
      out.write("                <h2>Datos Generales</h2>\n");
      out.write("                <div class=\"user_info\">\n");
      out.write("                    <label for=\"lbruc\">Ruc </label>\n");
      out.write("                    <input type=\"text\" name=\"txtruc\" value=\"");
      out.print( base[1] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbnombre\">Nombre </label>\n");
      out.write("                    <input type=\"text\" name=\"txtnombre\" value=\"");
      out.print( base[2] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbrazon\">Razon </label>\n");
      out.write("                    <input type=\"text\" name=\"txtrazon\" value=\"");
      out.print( base[3] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbdir\">Direccion </label>\n");
      out.write("                    <textarea name=\"txtdir\">");
      out.print( base[4] );
      out.write("</textarea>\n");
      out.write("                    \n");
      out.write("                    <label for=\"lbfono\">Telefono </label>\n");
      out.write("                    <input type=\"text\" name=\"txtfono\" value=\"");
      out.print( base[5] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbmail\">Email </label>\n");
      out.write("                    <input type=\"email\" name=\"txtmail\" value=\"");
      out.print( base[6] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lblogo\">Logo </label>\n");
      out.write("                    <input type=\"text\" name=\"txtfoto\" value=\"");
      out.print( base[7] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbbanco\">Banco </label>\n");
      out.write("                    <input type=\"text\" name=\"txtbanco\" value=\"");
      out.print( base[9] );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <label for=\"lbcuenta\">Cuenta </label>\n");
      out.write("                    <input type=\"text\" name=\"txtcuenta\" value=\"");
      out.print( base[8] );
      out.write("\">\n");
      out.write("                    \n");
      out.write("                    <input type=\"text\" name=\"user\" style=\"display: none;\" value=\"");
      out.print( request.getParameter("user") );
      out.write("\">\n");
      out.write("\n");
      out.write("                    <input type=\"submit\" value=\"Guardar\" id=\"btnSend\">\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </section>\n");
      out.write("        \n");
      out.write("        <section id=\"frmcuenta\" class=\"form_wrap\" style=\"display: none;\">\n");
      out.write("        <section class=\"cantact_info\">\n");
      out.write("            <section class=\"info_title\">\n");
      out.write("                <span class=\"fa fa-user-circle\"></span>\n");
      out.write("                <h2>Mi Cuenta</h2>\n");
      out.write("            </section>            \n");
      out.write("        </section>\n");
      out.write("            <form method=\"post\" class=\"form_contact\" action=\"../info\">\n");
      out.write("            <h2>Informacion</h2>\n");
      out.write("            <div class=\"user_info\">\n");
      out.write("                <label for=\"lbcedula\">Cedula </label>\n");
      out.write("                <input type=\"text\" name=\"txtcedula\" value=\"");
      out.print( dato[1] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbnombre\">Nombres </label>\n");
      out.write("                <input type=\"text\" name=\"txtnombre\" value=\"");
      out.print( dato[2] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbapel\">Apellidos </label>\n");
      out.write("                <input type=\"text\" name=\"txtapel\" value=\"");
      out.print( dato[3] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbrazon\">Razon </label>\n");
      out.write("                <input type=\"text\" name=\"txtrazon\" value=\"");
      out.print( dato[4] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbfono\">Convencional </label>\n");
      out.write("                <input type=\"text\" name=\"txtfono\" value=\"");
      out.print( dato[5] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbcelu\">Celular </label>\n");
      out.write("                <input type=\"text\" name=\"txtcelu\" value=\"");
      out.print( dato[6] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbmail\">Email </label>\n");
      out.write("                <input type=\"text\" name=\"txtmail\" value=\"");
      out.print( dato[7] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <label for=\"lbciud\">Ciudad </label>\n");
      out.write("                ");

                        Ciudad ciu = new Ciudad();
                        String[][] ciudad = new String[225][2];
                        ciudad = ciu.ListarCiudades();
                
                        String op = "";
                        for(int i=1; i< ciudad.length; i++){
                            if(ciudad[i][0] != null){
                                if(Integer.parseInt(ciudad[i][0]) == Integer.parseInt(dato[8])){
                                    op += "<option value='"+ciudad[i][0]+"' selected>"+ciudad[i][1]+"</option>";
                                } else {
                                            op += "<option value='"+ciudad[i][0]+"'>"+ciudad[i][1]+"</option>";
                                        }
                            }
                        }
                    
      out.write("\n");
      out.write("                <select name=\"txtciu\">\n");
      out.write("                    ");
      out.print( op );
      out.write("\n");
      out.write("                </select>\n");
      out.write("                \n");
      out.write("                <label for=\"lbdire\">Direccion </label>\n");
      out.write("                <textarea name=\"txtdir\">");
      out.print( dato[9] );
      out.write("</textarea>\n");
      out.write("                \n");
      out.write("                <label for=\"lbrefe\">Referencia </label>\n");
      out.write("                <input type=\"text\" name=\"txtrefe\" value=\"");
      out.print( dato[10] );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <input type=\"submit\" value=\"Guardar\" id=\"btnguar\">\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("        </section>   \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
