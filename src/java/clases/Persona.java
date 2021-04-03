package clases;

import controlador.CRUD;
import controlador.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.omg.CORBA.INTERNAL;

/**
 *
 * @author lakeo
 */
public class Persona {
    
        public String[] ListarAdministrador(String cedula){
            String consul = "SELECT * FROM administrador WHERE ced_admin='"+cedula+"'";
            conexion con = new conexion();
            PreparedStatement pst = null;
            ResultSet rs = null;
            String[] datos = new String[13];
            try {
                pst = con.getConnection().prepareStatement(consul);
                rs = pst.executeQuery();
                while(rs.next()){ 
                    datos[1] = rs.getString(1);
                    datos[2] = rs.getString(2);
                    datos[3] = rs.getString(3);
                    datos[4] = rs.getString(4);
                    datos[5] = rs.getString(5);
                    datos[6] = rs.getString(6);
                    datos[7] = rs.getString(7);
                    datos[8] = rs.getString(8);
                    datos[9] = rs.getString(9);
                    datos[10] = rs.getString(10);
                    datos[11] = rs.getString(11);
                    datos[12] = rs.getString(12);
                    return datos;
                }
            } catch (Exception e) {
                System.err.println("Error"+e);
            } finally {
                try {
                    if(con.getConnection() != null) con.getConnection().close();
                    if(pst != null) pst.close();
                    if(rs != null) rs.close();
                } catch (Exception e) {
                    System.err.println("Error"+e);
                }
            }
            return null;
    }
        
