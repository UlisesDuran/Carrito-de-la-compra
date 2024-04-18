<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Optional" %>
<%Optional<String> nombre = Optional.ofNullable((String)request.getAttribute("nombre"));%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tienda Online!</title>
</head>
<body>
<h1>Hola <%=nombre.get()%> has iniciado sesión con éxito!</h1>
<ul>
    <li><a href="<%=request.getContextPath()%>/comprar">Mostrar tienda</a></li>
    <li><a href="<%=request.getContextPath()%>/carro/ver">ver Carro</a></li>
</ul>
<div>
    <button onclick="window.location.href='/webapp10/index.html'">Volver!</button>
</div>
</body>
</html>