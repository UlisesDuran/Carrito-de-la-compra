<%@page contentType="text/html" pageEncoding="UTF-8" import="com.uduran.apiserverlet.webapp10.models.*" %>
<%@ page import="java.util.Optional" %>

<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
<h1>Carro de Compras</h1>
<% if (carro.getItems().isEmpty()) { %>
<p>Lo sentimos no hay productos en el carro de compras!</p>
<%} else {%>
<form name="formcarro" action="<%=request.getContextPath()%>/carro/actualizar" method="post">
    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>precio</th>
            <th>cantidad</th>
            <th>total</th>
            <th>borrar</th>
        </tr>
        <%for (ItemCarro item : carro.getItems()) {%>
            <%Optional<Curso> curso = Optional.ofNullable(item.getCurso());%>
            <%Optional<Producto> producto = Optional.ofNullable(item.getProducto());%>
            <%if (curso.isPresent()) {%>
            <tr>
                <td><%=curso.get().getId()%>
                </td>
                <td><%=curso.get().getNombre()%>
                </td>
                <td><%=curso.get().getPrecio()%>
                </td>
                <td><input type="text" size="4" name="cant_<%=curso.get().getId()%>" value="<%=item.getCantidad()%>"/></td>
                <td><%=item.getImporte()%>
                </td>
                <td><input type="checkbox" value="<%=curso.get().getNombre()%>" name="deleteItems"/></td>
            </tr>
            <%}%>
            <%if (producto.isPresent()) {%>
            <tr>
                <td><%=producto.get().getId()%>
                </td>
                <td><%=producto.get().getNombre()%>
                </td>
                <td><%=producto.get().getPrecio()%>
                </td>
                <td><input type="text" size="4" name="cant_<%=producto.get().getNombre()%>" value="<%=item.getCantidad()%>"/></td>
                <td><%=item.getImporte()%>
                </td>
                <td><input type="checkbox" value="<%=producto.get().getNombre()%>" name="deleteItems"/></td>
            </tr>
            <%}%>
        <%}%>
        <tr>
            <td colspan="4" style="text-align: right">Total:</td>
            <td><%=carro.getTotal()%>
            </td>
        </tr>
    </table>
    <a href="javascript:document.formcarro.submit();">Actualizar</a>
</form>
<%}%>
<p><a href="<%=request.getContextPath()%>/comprar">seguir comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">volver</a></p>
</body>
</html>