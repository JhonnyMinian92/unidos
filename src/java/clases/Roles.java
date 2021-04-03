package clases;

import controlador.CRUD;
import controlador.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Roles {
        public String[][] ListarRoles(){
            String query = "SELECT * FROM roles";
            String[][] ciudad = new String[20][2];
            conexion con = new conexion();
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                pst = con.getConnection().prepareStatement(query);
                rs = pst.executeQuery();
                int i = 1;
                while(rs.next()){
                    ciudad[i][0] = rs.getString(1);
                    ciudad[i][1] = rs.getString(2);
                    i++;
                }
            } catch (Exception e) {
                System.err.println("Error "+e);
            }
            return ciudad;
        }
        
        public String ActualizarRol(String id, String rol){
            String res = "";
            String queri = "UPDATE roles SET nom_rol='"+rol+"' WHERE id_rol='"+id+"'";
            CRUD cru = new CRUD();
            if(cru.Registrar(queri)){ res = "Rol guardada exitosamente"; }
            else { res = "Error al ingresar rol";  }
            return res;
        }
        
        public String GuardarRol(String rol){
            String res = "";
            String query = "INSERT INTO roles(nom_rol) VALUES('"+rol+"')";
            CRUD cr = new CRUD();
            if(cr.Registrar(query)){ res = "Rol guardada exitosamente"; }
            else { res = "Error al ingresar rol";  }
            return res;
        } 
        
        public int BuscaRol(String cedula){
            String consul = "SELECT id_rol FROM usuario WHERE ced_usuario = '"+cedula+"'";
            conexion con = new conexion();
            PreparedStatement pst = null;
            ResultSet rs = null;
            String idrol = "";
            try {
                pst = con.getConnection().prepareStatement(consul);
                rs = pst.executeQuery();
                while(rs.next()){ 
                    idrol = rs.getString("id_rol");
                }
            } catch (Exception e) { System.err.println("Error"+e);
            } finally {
                try {
                    if(con.getConnection() != null) con.getConnection().close();
                    if(pst != null) pst.close();
                    if(rs != null) rs.close();
                } catch (Exception e) { System.err.println("Error"+e); }
            }
            return Integer.parseInt(idrol);
        }
        
}
