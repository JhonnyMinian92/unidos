package clases;

import controlador.CRUD;
import controlador.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.omg.CORBA.INTERNAL;

public class Vehiculo {
    
    public String GuardarVehiculo(String propietario, String tipo, String dueno, String chofer, String placa, String marca, String modelo, String anio, String foto, String fecha, String clase, String combustible, String color, String permiso){
        String r = "";
        String q = "INSERT INTO vehiculo(placa_veh,marca_veh,modelo_veh,anio_veh,foto_veh,fecha_mat_veh,dueno_veh,clase_veh,combus_veh,color,id_tipoveh,id_permiso,id_estado,ruc_propietario,ced_chofer) VALUES('"+placa+"','"+marca+"','"+modelo+"','"+anio+"','"+foto+"','"+fecha+"','"+dueno+"','"+clase+"','"+combustible+"','"+color+"','"+tipo+"','"+permiso+"','1','"+propietario+"','"+chofer+"')";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se guardo vehiculo exitosamente"; }
        else { r = "Error al guardar vehiculo"; }
        return r;
    }
    
    public String ActualizarVehiculo(String propietario, String tipo, String dueno, String chofer, String placa, String marca, String modelo, String anio, String foto, String fecha, String clase, String combustible, String color, String permiso){
        String r = "";
        String q = "UPDATE vehiculo SET marca_veh='"+marca+"',modelo_veh='"+modelo+"',anio_veh='"+anio+"',foto_veh='"+foto+"',fecha_mat_veh='"+fecha+"',dueno_veh='"+dueno+"',clase_veh='"+clase+"',combus_veh='"+combustible+"',color='"+color+"',id_tipoveh='"+tipo+"',id_permiso='"+permiso+"',ruc_propietario='"+propietario+"',ced_chofer='"+chofer+"' WHERE placa_veh = '"+placa+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se modifico vehiculo exitosamente"; }
        else { r = "Error al modificar vehiculo"; }
        return r;
    }
    
    public String BajaVehicular(String placa){
        String r = "";
        String q = "UPDATE vehiculo SET id_estado = '4' WHERE placa_veh = '"+placa+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se elimino vehiculo"; }
        else { r = "Error al eliminar vehiculo"; }
        return r;
    }
    
    public String BajaGuia(String guia){
        String r = "";
        String q = "UPDATE guiaremision SET estado='0' WHERE num_guia = '"+guia+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se anulo guia correctamente"; }
        else { r = "Error al eliminar guia"; }
        return r;
    }
    
    public String ActivarVehiculo(String placa){
        String r = "";
        String q = "UPDATE vehiculo SET id_estado = '1' WHERE placa_veh = '"+placa+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se reactivo vehiculo"; }
        else { r = "Error al reactivar vehiculo"; }
        return r;
    }
    
    public String GuardarTipoAuto(String tipo){
        String r = "";
        String q = "INSERT INTO tipovehiculo(nom_tipoveh) VALUES('"+tipo+"')";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se guardo tipo de vehiculo"; }
        else { r = "Error al guardar tipo de vehiculo"; }
        return r;
    }
    
    public String GuardarPermisoAuto(String permiso){
        String r = "";
        String q = "INSERT INTO pemiauto(nom_permi) VALUES('"+permiso+"')";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se guardo tipo de vehiculo"; }
        else { r = "Error al guardar tipo de vehiculo"; }
        return r;
    }
    
    public String ListarVehiculo(String dueno){
        String r = "";
        String q = "SELECT *\n" +
                    "FROM vehiculo, propietario, tipovehiculo, pemiauto\n" +
                    "WHERE vehiculo.id_tipoveh = tipovehiculo.id_tipoveh\n" +
                    "AND vehiculo.id_permiso = pemiauto.id_permi\n" +
                    "AND vehiculo.ruc_propietario = propietario.ruc_propietario\n" +
                    "AND vehiculo.id_estado < '4'\n" +
                    "AND propietario.razon_propietario LIKE '%"+dueno+"%'";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){
                    String due = "";
                    if(rs.getString("dueno_veh").equals("1")){ due = "Propio";}
                    if(rs.getString("dueno_veh").equals("2")){ due = "Alquilado"; }
                    r += "<tr id='tr"+rs.getString("placa_veh")+"' class='seclistar seccion' ondblclick='ModVehiculo(this.id);'>"
                            + "<td>"+rs.getString("placa_veh")+"</td>"
                            + "<td>"+rs.getString("marca_veh")+"</td>"
                            + "<td>"+rs.getString("anio_veh")+"</td>"
                            + "<td>"+rs.getString("clase_veh")+"</td>"
                            + "<td>"+rs.getString("nom_tipoveh")+"</td>"
                            + "<td>"+rs.getString("razon_propietario")+"</td>"
                            + "<td>"+due+"</td>"
                            + "<td><div class='imgbaja' id='"+rs.getString("placa_veh")+"' onclick='DarBaja(this.id);'></div></td>"
                            + "<td></td>"
                        + "</tr>";
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r;
    }
       
