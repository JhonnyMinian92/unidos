package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class CRUD extends conexion {
    
    public String Autenticacion(String user, String clave){
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String logueo = "SELECT roles.nom_rol FROM usuario, roles WHERE usuario.ced_usuario = ? AND usuario.clave_usuario = ? AND usuario.id_rol = roles.id_rol";
            pst = getConnection().prepareStatement(logueo);
            pst.setString(1, user);
            pst.setString(2, clave);
            rs = pst.executeQuery();
            if(rs.absolute(1)){ 
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println("Error"+e);
        } finally {
            try {
                if(getConnection() != null) getConnection().close();
                if(pst != null) pst.close();
                if(rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("Error"+e);
            }
        }
        return "";
    }
    
    public boolean Registrar(String query){
    PreparedStatement pst = null;
        try {
            pst = getConnection().prepareStatement(query);
            if(pst.executeUpdate() == 1){
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error"+e);
        } finally {
            try {
                if(getConnection() != null) getConnection().close();
                if(pst != null) pst.close();
            } catch (Exception e) {
                System.err.println("Error"+e);
            }
        }
    return false;
    }
    
    
    public int UltimoRegistro(String query){
    PreparedStatement pst = null;
    int id = 0;
        try {
            pst = getConnection().prepareStatement(query, pst.RETURN_GENERATED_KEYS);
            id = pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                     id = generatedKeys.getInt(1);
            }
        } catch (Exception e) { System.err.println("Error"+e); } 
    return id;
    }
    
}
