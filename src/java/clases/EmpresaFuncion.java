package clases;

import controlador.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lakeo
 */
public class EmpresaFuncion {
    
    public String[] ListarEmpresa(String consul){
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String[] datos = new String[10];
        try {
            pst = con.getConnection().prepareStatement(consul);
            rs = pst.executeQuery();
            while(rs.next()){ 
                if(rs.getRow() == 0) {
                    datos[1] = "";
                    datos[2] = "";
                    datos[3] = "";
                    datos[4] = "";
                    datos[5] = "";
                    datos[6] = "";
                    datos[7] = "";
                    datos[8] = "";
                    datos[9] = "";
                }
                else{
                    datos[1] = rs.getString(1);
                    datos[2] = rs.getString(2);
                    datos[3] = rs.getString(3);
                    datos[4] = rs.getString(4);
                    datos[5] = rs.getString(5);
                    datos[6] = rs.getString(6);
                    datos[7] = rs.getString(7);
                    datos[8] = rs.getString(8);
                    datos[9] = rs.getString(9);
                }
            }
            return datos;
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
    
}
