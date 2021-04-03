<%@page import="clases.Roles"%>
<%@page import="clases.Ciudad"%>
<%@page import="clases.Persona"%>
<%@page import="clases.EmpresaFuncion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="controlador.CRUD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        //Cargar Datos de Usuario
        Persona per = new Persona();
        String[] dato = new String[13];
        dato = per.ListarAdministrador(request.getParameter("user"));
        
        //Cargar Clientes
        String[][] clientlist = new String[200][19];
        clientlist = per.ListarClientes();
        
        int activos = 0;
        int inactivos = 0;
        
        String tabla = "<table class='tablalistar'><tr class='barralistado'><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Telefono</th><th>Celular</th><th>Ciudad</th><th>Oficio</th></tr>";
        String titutabla = "<tr class='barralistado'><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Telefono</th><th>Celular</th><th>Ciudad</th><th>Oficio</th></tr>";
        String total = "<article id='cjreporte' style='width: 770px;'><table class='tablalistar'><tr><td colspan='7' class='subtit'>CLIENTES ACTIVOS</td></tr>";
        String noactivos = "<tr><td colspan='7' class='subtit'>CLIENTES INACTIVOS<td></tr>"+tabla;
        for(int l=0; l<clientlist.length; l++){
            if(clientlist[l][0] != null && Integer.parseInt(clientlist[l][12]) == 1){
                tabla += "<tr class='seclistar'><td>"+clientlist[l][0]+"</td><td>"+clientlist[l][1]+"</td><td>"+clientlist[l][2]+"</td><td>"+clientlist[l][4]+"</td><td>"+clientlist[l][5]+"</td><td>"+clientlist[l][16]+"</td><td>"+clientlist[l][10]+"</td></tr>";
                titutabla += "<tr class='seclistar'><td>"+clientlist[l][0]+"</td><td>"+clientlist[l][1]+"</td><td>"+clientlist[l][2]+"</td><td>"+clientlist[l][4]+"</td><td>"+clientlist[l][5]+"</td><td>"+clientlist[l][16]+"</td><td>"+clientlist[l][10]+"</td></tr>";
                activos++;
            }
            if(clientlist[l][0] != null && Integer.parseInt(clientlist[l][12]) == 0){
                noactivos += "<tr class='seclistar'><td>"+clientlist[l][0]+"</td><td>"+clientlist[l][1]+"</td><td>"+clientlist[l][2]+"</td><td>"+clientlist[l][4]+"</td><td>"+clientlist[l][5]+"</td><td>"+clientlist[l][16]+"</td><td>"+clientlist[l][10]+"</td></tr>";
                inactivos++;
            }
        }
        total += titutabla+noactivos+"<tr class='resulrep'><td colspan='6'>Total Clientes Activos</td><td>"+activos+"</td></tr><tr class='resulrep'><td colspan='6'>Total Clientes Inactivos</td><td>"+inactivos+"</td></tr></table></article><article class='cjdescargar'><div class='btndown' onclick='downloader();'>Descargar</div><article>";
        tabla += "</table>";
        
        //Cargar Ciudades
        Ciudad ciu = new Ciudad();
        String[][] ciudad = new String[225][3];
        ciudad = ciu.ListarCiudades();
        String op = "";
        String lisciu = "<table class='tablaciudad'><tr class='barralicomple'><th colspan='4'>Ciudades Existentes</th></tr><tr class='barralistado'><th>Id</th><th>Ciudad</th><th>Provincia</th><th>Accion</th></tr>";
        for(int i=1; i< ciudad.length; i++){
            if(ciudad[i][0] != null){
                //Lista para complemento
                lisciu += "<tr class='seclistar'><td>"+i+"</td><td id='nomci"+ciudad[i][0]+"' >"+ciudad[i][1]+"</td><td id='nomprov"+ciudad[i][0]+"'>"+ciudad[i][2]+"</td><td id='img"+ciudad[i][0]+"'><img class='btnmodif' src='../img/modificar.png' onclick='modifciudad("+ciudad[i][0]+");'></td></tr>";
                //lista para combobox
                if(Integer.parseInt(ciudad[i][0]) == Integer.parseInt(dato[8])){ op += "<option value='"+ciudad[i][0]+"' selected>"+ciudad[i][1]+"</option>";
                } else { op += "<option value='"+ciudad[i][0]+"'>"+ciudad[i][1]+"</option>"; }
            }
        }
        lisciu += "</table>";
        
        //Listado de Roles
        Roles role = new Roles();
        String[][] cargos = new String[20][2];
        cargos = role.ListarRoles();
        String rol = "";
        String tablarol = "<article class='cjtablapermi'><table class='tablapermi'><tr class='barrapermiso'><th>Id</th><th>Rol</th><th>Accion</th></tr>";
                        
        for(int i=1; i< cargos.length; i++){
            if(cargos[i][0] != null){
                tablarol += "<tr class='secpermiso'><td>"+i+"</td><td id='tdrol"+cargos[i][0]+"'>"+cargos[i][1]+"</td><td id='img"+cargos[i][0]+"'><img class='btnmodif' src='../img/modificar.png' onclick='modifrol("+cargos[i][0]+");'></td></tr>";
                if(cargos[i][1].equals(dato[12])){
                    rol += "<option value='"+cargos[i][0]+"' selected>"+cargos[i][1]+"</option>";
                } else {
                    rol += "<option value='"+cargos[i][0]+"'>"+cargos[i][1]+"</option>";
                }
            }
        }
        tablarol += "</table></article>";
        
        //Datos de la Empresa
        EmpresaFuncion emp = new EmpresaFuncion();
        String[] base = new String[10];
        base = emp.ListarEmpresa("SELECT * FROM empresa");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cooperativa de Transporte "Unidos"</title>
        <script>
            var listadototal = "<%=tabla%>";
            var tablarepor = "<%=total%>";
            var listadeciudad = "<%=lisciu%>";
            var tablarol = "<%=tablarol%>";
            var selectciudades = "<%=op%>";
            var ruc = "<%=base[1]%>";
            var razon = "<%=base[3]%>";
            var direccion = "<%=base[4]%>";
        </script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript" src="../js/administrador.js"></script>
        <script type="text/javascript" src="../js/html2canvas.js"></script>
        <script type="text/javascript" src="../js/jspdf.min.js"></script>
        <link rel="stylesheet" href="../css/estilos.css">
        <link rel="stylesheet" href="../css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="../css/administrador.css">
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
    </head>
    <body>
        <section class="barra">
            <h1>Sistema de Transporte de Carga Pesada</h1>
            <h2>Bienvenido <%= dato[4] %> </h2>
        </section>
        <nav class="botonera">
            <div>
                <button onclick="frmcuenta();">Mi Cuenta</button>
            </div>
            <div>
                <button onclick="frmempresa();">Datos de la Empresa</button>
            </div>
            <div>
                <button onmouseup="menumostrar();">Gestion de Personas</button>
                <div class="cjmenuperson" id="cjmenbtn" style="display: none;">
                    <ul class="menuperson">
                        <li value="1" onclick="cargaperson(this.value);" class="licjbtn">Gestionar Clientes</li>
                        <li value="2" onclick="cargaperson(this.value);" class="licjbtn">Gestionar Choferes</li>
                        <li value="3" onclick="cargaperson(this.value);" class="licjbtn">Gestion Administrador</li>
                        <li value="4" onclick="cargaperson(this.value);" class="licjbtn">Gestionar Propietario</li>
                    </ul>
                </div>
            </div>
            <div>
                <button onclick="frmvehiculo();">Gestion de Vehiculo</button>
            </div>
            <div>
                <button onclick="frmfletes();">Gestion de Fletes</button>
            </div>
            <div class='ocultar'>
                <button>Gestion de Compras</button>
            </div>
            <div>
                <button onmouseup="menucomplemento();">Complementos</button>
                <div class="cjmenuperson" id="cjcombtn" style="display: none;">
                    <ul class="menuperson">
                        <li value="1" onclick="cargacomplemento(this.value);" class="licjbtn">Gestionar Ciudad</li>
                        <li value="2" onclick="cargacomplemento(this.value);" class="licjbtn">Gestionar Permisos</li>
                        <li value="3" onclick="cargacomplemento(this.value);" class="licjbtn">Gestionar Roles</li>
                        <li value="4" onclick="cargacomplemento(this.value);" class="licjbtn">Activar Persona</li>
                        <li value="5" onclick="cargacomplemento(this.value);" class="licjbtn">Tipo de Vehiculo</li>
                        <li value="6" onclick="cargacomplemento(this.value);" class="licjbtn">Permiso Vehicular</li>
                        <li value="7" onclick="cargacomplemento(this.value);" class="licjbtn">Ingreso de Vehiculo</li>
                    </ul>
                </div>
            </div>
            <div>
                <button onclick="salir();">Salir</button>
            </div>
        </nav>
        
        <section id="frmempresa" class="form_wrap" style="display:none;">           
            <section class="cantact_info">
                <section class="info_title" style="padding-top: 20px;">
                    <h2>COOPERATIVA "UNIDOS"</h2>
                </section>
            </section>
            
            <form method="post" action="../empresa" class="form_contact">
                <h2>Datos Generales</h2>
                <div class="user_info">
                    <label for="lbruc">Ruc </label>
                    <input type="number" name="txtruc" value="<%= base[1] %>" required>

                    <label for="lbnombre">Nombre </label>
                    <input type="text" name="txtnombre" value="<%= base[2] %>" required>

                    <label for="lbrazon">Razon </label>
                    <input type="text" name="txtrazon" value="<%= base[3] %>" required>

                    <label for="lbdir">Direccion </label>
                    <textarea name="txtdir" class="txtarea" required><%= base[4] %></textarea>
                    
                    <label for="lbfono">Telefono </label>
                    <input type="number" name="txtfono" value="<%= base[5] %>" required>

                    <label for="lbmail">Email </label>
                    <input type="email" name="txtmail" value="<%= base[6] %>" required>

                    <label for="lblogo">Logo </label>
                    <input type="text" name="txtfoto" value="<%= base[7] %>" required>

                    <label for="lbbanco">Banco </label>
                    <input type="text" name="txtbanco" value="<%= base[9] %>" required>

                    <label for="lbcuenta">Cuenta </label>
                    <input type="number" name="txtcuenta" value="<%= base[8] %>" required>
                    
                    <input type="text" name="user" id="txtelusuario" style="display: none;" value="<%= request.getParameter("user") %>">

                    <input type="submit" value="Guardar" id="btnSend">
                </div>
            </form>
        </section>
        
        <section id="frmcuenta" class="form_wrap" style="display: none;">
        <section class="cantact_info">
            <section class="info_title">
                <span class="fa fa-user-circle"></span>
                <h2>Mi Cuenta</h2>
            </section>            
        </section>
        <form method="post" class="form_contact" action="../info">
            <h2>Informacion</h2>
            <div class="user_info">
                <label for="lbcedula">Cedula </label>
                <input type="number" name="txtcedula" value="<%= dato[1] %>" required>
                
                <label for="lbnombre">Nombres </label>
                <input type="text" name="txtnombre" value="<%= dato[2] %>" required>
                
                <label for="lbapel">Apellidos </label>
                <input type="text" name="txtapel" value="<%= dato[3] %>" required>
                
                <label for="lbrazon">Razon </label>
                <input type="text" name="txtrazon" value="<%= dato[4] %>" required>
                
                <label for="lbfono">Convencional </label>
                <input type="number" name="txtfono" value="<%= dato[5] %>" required>
                
                <label for="lbcelu">Celular </label>
                <input type="number" name="txtcelu" value="<%= dato[6] %>" required>
                
                <label for="lbmail">Email </label>
                <input type="email" name="txtmail" value="<%= dato[7] %>" required>
                
                <label for="lbciud">Ciudad </label>
                <select name="txtciu" id="txtciu" class="cmbx">
                    <%= op %>
                </select>
                
                <label for="lbdire" class="txtarea">Direccion </label>
                <textarea name="txtdir" required><%= dato[9] %></textarea>
                
                <label for="lbrefe">Referencia </label>
                <input type="text" name="txtrefe" value="<%= dato[10] %>" required>
                
                <label for="lbrol">Rol </label>
                <select name="txtrol" class="cmbx">
                <%= rol %>  
                </select>
                
                <input type="submit" value="Guardar" id="btnguar">
            </div>
        </form>
        </section>   
                
        <section id="frmpersonas" class="form_wrap" style="display:none;">
            <section class="cjbotonera" id="barrame"></section>
            <form method="post" class="form_contact" action="../persona">
                <div class="user_info" id="cjclientenew"></div>
            </form>
        </section>
                
        <section id="frmcomplementos" class="form_wrap" style="display:none;"></section>
    </body>
</html>
