package clases;

import controlador.CRUD;
import controlador.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ciudad {
    
    public String[][] ListarCiudades(){
        String query = "SELECT * FROM ciudad";
        String[][] ciudad = new String[225][3];
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
                ciudad[i][2] = rs.getString(3);
                i++;
            }
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        return ciudad;
    }
    
    public String GuardarCiudad(String ciudad, String provincia){
        String r = "";
        String query = "INSERT INTO ciudad(nom_ciudad,prov_ciudad) VALUES ('"+ciudad+"','"+provincia+"')";
        CRUD cr = new CRUD();
        if(cr.Registrar(query)){ r = "Ciudad guardada exitosamente"; }
        else { r = "Error al ingresar ciudad";  }
        return r;
    }
    
    public String ActualizarCiudad(String id, String ciudad, String provincia){
        String queri = "UPDATE ciudad SET nom_ciudad = '"+ciudad+"', prov_ciudad = '"+provincia+"' WHERE id_ciudad = '"+id+"'";
        String res = "";
        CRUD cru = new CRUD();
        if(cru.Registrar(queri)){ res = "Ciudad guardada exitosamente"; }
        else { res = "Error al ingresar ciudad";  }
        return res;
    }
    
}
