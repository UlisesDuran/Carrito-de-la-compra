<%@page contentType="UTF-8" import="java.util.*, com.uduran.apiserverlet.webapp10.models.*"%>
<%@ page import="com.uduran.apiserverlet.webapp10.models.Producto" %>
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Listado de productos</title>
</head>
<body>
<h1>Listado de productos</h1>
<% if (username.isPresent()){%>
<div>Hola, <%=username.get()%>, bienvenido!</div>
<% }  %>
<table>
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>tipo</th>
        <% if (username.isPresent()){%>
        <th>precio</th>
        <th>agregar</th>
        <% } %>
    </tr>
    <%for(Producto p: productos) {%>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getNombre()%></td>
        <td><%=p.getTipo()%></td>
        <% if (username.isPresent()){%>
        <td><%=p.getPrecio()%></td>
        <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></td>
        <% } %>
    </tr>
    <%}%>
</table>
</body>
</html>