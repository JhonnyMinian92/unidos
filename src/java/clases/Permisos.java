package clases;

import controlador.CRUD;
import controlador.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Permisos {
    
    public String ListadoPermisos(){
        String tabla = "<article class='cjtablapermi'><table class='tablapermi'><tr class='barrapermiso'><th>Id</th><th>Permiso</th><th>Accion</th></tr>";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int n = 1;
        try {
                pst = con.getConnection().prepareStatement("SELECT * FROM permisos");
                rs = pst.executeQuery();               
                while(rs.next()){
                    tabla += "<tr class='secpermiso'><td>"+n+"</td><td id='tdperm"+rs.getString("id_permisos")+"'>"+rs.getString("tipo_permiso")+"</td><td id='img"+rs.getString("id_permisos")+"'><img class='btnmodif' src='../img/modificar.png' onclick='modifpermiso("+rs.getString("id_permisos")+");'></td></tr>";
                    n++;
                }
                tabla += "</table></article>";
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return tabla;
    }
    
    public String UpdatePermisos(String id, String permiso){
        String res = "";
        String query = "UPDATE permisos SET tipo_permiso = '"+permiso+"' WHERE id_permisos = '"+id+"'";
        CRUD cru = new CRUD();
        if(cru.Registrar(query)){ res = "Permiso guardada exitosamente"; }
        else { res = "Error al ingresar Permiso";  }
        return res;
    }
    
    
    public String Savepermisos(String permiso){
        String res = "";
        String queri = "INSERT INTO permisos(tipo_permiso) VALUES('"+permiso+"')";
        CRUD cr = new CRUD();
        if(cr.Registrar(queri)){ res = "Permiso guardada exitosamente"; }
        else { res = "Error al ingresar Permiso";  }
        return res;
    }
}
