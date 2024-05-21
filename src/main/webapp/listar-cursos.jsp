<%@page contentType="UTF-8" import="java.util.*" %>
<%@page import="com.uduran.apiserverlet.webapp10.models.*"%>
<%@page import="com.google.gson.Gson" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.google.gson.JsonObject" %>
<%
    List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>Listado de cursos</title>
    </head>
    <body>
        <div>
            <div>
                <h1>Listado de cursos</h1>
            </div>
            <% if (username.isPresent()) {%>
            <div>
                <h3>Hola, <%=username.get()%>, bienvenido!</h3>
            </div>
            <div>
                <form action="<%=request.getContextPath()%>/item/form/categoria" method="get">
                    <label for="categoria_id">Seleccione la categoria del producto:</label>
                    <select name="categoria_id" id="categoria_id">
                        <option value="" disabled selected>Seleccione una categoria</option>
                        <%for (Categoria c: categorias){%>
                            <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                        <%}%>
                    </select>
                    <br/>
                    <input type="submit" value="Crear"/>
                </form>
            </div>
            <% } %>
        </div>

        <div>
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
                        <th>editar</th>
                        <% } %>
                    </tr>
                    <%for (Curso c : cursos) {%>
                    <tr>
                        <td><%=c.getId()%></td>
                        <td><%=c.getNombre()%></td>
                        <td><%=c.getDescripcion()%></td>
                        <td><%=c.getInstructor()%></td>
                        <td><%=c.getDuracion()%></td>
                        <td><%=c.getCategoria().getNombre()%></td>
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