    public String[][] ListarClientes(){
        String[][] client = new String[200][19];
        String query2 = "SELECT * FROM cliente, ciudad WHERE ciudad.id_ciudad = cliente.ciudad_cliente";
        conexion con2 = new conexion();
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        try {
            pst2 = con2.getConnection().prepareStatement(query2);
            rs2 = pst2.executeQuery();
            int j = 1;
            while(rs2.next()){
                client[j][0] = rs2.getString(1);
                client[j][1] = rs2.getString(2);
                client[j][2] = rs2.getString(3);
                client[j][3] = rs2.getString(4);
                client[j][4] = rs2.getString(5);
                client[j][5] = rs2.getString(6);
                client[j][6] = rs2.getString(7);
                client[j][7] = rs2.getString(8);
                client[j][8] = rs2.getString(9);
                client[j][9] = rs2.getString(10);
                client[j][10] = rs2.getString(11);
                client[j][11] = rs2.getString(12);
                client[j][12] = rs2.getString(13);
                client[j][13] = rs2.getString(14);
                client[j][14] = rs2.getString(15);
                client[j][15] = rs2.getString(16);
                client[j][16] = rs2.getString(17);
                client[j][17] = rs2.getString(18);
                j++;
            }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return client;
    }
    
    public ArrayList<String> BuscarCliente(String cedula){
        String query3 = "SELECT * FROM cliente, ciudad WHERE cliente.ced_cliente = '"+cedula+"' AND cliente.ciudad_cliente = ciudad.id_ciudad";
        ArrayList<String> datos = new ArrayList<>();
        conexion con3 = new conexion();
        PreparedStatement pst3 = null;
        ResultSet rs3 = null;
        try {
              pst3 = con3.getConnection().prepareStatement(query3);
              rs3 = pst3.executeQuery();
              while(rs3.next()){
                  datos.add(rs3.getString("nom_cliente"));
                  datos.add(rs3.getString("apel_cliente"));
                  datos.add(rs3.getString("razon_cliente"));
                  datos.add(rs3.getString("telf_cliente"));
                  datos.add(rs3.getString("cel_cliente"));
                  datos.add(rs3.getString("email_cliente"));
                  datos.add(rs3.getString("nom_ciudad"));
                  datos.add(rs3.getString("direc_cliente"));
                  datos.add(rs3.getString("ref_cliente"));
                  datos.add(rs3.getString("trab_cliente"));
                  datos.add(rs3.getString("iva_cliente"));
                  datos.add(rs3.getString("estado_cliente"));
                  datos.add(rs3.getString("cuenta_cliente"));
                  datos.add(rs3.getString("banco_cliente"));
              }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return datos;
    }
    
    public String EliminarPersona(String cedula){
        String respuesta = "";
        //conocer si tiene deuda
        
        String query4 = "UPDATE cliente SET estado_cliente = '0' WHERE ced_cliente = '"+cedula+"'";
        CRUD cr = new CRUD();
        if(cr.Registrar(query4)){ respuesta = "Se Elimino exitosamente"; }
        else { respuesta = "Error al eliminar"; }
        
        return respuesta;
    }
    
    public String ModificarPersona(String cedula, String nombres, String apellidos, String razon, String telefono, String celular, String email, String ciudad, String direccion, String referencia,  String oficio, String iva, String cuenta, String banco){
        String respuesta = "";
        String query5 = "UPDATE cliente SET nom_cliente = '"+nombres+"', apel_cliente='"+apellidos+"', razon_cliente='"+razon+"', telf_cliente='"+telefono+"', cel_cliente='"+celular+"', email_cliente='"+email+"', ciudad_cliente='"+ciudad+"', direc_cliente='"+direccion+"', ref_cliente='"+referencia+"', trab_cliente='"+oficio+"', iva_cliente='"+iva+"', cuenta_cliente='"+cuenta+"', banco_cliente='"+banco+"' WHERE ced_cliente ='"+cedula+"'";
        CRUD cr2 = new CRUD();
        if(cr2.Registrar(query5)){ respuesta = "Se Modifico datos exitosamente"; }
        else { respuesta = "Error al modificar"; }
        return respuesta;
    }
    
    public String[] LisPermicliente(String cedula){
        String[] idpermisos = new String[20];
        conexion con6 = new conexion();
        PreparedStatement pst6 = null;
        ResultSet rs6 = null;
        try {
                pst6 = con6.getConnection().prepareStatement("SELECT id_permisos FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '1'");
                rs6 = pst6.executeQuery();
                int m = 1;
                while(rs6.next()){
                    idpermisos[m] = rs6.getString("id_permisos");
                    m++;
                }
        } catch (Exception e) {
        }
        return idpermisos;
    }
    
    public String ReporteChofer(){
        String total = "";
        int numact = 0, inactiv = 0;
        String listaactivos = "<tr class='subtit'><th colspan='7'>Choferes Activos</th></tr><tr class='barralistado'><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Telefono</th><th>Celular</th><th>Ciudad</th><th>Licencia</th></tr>";
        String listainactivos = "<tr class='subtit'><th colspan='7'>Choferes Inactivos</th></tr><tr class='barralistado'><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Telefono</th><th>Celular</th><th>Ciudad</th><th>Licencia</th></tr>";
        String query = "SELECT * FROM chofer, ciudad WHERE chofer.ciudad_chofer = ciudad.id_ciudad";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                p = c.getConnection().prepareStatement(query);
                r = p.executeQuery();
                while(r.next()){
                    if(r.getString("estado_chofer").equals("1")){ numact++; listaactivos += "<tr class='seclistar'><td>"+r.getString("ced_chofer")+"</td><td>"+r.getString("nom_chofer")+"</td><td>"+r.getString("apel_chofer")+"</td><td>"+r.getString("telf_chofer")+"</td><td>"+r.getString("cel_chofer")+"</td><td>"+r.getString("nom_ciudad")+"</td><td>"+r.getString("lic_chofer")+"</td></tr>"; }
                    else { inactiv++; listainactivos += "<tr class='seclistar'><td>"+r.getString("ced_chofer")+"</td><td>"+r.getString("nom_chofer")+"</td><td>"+r.getString("apel_chofer")+"</td><td>"+r.getString("telf_chofer")+"</td><td>"+r.getString("cel_chofer")+"</td><td>"+r.getString("nom_ciudad")+"</td><td>"+r.getString("lic_chofer")+"</td></tr>"; }
                }
                total = listaactivos+listainactivos;
                total += "<tr class='resulrep'><td colspan='6'>Total Chofer Activos</td><td>"+numact+"</td></tr><tr class='resulrep'><td colspan='6'>Total Chofer Inactivos</td><td>"+inactiv+"</td></tr>";
        } catch (Exception e) { System.err.println("Error "+e); }
        return total;
    }
    
    public String GuardaAdmin(String cedula, String nombre, String apellido, String razon, String telefono, String celular, String email, String ciudad, String direccion, String referencia, String fecha, String clave, String imagen){
        String respuesta = "";
        String q = "INSERT INTO administrador(ced_admin, nom_admin, apel_admin, razon_admin, telf_admin, cel_admin, email_admin, ciud_admin, dir_admin, ref_admin,fech_ing_admin, cargo_admin, estado_admin) VALUES('"+cedula+"','"+nombre+"','"+apellido+"','"+razon+"','"+telefono+"','"+celular+"','"+email+"','"+ciudad+"','"+direccion+"','"+referencia+"','"+fecha+"','2','1')";
        CRUD crea = new CRUD();
        if(crea.Registrar(q)){ UsuarioAdmin(cedula, clave, imagen); respuesta = "Se guardo exitosamente"; }
        else { respuesta = "Error al guardar"; }
        return respuesta;
    }
    
    public String ListarAdmin(){
        String resp = "";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                p = c.getConnection().prepareStatement("SELECT * FROM administrador, ciudad WHERE administrador.ciud_admin = ciudad.id_ciudad");
                r = p.executeQuery();
                while(r.next()){
                    resp += "<tr class='secpermiso'><td>"+r.getString("ced_admin")+"</td><td>"+r.getString("nom_admin")+"</td><td>"+r.getString("apel_admin")+"</td><td>"+r.getString("telf_admin")+"</td><td>"+r.getString("cel_admin")+"</td><td>"+r.getString("nom_ciudad")+"</td><td>"+r.getString("fech_ing_admin")+"</td></tr>";
                }
        } catch (Exception e) { System.err.println("Error "+e); }        
        return resp;
    }
    
