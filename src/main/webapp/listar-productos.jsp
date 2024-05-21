<%@page contentType="UTF-8" import="java.util.*" %>
<%@page import="com.uduran.apiserverlet.webapp10.models.*"%>
<%@page import="com.google.gson.Gson" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.google.gson.JsonObject" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>Listado de productos</title>
    </head>
    <body>
        <h1>Listado de productos</h1>
        <% if (username.isPresent()) {%>
        <div>
            <h3>Hola, <%=username.get()%>, bienvenido!</h3>
        </div>
        <div>
            <form action="<%=request.getContextPath()%>/item/form/categoria" method="get">
                <label for="categoria">Seleccione la categoria del producto:</label>
                <select name="categoria" id="categoria">
                    <option value="">Seleccione una categoria</option>
                    <%for (Categoria c: categorias){%>
                    <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                    <%}%>
                </select>
                <br/>
                <input type="submit" value="Crear"/>
            </form>
        </div>
        <% } %>
        <div>
            <div>
                <table>
                    <tr>
                        <th>id</th>
                        <th>nombre</th>
                        <th>tipo</th>
                        <% if (username.isPresent()) {%>
                        <th>precio</th>
                        <th>agregar</th>
                        <th>editar</th>
                        <% } %>
                    </tr>
                    <%for (Producto p : productos) {%>
                    <tr>
                        <td><%=p.getId()%></td>
                        <td><%=p.getNombre()%></td>
                        <td><%=p.getCategoria().getNombre()%></td>
                        <% if (username.isPresent()) {%>
                        <td><%=p.getPrecio()%></td>
                        <td>
                            <%
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("tipo", p.getClass().getName());
                                jsonObject.add("item", new Gson().toJsonTree(p));
                                String itemJson = jsonObject.toString();
                                String encodedItemJson = URLEncoder.encode(itemJson, StandardCharsets.UTF_8);
                            %>
                            <form action="<%=request.getContextPath()%>/carro/agregar" method="post">
                                <input type="hidden" name="item" value="<%=encodedItemJson%>"/>
                                <input type="submit" value="Agregar al carro"/>
                            </form>
                            <form action="<%=request.getContextPath()%>/carro/agregar" method="post">
                                <input type="hidden" name="item" value="<%=encodedItemJson%>"/>
                                <input type="submit" value="Agregar al carro"/>
                            </form>
                        </td>
                        <% } %>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
        <div>
            <%if (username.isPresent()){%>
            <button onclick="window.location.href='<%=request.getContextPath()%>/carro/ver'">Carro</button>
            <%}%>

        </div>
        <div>
            <%if (username.isPresent()){%>
                <button onclick="window.location.href='<%=request.getContextPath()%>/index.jsp'">Inicio</button>
            <%}else{%>
                <button onclick="window.location.href='<%=request.getContextPath()%>/index.html'">Inicio</button>
            <%}%>
        </div>
    </body>
</html>