    public ArrayList<String> BuscarVehiculo(String placa){
        ArrayList<String> r = new ArrayList<>();
        String q = "SELECT propietario.ruc_propietario, propietario.razon_propietario, vehiculo.dueno_veh, chofer.ced_chofer, chofer.razon_chofer, vehiculo.marca_veh, vehiculo.modelo_veh, vehiculo.anio_veh, vehiculo.foto_veh, vehiculo.fecha_mat_veh, vehiculo.clase_veh, vehiculo.id_tipoveh, vehiculo.combus_veh, vehiculo.color, vehiculo.id_permiso, vehiculo.id_estado "
                + "FROM vehiculo, propietario, chofer "
                + "WHERE vehiculo.ruc_propietario = propietario.ruc_propietario "
                + "AND vehiculo.placa_veh = '"+placa+"'";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){ 
                    r.add(rs.getString(1));
                    r.add(rs.getString(2));
                    r.add(rs.getString(3));
                    r.add(rs.getString(4));
                    r.add(rs.getString(5));
                    r.add(rs.getString(6));
                    r.add(rs.getString(7));
                    r.add(rs.getString(8));
                    r.add(rs.getString(9));
                    r.add(rs.getString(10));
                    r.add(rs.getString(11));
                    r.add(rs.getString(12));
                    r.add(rs.getString(13));
                    r.add(rs.getString(14));
                    r.add(rs.getString(15));
                    r.add(rs.getString(16));
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r;
    }
    
    public String VerVehiculo(String placa){
        String r = "";
        String q = "SELECT marca_veh, modelo_veh FROM vehiculo WHERE placa_veh = '"+placa+"'";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){ 
                    r = ""+rs.getString("marca_veh")+" "+rs.getString("modelo_veh")+"";
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r;
    }
    
