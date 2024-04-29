<%@page contentType="UTF-8" import="java.util.*" %>
<%@page import="com.uduran.apiserverlet.webapp10.models.*"%>
<%@page import="com.google.gson.Gson" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.google.gson.JsonObject" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
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
        <% if (username.isPresent()) {%>
        <div>Hola, <%=username.get()%>, bienvenido!</div>
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
                        </td>
                        <% } %>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div>
                <table>
                    <tr>
                        <th>id</th>
                        <th>nombre</th>
                        <th>decripcion</th>
                        <th>intructor</th>
                        <th>duracion</th>
                        <% if (username.isPresent()) {%>
                        <th>precio</th>
                        <th>agregar</th>
                        <% } %>
                    </tr>
                    <%for (Curso c : cursos) {%>
                    <tr>
                        <td><%=c.getId()%></td>
                        <td><%=c.getNombre()%></td>
                        <td><%=c.getDescripcion()%></td>
                        <td><%=c.getInstructor()%></td>
                        <td><%=c.getDuracion()%></td>
                        <% if (username.isPresent()) {%>
                        <td><%=c.getPrecio()%></td>
                        <td>
                            <%
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("tipo", c.getClass().getName());
                                jsonObject.add("item", new Gson().toJsonTree(c));
                                String itemJson = jsonObject.toString();
                                String encodedItemJson = URLEncoder.encode(itemJson, StandardCharsets.UTF_8);
                            %>
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
            <button onclick="window.location.href='<%=request.getContextPath()%>/carro/ver'">Carro</button>
        </div>
        <div>
            <button onclick="window.location.href='<%=request.getContextPath()%>/index.html'">Volver!</button>
        </div>
    </body>
</html>