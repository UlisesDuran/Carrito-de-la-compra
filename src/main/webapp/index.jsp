<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Optional" %>
<%Optional<String> nombre = Optional.ofNullable((String)request.getAttribute("nombre"));%>
<%Optional<String> username = Optional.ofNullable((String)session.getAttribute("username"));%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tienda Online!</title>
</head>
<body>
<%if (username.isPresent()){%>
    <%if (nombre.isPresent()){%>
    <h1>Hola <%=nombre.get()%> has iniciado sesión con éxito!</h1>
    <%}%>
<%}%>

<ul>
    <li><a href="<%=request.getContextPath()%>/comprar/productos">Mostrar tienda productos</a></li>
    <li><a href="<%=request.getContextPath()%>/comprar/cursos">Mostrar tienda cursos</a></li>
    <%if (username.isPresent()){%>
        <li><a href="<%=request.getContextPath()%>/carro/ver">ver carro</a></li>
        <li><a href="<%=request.getContextPath()%>/index.jsp">Inicio</a></li>
        <li><a href="<%=request.getContextPath()%>/logout">Cerrar sesión</a></li>
    <%}else{%>
        <li><a href="<%=request.getContextPath()%>/index.html">Inicio</a></li>
    <%}%>
</ul>
</body>
</html>