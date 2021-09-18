<%@ page import="controlador.Conexion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aplicación Web 10</title>
</head>
<body>

<%
//Conexion con=new Conexion();
//con.conexiondb();

	String d="", u="", c="", r="", e="";	
if(request.getParameter("do")!=null){

	d=request.getParameter("do");
	u=request.getParameter("us");
	c=request.getParameter("cl");
	r=request.getParameter("ro");
	e=request.getParameter("es");
	}

%>

<form action="ServletGestionUsuario" method="post">
<h1>Formulario de ingreso</h1>

<input type="text" name="doc" value="<%=d%>">

<input type="text" name="usu" value="<%=u%>">

<input type="text" name="cla" value="<%=c%>">

<input type="text" name="rol" value="<%=r%>">

<input type="text" name="est" value="<%=e%>">
<input type="submit" name="btnins" value="REGISTRAR">
<input type="submit" name="btncon" value="CONSULTAR">
<input type="submit" name="btnact" value="ACTUALIZAR">
<input type="submit" name="btneli" value="ELIMINAR">

</form>

</body>
</html>