    public String CambiarEstadoVeh(String placa, int id){
        String r = "";
        String q = "UPDATE vehiculo SET id_estado = '"+id+"' WHERE placa_veh = '"+placa+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "Se realizo exitosamente"; }
        else { r = "Error al cambiar estado"; }
        return r;
    }
    
    public String GuardarMantenimiento(String placa, String taller, String fecha){
        String r = "";
        String q = "INSERT INTO mantenimiento(fecha_manten,nom_taller,placa_vehiculo) VALUES('"+fecha+"','"+taller+"','"+placa+"')";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ 
            //modificar estado de vehiculo
            r = CambiarEstadoVeh(placa,3);
        }
        else{ r = "error al guardar"; }
        return r;
    }
    
    public String GuardarDatosGuia(String num, String fecha, String destino, String cedula, String cantidad, String peso, String tamano, String origen, String motivo){
        String r = "";
        String q = "UPDATE guiaremision SET fech_envio_guia='"+fecha+"', destino_guia='"+destino+"', ruc_dest_guia='"+cedula+"', cant_carga='"+cantidad+"', peso_carga='"+peso+"', tamano_carga='"+tamano+"' WHERE num_guia = '"+num+"'";
        CRUD c = new CRUD();
        if(c.Registrar(q)){ r = "actualizado correctamente"; }
        else{ r = "error al guardar"; }
        return r;
    }
    
    public String ListarMantenimiento(String placa){
        String r = "";
        String q = "SELECT * FROM mantenimiento WHERE placa_vehiculo = '"+placa+"'";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){
                r += "<tr class='seclistar'><td>"+rs.getString("id_manten")+"</td><td>"+rs.getString("nom_taller")+"</td><td>"+rs.getString("fecha_manten")+"</td></tr>";                  
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r;
    }
    
    public String ReporteVehiculo(){
        String repor = "<table id='tvehiculos' class='tablalistar'>";
        String estado1 = "<tr class='barralistado encabe'><th colspan='9'>Vehiculos Disponibles</th></tr>"
                       + "<tr class='barralistado'><th>Placa</th><th>Marca</th><th>Modelo</th><th>Tipo</th><th>Permiso</th><th>Año</th><th>Dueño</th><th>Propiedad</th></tr>";
        String estado2 = "<tr class='barralistado encabe'><th colspan='9'>Vehiculos Ocupados</th></tr>"
                       + "<tr class='barralistado'><th>Placa</th><th>Marca</th><th>Modelo</th><th>Tipo</th><th>Permiso</th><th>Año</th><th>Dueño</th><th>Propiedad</th></tr>";
        String estado3 = "<tr class='barralistado encabe'><th colspan='9'>Vehiculos en Mantenimiento</th></tr>"
                       + "<tr class='barralistado'><th>Placa</th><th>Marca</th><th>Modelo</th><th>Tipo</th><th>Permiso</th><th>Año</th><th>Dueño</th><th>Propiedad</th></tr>";
        String estado4 = "<tr class='barralistado encabe'><th colspan='9'>Vehiculos de Baja</th></tr>"
                       + "<tr class='barralistado'><th>Placa</th><th>Marca</th><th>Modelo</th><th>Tipo</th><th>Permiso</th><th>Año</th><th>Dueño</th><th>Propiedad</th></tr>";
        String sql = "SELECT * FROM vehiculo, propietario, tipovehiculo, pemiauto, estadovehiculo WHERE vehiculo.id_estado = estadovehiculo.id_estado AND vehiculo.id_tipoveh = tipovehiculo.id_tipoveh AND vehiculo.id_permiso = pemiauto.id_permi AND vehiculo.ruc_propietario = propietario.ruc_propietario";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
            while(rs.next()){
                String due = "";
                if(rs.getString("dueno_veh").equals("1")){ due = "Propio";}
                if(rs.getString("dueno_veh").equals("2")){ due = "Alquilado"; }
                if(rs.getString("id_estado").equals("1")){ estado1 += "<tr class='seclistar'><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("marca_veh")+"</td><td>"+rs.getString("modelo_veh")+"</td><td>"+rs.getString("nom_tipoveh")+"</td><td>"+rs.getString("nom_permi")+"</td><td>"+rs.getString("anio_veh")+"</td><td>"+rs.getString("razon_propietario")+"</td><td>"+due+"</td></tr>"; }
                if(rs.getString("id_estado").equals("2")){ estado2 += "<tr class='seclistar'><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("marca_veh")+"</td><td>"+rs.getString("modelo_veh")+"</td><td>"+rs.getString("nom_tipoveh")+"</td><td>"+rs.getString("nom_permi")+"</td><td>"+rs.getString("anio_veh")+"</td><td>"+rs.getString("razon_propietario")+"</td><td>"+due+"</td></tr>"; }
                if(rs.getString("id_estado").equals("3")){ estado3 += "<tr class='seclistar'><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("marca_veh")+"</td><td>"+rs.getString("modelo_veh")+"</td><td>"+rs.getString("nom_tipoveh")+"</td><td>"+rs.getString("nom_permi")+"</td><td>"+rs.getString("anio_veh")+"</td><td>"+rs.getString("razon_propietario")+"</td><td>"+due+"</td></tr>"; }
                if(rs.getString("id_estado").equals("4")){ estado4 += "<tr class='seclistar'><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("marca_veh")+"</td><td>"+rs.getString("modelo_veh")+"</td><td>"+rs.getString("nom_tipoveh")+"</td><td>"+rs.getString("nom_permi")+"</td><td>"+rs.getString("anio_veh")+"</td><td>"+rs.getString("razon_propietario")+"</td><td>"+due+"</td></tr>"; }
            }
            repor += estado1+estado2+estado3+estado4+"</table>";
        } catch (Exception e) { System.err.println("Error"+e);}        
        return repor;
    }
    
    public String TipodeVehiculos(){
        String r = "<table class='tablapermi'><tr class='barrapermiso'><th>Item</th><th colspan='2'>Tipo de Vehiculo</th></tr>";
        String q = "SELECT * FROM tipovehiculo";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){
                r += "<tr class='secpermiso'><td>"+rs.getString("id_tipoveh")+"</td><td colspan='2'>"+rs.getString("nom_tipoveh")+"</td></tr>";                  
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r+"</table>";
    }
    
    public String PermisoVehicular(){
        String r = "<table class='tablapermi'><tr class='barrapermiso'><th>Item</th><th colspan='2'>Permiso Vehicular</th></tr>";
        String q = "SELECT * FROM pemiauto";
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(q);
                rs = pst.executeQuery();
            while(rs.next()){
                r += "<tr class='secpermiso'><td>"+rs.getString("id_permi")+"</td><td colspan='2'>"+rs.getString("nom_permi")+"</td></tr>";                  
            }
        } catch (Exception e) { System.err.println("Error"+e);}
        return r+"</table>";
    }
    
    public int GuardarFactura(String total){
        int id = 0;
        Calendar c = Calendar.getInstance();
        String d = ""+Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.DATE));
        String sql = "INSERT INTO facturacion(fecha_emision, total_fact) VALUES('"+d+"','"+total+"')";
        CRUD cr = new CRUD();
        id = cr.UltimoRegistro(sql);
        return id;
    }
    
    public int GuardarGuiaRemision(String fecha,String destino,String persona,String nombre,int zona,int cantidad,int peso,int tamano,int motivo,String ruc,String cliente,String placa,String chofer, String total){
        int num = 0;
        int factura = GuardarFactura(total);
        String q = "INSERT INTO guiaremision(fech_envio_guia,destino_guia,ruc_dest_guia,nom_dest_guia,id_zonacarga,cant_carga,peso_carga,tamano_carga,id_motivo,ruc_empresa,ced_cliente,placa_veh,ced_chofer,num_fact) VALUES('"+fecha+"','"+destino+"','"+persona+"','"+nombre+"','"+zona+"','"+cantidad+"','"+peso+"','"+tamano+"','"+motivo+"','"+ruc+"','"+cliente+"','"+placa+"','"+chofer+"','"+factura+"')";
        CRUD c = new CRUD();
        if(c.Registrar(q)){  num = factura; CambiarEstadoVeh(placa, 2);   }
        else { num = 0; }
        return num;
    }
    
    public String TablaGuiaRemision(){
        String sql = "SELECT guiaremision.num_guia, facturacion.num_fact, guiaremision.fech_envio_guia, zonacarga.dir_zona, guiaremision.destino_guia, guiaremision.placa_veh, cliente.razon_cliente, facturacion.total_fact "
                     + "FROM guiaremision, facturacion, zonacarga, cliente "
                     + "WHERE guiaremision.ced_cliente = cliente.ced_cliente AND guiaremision.num_fact = facturacion.num_fact AND zonacarga.id_zona = guiaremision.id_zonacarga AND guiaremision.estado = '1'";
        
        String tabla = "";
        String ta = ""
                    + "<tr class='barralistado'><th colspan='8'>Envios Pendientes</th></tr>"
                    + "<tr class='barralistado'><th>Factura</th><th>Fecha de Envio</th><th>Origen</th><th>Destino</th><th>Vehiculo</th><th>Cliente</th><th>Precio</th><th></th></tr>";
        String tb = ""
                    + "<tr class='barralistado'><th colspan='8' >Envios Realizados</th></tr>"
                    + "<tr class='barralistado'><th>Factura</th><th>Fecha de Envio</th><th>Origen</th><th colspan='2'>Destino</th><th>Vehiculo</th><th>Cliente</th><th>Precio</th></tr>";
        
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
            while(rs.next()){
                
                //Fecha actual
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                String d = ""+Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.DATE));
                Date fechaactual = sdf.parse(d);
                //Fecha de envio y comparar
                Date fechaenvio = sdf.parse(rs.getString("fech_envio_guia"));
                if(fechaactual.after(fechaenvio)){
                    tb += "<tr class='seclistar'><td>0000-"+rs.getString("num_fact")+"</td><td>"+rs.getString("fech_envio_guia")+"</td><td>"+rs.getString("dir_zona")+"</td><td colspan='2'>"+rs.getString("destino_guia")+"</td><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("razon_cliente")+"</td><td>"+rs.getString("total_fact")+"</td></tr>";
                }
                else { ta += "<tr class='seclistar'><td>0000-"+rs.getString("num_fact")+"</td><td>"+rs.getString("fech_envio_guia")+"</td><td>"+rs.getString("dir_zona")+"</td><td>"+rs.getString("destino_guia")+"</td><td>"+rs.getString("placa_veh")+"</td><td>"+rs.getString("razon_cliente")+"</td><td>"+rs.getString("total_fact")+"</td><td><div class='imgbaja' id='"+rs.getString("num_guia")+"' onclick='BajaGuia(this.id);'></div></td></tr>";
                }                  
            }
        } catch (Exception e) { System.err.println("Error"+e);}        
        
        tabla = "<table id='reportetransito' class='tablalistar'>"+ta+tb+"</table>";
        
        return tabla;
    }
    
    
    public String TablaVehicular(String placa){
        String sql = "SELECT guiaremision.fech_envio_guia, zonacarga.dir_zona, guiaremision.destino_guia, guiaremision.placa_veh, cliente.razon_cliente "
                     + "FROM guiaremision, facturacion, zonacarga, cliente " 
                     + "WHERE guiaremision.ced_cliente = cliente.ced_cliente AND guiaremision.num_fact = facturacion.num_fact AND zonacarga.id_zona = guiaremision.id_zonacarga AND placa_veh LIKE '%"+placa+"%' " 
                     + "ORDER BY guiaremision.fech_envio_guia";
        
        String tabla = "";
        String ta = "<tr class='barralistado'><th>Fecha de Envio</th><th>Origen</th><th>Destino</th><th>Cliente</th></tr>";
        
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
            while(rs.next()){
                ta += "<tr class='seclistar'><td>"+rs.getString("fech_envio_guia")+"</td><td>"+rs.getString("dir_zona")+"</td><td>"+rs.getString("destino_guia")+"</td><td>"+rs.getString("razon_cliente")+"</td></tr>";
            }
        } catch (Exception e) { System.err.println("Error"+e);}        
        
        tabla = "<table id='reportevehicular' class='tablalistar'>"+ta+"</table>";
        
        return tabla;
    }
    
        public String EstadisticaCliente(String cedula){
        String sql = "SELECT facturacion.num_fact, guiaremision.fech_envio_guia, guiaremision.nom_dest_guia, zonacarga.dir_zona, guiaremision.destino_guia, facturacion.total_fact, facturacion.fecha_emision "
                     + "FROM guiaremision, zonacarga, facturacion "
                     + "WHERE guiaremision.id_zonacarga = zonacarga.id_zona "
                     + "AND guiaremision.num_fact = facturacion.num_fact " 
                     + "AND guiaremision.ced_cliente LIKE '%"+cedula+"%' "
                     + "ORDER BY guiaremision.fech_envio_guia";
        
        String tabla = "";
        String ta = "<tr class='barralistado'><th>Factura</th><th>Fecha de Envio</th><th>Titular</th><th>Origen</th><th>Destino</th><th>Valor</th></tr>";
        
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        int capital = 0;
        int envios = 0;
        String ultimoenvio = "";
        
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
            while(rs.next()){
                envios++;
                ultimoenvio = rs.getString("fecha_emision");
                ta += "<tr class='seclistar'><td>"+rs.getString("num_fact")+"</td><td>"+rs.getString("fech_envio_guia")+"</td><td>"+rs.getString("nom_dest_guia")+"</td><td>"+rs.getString("dir_zona")+"</td><td>"+rs.getString("destino_guia")+"</td><td>"+rs.getString("total_fact")+"</td></tr>";
                capital += Integer.parseInt(rs.getString("total_fact"));
            }
        } catch (Exception e) { System.err.println("Error"+e);}        
        
        tabla = "<table id='reportevehicular' class='tablalistar'><tr class='barralistado'><th colspan='5'>Ultima Compra</th><th>"+ultimoenvio+"</th></tr>"+ta+"<tr class='barralistado'><th colspan='5'>Total de Envios</th><th>"+envios+"</th></tr><tr class='barralistado'><th colspan='5'>Capital Total</th><th>"+capital+"</th></tr></table>";
        
        return tabla;
    }
    
        
        
    public String GuardarCobro(String valor, String medio, String factura){
        String r = "";
        //Obtener Fecha Actual
        Calendar c = Calendar.getInstance();
        String d = ""+Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.DATE));
        //sql generado
        String q = "INSERT INTO cobros(fech_cobro, valor_cobro, medio_cobro, num_fact) WHERE ('"+d+"','"+valor+"','"+medio+"','"+factura+"')";
        CRUD cr = new CRUD();
        if(cr.Registrar(q)){ r = "Gracias por su pago"; }
        else{ r = "error al guardar"; }
        return r;
    }
        
    public ArrayList<String> BuscaGuiaporFactura(String fact){
        ArrayList<String> r = new ArrayList<>();
        String sql = "SELECT guiaremision.num_guia, guiaremision.fech_envio_guia, guiaremision.destino_guia, guiaremision.ruc_dest_guia, guiaremision.cant_carga, guiaremision.peso_carga, guiaremision.tamano_carga, guiaremision.ced_cliente " +
                        "FROM guiaremision, facturacion, zonacarga, cliente " +
                        "WHERE guiaremision.ced_cliente = cliente.ced_cliente " +
                        "AND guiaremision.num_fact = facturacion.num_fact " +
                        "AND zonacarga.id_zona = guiaremision.id_zonacarga " +
                        "AND guiaremision.estado = '1' " +
                        "AND facturacion.num_fact = '"+fact+"'";
              
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                    //Fecha actual
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c = Calendar.getInstance();
                    String d = ""+Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(c.get(Calendar.DATE));
                    Date fechaactual = sdf.parse(d);
                    //Fecha de envio y comparar
                    Date fechaenvio = sdf.parse(rs.getString("fech_envio_guia"));
                    if(!fechaactual.after(fechaenvio)){
                       r.add(rs.getString("num_guia"));
                       r.add(rs.getString("fech_envio_guia"));
                       r.add(rs.getString("destino_guia"));
                       r.add(rs.getString("ruc_dest_guia"));
                       r.add(rs.getString("cant_carga"));
                       r.add(rs.getString("peso_carga"));
                       r.add(rs.getString("tamano_carga"));
                       r.add(rs.getString("ced_cliente"));
                    }                  
                }
        } catch (Exception e) { System.err.println("Error"+e);} 
        return r;
    }    
        
    
    public ArrayList<String> CostoFacturas(String cedula){
        ArrayList<String> r = new ArrayList<>();
        String sql = "SELECT facturacion.total_fact FROM facturacion, guiaremision WHERE ced_cliente = '"+cedula+"' AND facturacion.num_fact = guiaremision.num_fact ORDER BY guiaremision.fech_envio_guia";
              
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                       r.add(rs.getString("total_fact"));                
                }
        } catch (Exception e) { System.err.println("Error"+e);} 
        return r;
    } 
    
    
    public ArrayList<String> IdsExistentes(String cedula){
        ArrayList<String> r = new ArrayList<>();
        String sql = "SELECT facturacion.num_fact FROM facturacion, guiaremision WHERE ced_cliente = '"+cedula+"' AND facturacion.num_fact = guiaremision.num_fact ORDER BY guiaremision.fech_envio_guia";
              
        conexion con = new conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
                pst = con.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                       r.add(rs.getString("total_fact"));                
                }
        } catch (Exception e) { System.err.println("Error"+e);} 
        return r;
    }
        
    public String TotalPagados(String cedula){
            String q = "SELECT SUM(cobros.valor_cobro) AS suma FROM cobros, guiaremision WHERE cobros.num_fact = guiaremision.num_fact AND guiaremision.ced_cliente = '"+cedula+"'";
            String r = "";
            conexion con = new conexion();
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                    pst = con.getConnection().prepareStatement(q);
                    rs = pst.executeQuery();
                    while(rs.next()){
                           r = rs.getString("suma");
                    }
            } catch (Exception e) { System.err.println("Error"+e);} 
            return r;
        }
    
}
