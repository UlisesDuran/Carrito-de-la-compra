<%@page contentType="text/hmtl" pageEncoding="UTF-8" import="java.util.*, com.uduran.apiserverlet.webapp10.models.*"%>

<%
    Object categoriasObj = request.getAttribute("categorias");
    List<Categoria> categorias = new ArrayList<>();

    if (categoriasObj instanceof List<?>){
        for (Object item: (List<?>) categoriasObj){
            if (item instanceof Categoria){
                categorias.add((Categoria) item);
            }
        }
    } else {
        throw new ClassCastException("El atributo categorias no es una lista");
    }

    //List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Formulario cursos</title>
</head>
<body>
    <h1>Formulario cursos</h1>
    <form action="<%=request.getContextPath()%>/item/form" method="post">
        <div>
            <label for="nombre">Nombre</label>
            <div>
                <input type="text" name="nombre" id="nombre"/>
            </div>
            <% if (errores !=null && errores.containsKey("nombre")){ %>
            <div style="color:red"><%=errores.get("nombre")%></div>
            <% } %>
        </div>
        <div>
            <label for="descripcion">Descripción</label>
            <div>
                <input type="text" name="descripcion" id="descripcion"/>
            </div>
            <% if (errores !=null && errores.containsKey("descripcion")){ %>
            <div style="color:red"><%=errores.get("descripcion")%></div>
            <% } %>
        </div>
        <div>
            <label for="instructor">Instructor</label>
            <div>
                <input type="text" name="instructor" id="instructor"/>
            </div>
            <% if (errores !=null && errores.containsKey("instructor")){ %>
            <div style="color:red"><%=errores.get("instructor")%></div>
            <% } %>
        </div>
        <div>
            <label for="duracion">Duración</label>
            <div>
                <input type="number" name="duracion" id="duracion"/>
            </div>
            <% if (errores !=null && errores.containsKey("duracion")){ %>
            <div style="color:red"><%=errores.get("duracion")%></div>
            <% } %>
        </div>
        <div>
            <label for="precio">Precio</label>
            <div>
                <input type="number" name="precio" id="precio"/>
            </div>
            <% if (errores !=null && errores.containsKey("precio")){ %>
            <div style="color:red"><%=errores.get("precio")%></div>
            <% } %>
        </div>
        <div>
            <label for="sku">SKU</label>
            <div>
                <input type="text" name="sku" id="sku"/>
            </div>
            <% if (errores !=null && errores.containsKey("sku")){ %>
            <div style="color:red"><%=errores.get("sku")%></div>
            <% } %>
        </div>
        <div>
            <label for="fecha_registro">Fecha registro</label>
            <div>
                <input type="date" name="fecha_registro" id="fecha_registro"/>
            </div>
            <% if (errores !=null && errores.containsKey("fecha_registro")){ %>
            <div style="color:red"><%=errores.get("fecha_registro")%></div>
            <% } %>
        </div>
        <div>
            <label for="categoria">Categoría</label>
            <div>
                <select name="categoria" id="categoria">
                    <option value="5">Cursos</option>
                </select>
            </div>
        </div>
    </form>
</body>
</html>