    public String ListarPropietario(String razon){
        String resp = "";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                p = c.getConnection().prepareStatement("SELECT * FROM propietario WHERE razon_propietario LIKE '%"+razon+"%'");
                r = p.executeQuery();
                while(r.next()){
                    resp += "<tr class='seclistar'><td>"+r.getString("ruc_propietario")+"</td><td>"+r.getString("razon_propietario")+"</td><td>"+r.getString("ganan_propietario")+"</td><td>"+r.getString("dir_propietario")+"</td><td>"+r.getString("telf_propietario")+"</td><td>"+r.getString("cel_propietario")+"</td></tr>";
                }
        } catch (Exception e) { System.err.println("Error "+e); }        
        return resp;
    }
    
    
    public String ListarZonas(String cedula){
        String resp = "<table class='tablapermi'><tr class='barrapermiso'><th>Item</th><th>Nombre</th><th>Ciudad</th><th>Direccion</th><th>Referencia</th></tr>";
        String query = "SELECT * FROM zonacarga, ciudad WHERE zonacarga.ced_cliente = '"+cedula+"' AND zonacarga.ciu_zona = ciudad.id_ciudad";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                int i = 1;
                p = c.getConnection().prepareStatement(query);
                r = p.executeQuery();
                while(r.next()){
                    resp += "<tr class='secpermiso'><td>"+i+"</td><td>"+r.getString("nom_zona")+"</td><td>"+r.getString("nom_ciudad")+"</td><td>"+r.getString("dir_zona")+"</td><td>"+r.getString("ref_zona")+"</td></tr>";
                    i++;
                }
                resp += "</table>";
        } catch (Exception e) { System.err.println("Error "+e); }
        return resp;
    }
    
    public String GuardarZonas(String nombre, String ciudad, String direccion, String referencia, String cedula){
        String respuesta = "";
        String quer = "INSERT INTO zonacarga(nom_zona, ciu_zona, dir_zona, ref_zona, ced_cliente) VALUES('"+nombre+"','"+ciudad+"','"+direccion+"','"+referencia+"','"+cedula+"')";
        CRUD cer = new CRUD();
        if(cer.Registrar(quer)){ respuesta = "Se guardo zona exitosamente"; }
        else { respuesta = "Error al guardar"; }
        return respuesta;
    }
    
    public String[] LisPermiChofer(String cedula){
        String[] idpermisos = new String[20];
        conexion con6 = new conexion();
        PreparedStatement pst6 = null;
        ResultSet rs6 = null;
        try {
                pst6 = con6.getConnection().prepareStatement("SELECT id_permisos FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '4'");
                rs6 = pst6.executeQuery();
                int m = 1;
                while(rs6.next()){
                    idpermisos[m] = rs6.getString("id_permisos");
                    m++;
                }
        } catch (Exception e) { System.err.println("Error "+e); }
        return idpermisos;
    }
    
    public String[] LisPermiAdmin(String cedula){
        String[] idpermisos = new String[20];
        conexion con6 = new conexion();
        PreparedStatement pst6 = null;
        ResultSet rs6 = null;
        try {
                pst6 = con6.getConnection().prepareStatement("SELECT id_permisos FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '2'");
                rs6 = pst6.executeQuery();
                int m = 1;
                while(rs6.next()){
                    idpermisos[m] = rs6.getString("id_permisos");
                    m++;
                }
        } catch (Exception e) { System.err.println("Error "+e); }
        return idpermisos;
    }
    
    public String ListarPermisos(String cedula, int tipo){
        //Obtener rol de la cedula
        Roles r = new Roles();
        String tabla = "";
        if(r.BuscaRol(cedula) == tipo){
                //Listado de permisos
                tabla = "<article class='cjtablapermi'><table class='tablapermi'><tr class='barrapermiso'><th>Item</th><th>Permiso</th></tr>";
                String[][] permiso = new String[20][2];
                conexion con5 = new conexion();
                PreparedStatement pst5 = null;
                ResultSet rs5 = null;
                try {
                        pst5 = con5.getConnection().prepareStatement("SELECT * FROM permisos");
                        rs5 = pst5.executeQuery();               
                        int n = 1;
                        while(rs5.next()){
                            permiso[n][0] = rs5.getString("id_permisos");
                            permiso[n][1] = rs5.getString("tipo_permiso");
                            n++;
                        }
                        //ckeck de permisos asigandos
                        String[] idpermisos = new String[20];
                        if(tipo == 1){ idpermisos = LisPermicliente(cedula); }
                        if(tipo == 4){ idpermisos = LisPermiChofer(cedula); }
                        if(tipo == 2){ idpermisos = LisPermiAdmin(cedula); }
                        boolean bandera = false;
                        for(int i=1; i < permiso.length; i++){
                            for(int j=1; j < idpermisos.length; j++){
                                if(permiso[i][0] != null && idpermisos[j] != null){ 
                                    if(Integer.parseInt(permiso[i][0]) == Integer.parseInt(idpermisos[j])){ bandera = true; } 
                                }
                            }
                            if(permiso[i][0] != null){
                                if(bandera){ tabla += "<tr class='secpermiso'><td><input name='elpermiso' type='checkbox' id='check"+permiso[i][0]+"' value='"+permiso[i][0]+"' checked></td><td>"+permiso[i][1]+"</td></tr>"; }
                                else { tabla += "<tr class='secpermiso'><td><input name='elpermiso' type='checkbox' id='check"+permiso[i][0]+"' value='"+permiso[i][0]+"'></td><td>"+permiso[i][1]+"</td></tr>"; }
                                bandera = false;
                            }
                        }
                        tabla += "</table></article>";
                } catch (Exception e) {
                    System.err.println("Error "+e);
                }
        } else { tabla = "Rol no corresponde ...."; }
        return tabla;
    }
    
    public String GuardarPropietario(String cedula, String razon, String ganancia, String direccion, String telefono, String celular){
        String resp = "";
        String query = "INSERT INTO propietario(ruc_propietario, razon_propietario, ganan_propietario, dir_propietario, telf_propietario, cel_propietario) VALUES('"+cedula+"','"+razon+"','"+ganancia+"','"+direccion+"','"+telefono+"','"+celular+"')";        
        CRUD cru2 = new CRUD();
        if(cru2.Registrar(query)){ resp = "Se guardo Correctamente"; }
        else { resp = "Error al guardar"; }
        return resp;
    }
    
    public String GuardaPermisos(String permiso, String cedula, int tipo){
        String resp = "";
        String querie = "";
        //guardar los permisos
        if(tipo == 1){ querie = "INSERT INTO permisocliente(id_permisos,ced_persona,id_rol) VALUES ('"+permiso+"','"+cedula+"','1')"; }
        if(tipo == 4){ querie = "INSERT INTO permisocliente(id_permisos,ced_persona,id_rol) VALUES ('"+permiso+"','"+cedula+"','4')"; }
        if(tipo == 2){ querie = "INSERT INTO permisocliente(id_permisos,ced_persona,id_rol) VALUES ('"+permiso+"','"+cedula+"','2')"; }
        CRUD cru2 = new CRUD();
        if(cru2.Registrar(querie)){ resp = "Actualizado Correctamente"; }
        else { resp = "Error al Asignar Permisos"; }
        return resp;
    }
    
    public String ActualizarPermisos(String permiso, String cedula, int tipo, int bandera){
        String respuesta = "";
        String queries = "";
        //Eliminar permisos ya existentes
        if(bandera == 1){
            if(tipo == 1) { queries = "DELETE FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '1'"; }
            if(tipo == 4) { queries = "DELETE FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '4'"; }
            if(tipo == 2) { queries = "DELETE FROM permisocliente WHERE ced_persona = '"+cedula+"' AND id_rol = '2'"; }
        }
        CRUD cru = new CRUD();
        if(cru.Registrar(queries)){ respuesta = GuardaPermisos(permiso, cedula, tipo); }
        else { respuesta = GuardaPermisos(permiso, cedula, tipo); }
        return respuesta;
    }
    
    public String VerRazon(String rol, String cedula){
        String razon = ""; String queri = "";
        conexion con8 = new conexion();
        PreparedStatement pst8 = null;
        ResultSet rs8 = null;
        try {
                if(rol.equals("Cliente")){
                    queri = "SELECT razon_cliente FROM cliente WHERE ced_cliente = '"+cedula+"' AND estado_cliente = '0'";
                }
                if(rol.equals("Administrador")){
                    queri = "SELECT razon_admin FROM administrador WHERE ced_admin = '"+cedula+"'";
                }
                if(rol.equals("Chofer")){
                    queri = "SELECT razon_admin FROM administrador WHERE ced_admin = '"+cedula+"'";
                }
                pst8 = con8.getConnection().prepareStatement(queri);
                rs8 = pst8.executeQuery();
                while(rs8.next()){
                    razon = rs8.getString(1);
                }
        } catch (Exception e) { System.err.println("Error "+e); }
        return razon;
    }
    
    public String PresentarPersona(String cedula){
        String razon = "";
        String query = "SELECT roles.nom_rol FROM roles, usuario WHERE usuario.ced_usuario = '"+cedula+"' AND roles.id_rol = usuario.id_rol";
        conexion con7 = new conexion();
        PreparedStatement pst7 = null;
        ResultSet rs7 = null;
        try {
            pst7 = con7.getConnection().prepareStatement(query);
            rs7 = pst7.executeQuery();
            while(rs7.next()){
                razon = rs7.getString(1)+" = "+VerRazon(rs7.getString(1), cedula);
            }
        } catch (Exception e) { System.err.println("Error "+e); }
        return razon;
    }
    
    public String ActivarPersona(String cedula, String rol){
        String respuesta = "";
        String queri = "";
        if(rol.equals("Cliente")){
            queri = "UPDATE cliente SET estado_cliente = '1' WHERE ced_cliente = '"+cedula+"'";
        }
        if(rol.equals("Administrador")){
            queri = "UPDATE administrador SET estado_admin = '1' WHERE ced_admin = '"+cedula+"'";
        }
        if(rol.equals("Chofer")){
            queri = "UPDATE chofer SET estado_chofer = '1' WHERE ced_chofer = '"+cedula+"'";
        }
        CRUD cr10 = new CRUD();
        if(cr10.Registrar(queri)){ respuesta = "Se activo exitosamente"; }
        else { respuesta = "Error al activar"; }
        return respuesta;
    }
    
    public String UsuarioChofer(String cedula, String clave, String foto){
        String r = "";
        //obtener id de usuario
        Roles ro = new Roles();
        String[][] cargos = new String[20][2];
        int idrol = 0;
        cargos = ro.ListarRoles();
        for(int i=1; i< cargos.length; i++){
            if(cargos[i][0] != null && cargos[i][1].equals("Chofer")){
                idrol = Integer.parseInt(cargos[i][0]);
            }
        }
        //guardar usuario
        String userquery = "INSERT INTO usuario(ced_usuario,clave_usuario,id_rol,foto_usuario) VALUES('"+cedula+"','"+clave+"','"+idrol+"','"+foto+"')";
        CRUD cm = new CRUD();
        if(cm.Registrar(userquery)){ r = "Guardado Exitosamente"; }
        else { r = "Error al guardar"; }
        return r;
    }
    
        public String UsuarioAdmin(String cedula, String clave, String foto){
        String r = "";
        //obtener id de usuario
        Roles ro = new Roles();
        String[][] cargos = new String[20][2];
        int idrol = 0;
        cargos = ro.ListarRoles();
        for(int i=1; i< cargos.length; i++){
            if(cargos[i][0] != null && cargos[i][1].equals("Administrador")){
                idrol = Integer.parseInt(cargos[i][0]);
            }
        }
        //guardar usuario
        String userquery = "INSERT INTO usuario(ced_usuario,clave_usuario,id_rol,foto_usuario) VALUES('"+cedula+"','"+clave+"','"+idrol+"','"+foto+"')";
        CRUD cm = new CRUD();
        if(cm.Registrar(userquery)){ r = "Guardado Exitosamente"; }
        else { r = "Error al guardar"; }
        return r;
    }
    
    public String GuardarChofer(String cedula, String nombre, String apellido, String razon, String telefono, String celular, String email, String ciudad, String direccion, String referencia, String fecha, String licencia, String clave, String imagen){
        String resp = "";
        String querie = "INSERT INTO chofer(ced_chofer,nom_chofer,apel_chofer,razon_chofer,telf_chofer,cel_chofer,email_chofer,ciudad_chofer,dir_chofer,ref_chofer,fech_ing_chofer, lic_chofer, estado_chofer) VALUES('"+cedula+"','"+nombre+"','"+apellido+"','"+razon+"','"+telefono+"','"+celular+"','"+email+"','"+ciudad+"','"+direccion+"','"+referencia+"','"+fecha+"','"+licencia+"','1')";
        CRUD c = new CRUD();
        if(c.Registrar(querie)){ resp = "Chofer se guardo correctamente"; UsuarioChofer(cedula, clave, imagen);  }
        else { resp = "Error al guardar"; }
        return resp;
    }
    
    public String ListarChofer(){
        String tabla = "<article class='cjtablapermi'><table class='tablachofer'><tr class='barrapermiso'><th>Cedula</th><th>Nombre</th><th>Apellido</th><th>Telefono</th><th>Celular</th><th>Ciudad</th><th>Licencia</th></tr>";
        conexion co = new conexion();
        PreparedStatement presta = null;
        ResultSet rset = null;
        try {
                presta = co.getConnection().prepareStatement("SELECT * FROM chofer, ciudad WHERE chofer.ciudad_chofer = ciudad.id_ciudad AND estado_chofer = '1'");
                rset = presta.executeQuery();
                while(rset.next()){ 
                    tabla += "<tr class='secchofer'><td>"+rset.getString("ced_chofer")+"</td><td>"+rset.getString("nom_chofer")+"</td><td>"+rset.getString("apel_chofer")+"</td><td>"+rset.getString("telf_chofer")+"</td><td>"+rset.getString("cel_chofer")+"</td><td>"+rset.getString("nom_ciudad")+"</td><td>"+rset.getString("lic_chofer")+"</td></tr>"; 
                }
                tabla += "</table></article>"; 
            } catch (Exception e) {
                System.err.println("Error"+e);
            } finally {
                try {
                    if(co.getConnection() != null) co.getConnection().close();
                    if(presta != null) presta.close();
                    if(rset != null) rset.close();
                } catch (Exception e) {
                    System.err.println("Error"+e);
                }
            }        
        return tabla;
    }
    
    public ArrayList<String> BuscarAdmin(String cedula){
        ArrayList<String> datos = new ArrayList<>();
        String que = "SELECT * FROM administrador WHERE ced_admin= '"+cedula+"'";
        conexion con3 = new conexion();
        PreparedStatement pst3 = null;
        ResultSet rs3 = null;
        try {
              pst3 = con3.getConnection().prepareStatement(que);
              rs3 = pst3.executeQuery();
              while(rs3.next()){
                  datos.add(rs3.getString("nom_admin"));
                  datos.add(rs3.getString("apel_admin"));
                  datos.add(rs3.getString("razon_admin"));
                  datos.add(rs3.getString("telf_admin"));
                  datos.add(rs3.getString("cel_admin"));
                  datos.add(rs3.getString("email_admin"));
                  datos.add(rs3.getString("dir_admin"));
                  datos.add(rs3.getString("ref_admin"));
                  datos.add(rs3.getString("fech_ing_admin"));
                  datos.add(rs3.getString("estado_admin"));
              }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return datos;
    }
    
    public String ModificarAdmin(String cedula, String nombre, String apellido, String razon, String telefono, String celular, String email, String ciudad, String direccion, String referencia, String fecha){
        String resp = "";
        String queries = "UPDATE administrador SET nom_admin='"+nombre+"', apel_admin='"+apellido+"', razon_admin='"+razon+"', telf_admin='"+telefono+"', cel_admin='"+celular+"', email_admin='"+email+"', ciud_admin='"+ciudad+"', dir_admin='"+direccion+"', ref_admin='"+referencia+"', fech_ing_admin='"+fecha+"' WHERE ced_admin = '"+cedula+"'";
        CRUD cr = new CRUD();
        if(cr.Registrar(queries)){ resp = "Se actualizo exitosamente"; }
        else { resp = "Error al actualizar"; }
        return resp;
    }
    
    public ArrayList<String> BuscarChofer(String cedula){
        String query3 = "SELECT * FROM chofer, ciudad WHERE chofer.ced_chofer = '"+cedula+"' AND chofer.ciudad_chofer = ciudad.id_ciudad";
        ArrayList<String> datos = new ArrayList<>();
        conexion con3 = new conexion();
        PreparedStatement pst3 = null;
        ResultSet rs3 = null;
        try {
              pst3 = con3.getConnection().prepareStatement(query3);
              rs3 = pst3.executeQuery();
              while(rs3.next()){
                  datos.add(rs3.getString("nom_chofer"));
                  datos.add(rs3.getString("apel_chofer"));
                  datos.add(rs3.getString("razon_chofer"));
                  datos.add(rs3.getString("telf_chofer"));
                  datos.add(rs3.getString("cel_chofer"));
                  datos.add(rs3.getString("email_chofer"));
                  datos.add(rs3.getString("nom_ciudad"));
                  datos.add(rs3.getString("dir_chofer"));
                  datos.add(rs3.getString("ref_chofer"));
                  datos.add(rs3.getString("lic_chofer"));
                  datos.add(rs3.getString("fech_ing_chofer"));
                  datos.add(rs3.getString("estado_chofer"));
              }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return datos;
    }
        
    public String EliminarChofer(String cedula){
        String respuesta = "";
        String query4 = "UPDATE chofer SET estado_chofer = '0' WHERE ced_chofer = '"+cedula+"'";
        CRUD cr = new CRUD();
        if(cr.Registrar(query4)){ respuesta = "Se Elimino exitosamente"; }
        else { respuesta = "Error al eliminar"; }
        return respuesta;
    }
    
    public String ModificarChofer(String cedula, String nombres, String apellidos, String razon, String telefono, String celular, String email, String ciudad, String direccion, String referencia, String licencia, String fecha){
        String respuesta = "";
        String query = "UPDATE chofer SET nom_chofer='"+nombres+"', apel_chofer='"+apellidos+"', razon_chofer='"+razon+"', telf_chofer='"+telefono+"', cel_chofer='"+celular+"', email_chofer='"+email+"', ciudad_chofer='"+ciudad+"', dir_chofer='"+direccion+"', ref_chofer='"+referencia+"', fech_ing_chofer='"+fecha+"', lic_chofer='"+licencia+"' WHERE ced_chofer='"+cedula+"'";
        CRUD cr = new CRUD();
        if(cr.Registrar(query)){ respuesta = "Se Modifico datos exitosamente"; }
        else { respuesta = "Error al modificar"; }
        return respuesta;
    }
    
    public String RazonPropietario(String cedula, int tipo){
        String resp = "";
        String sql = "";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        if(tipo == 1){ sql = "SELECT razon_propietario FROM propietario WHERE ruc_propietario = '"+cedula+"'"; }
        if(tipo == 2){ sql = "SELECT razon_chofer FROM chofer WHERE ced_chofer = '"+cedula+"'"; }
        try {
                p = c.getConnection().prepareStatement(sql);
                r = p.executeQuery();
                while(r.next()){
                    resp = ""+r.getString(1)+"";
                }
        } catch (Exception e) { System.err.println("Error "+e); }        
        return resp;
    }
    
    public String ComboTipoVehiculo(){
        String resp = "";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                p = c.getConnection().prepareStatement("SELECT * FROM tipovehiculo");
                r = p.executeQuery();
                while(r.next()){
                    resp += "<option value='"+r.getString("id_tipoveh")+"'>"+r.getString("nom_tipoveh")+"</option>";
                }
        } catch (Exception e) { System.err.println("Error "+e); } 
        return resp;
    }
    
    public String ComboPermisoVehiculo(){
        String resp = "";
        conexion c = new conexion();
        PreparedStatement p= null;
        ResultSet r = null;
        try {
                p = c.getConnection().prepareStatement("SELECT * FROM pemiauto");
                r = p.executeQuery();
                while(r.next()){
                    resp += "<option value='"+r.getString("id_permi")+"'>"+r.getString("nom_permi")+"</option>";
                }
        } catch (Exception e) { System.err.println("Error "+e); } 
        return resp;
    }
    
    public ArrayList<String> BuscarPropietario(String cedula){
        String query3 = "SELECT razon_propietario, cel_propietario FROM propietario WHERE ruc_propietario = '"+cedula+"'";
        ArrayList<String> datos = new ArrayList<>();
        conexion con3 = new conexion();
        PreparedStatement pst3 = null;
        ResultSet rs3 = null;
        try {
              pst3 = con3.getConnection().prepareStatement(query3);
              rs3 = pst3.executeQuery();
              while(rs3.next()){
                  datos.add(rs3.getString("razon_propietario"));
                  datos.add(rs3.getString("cel_propietario"));
              }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return datos;
    }
    
       public String CargarZonaCmb(String cedula){
        String query3 = "SELECT id_zona, nom_zona FROM zonacarga WHERE ced_cliente = '"+cedula+"'";
        String datos = "";
        conexion con3 = new conexion();
        PreparedStatement pst3 = null;
        ResultSet rs3 = null;
        try {
              pst3 = con3.getConnection().prepareStatement(query3);
              rs3 = pst3.executeQuery();
              while(rs3.next()){
                  datos += "<option value='"+rs3.getString("id_zona")+"'>"+rs3.getString("nom_zona")+"</option>";
              }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return datos;
    }

}
