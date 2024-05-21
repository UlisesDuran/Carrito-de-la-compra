package com.uduran.apiserverlet.webapp10.controllers;

import com.uduran.apiserverlet.webapp10.models.Categoria;
import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.service.CategoriaServiceJdbcImpl;
import com.uduran.apiserverlet.webapp10.service.CursoServiceJdbcImpl;
import com.uduran.apiserverlet.webapp10.service.ItemService;
import com.uduran.apiserverlet.webapp10.service.ProductoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
@WebServlet({"/item/form", "/item/form/categoria"})
public class ItemFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ItemService<Categoria> service = new CategoriaServiceJdbcImpl(conn);
        Categoria categoria = new Categoria();
        long categoriaId;
        try{
            categoriaId = Long.parseLong(req.getParameter("categoriaId"));
            if (service.porId(categoriaId).isPresent()){
                categoria = service.porId(categoriaId).get();
            }

        } catch (NullPointerException e){
            categoriaId = 0L;
        }
        req.setAttribute("categoria", categoria);
        req.setAttribute("categorias", service.listar());
        if (categoriaId==5){
            getServletContext().getRequestDispatcher("/form-cursos.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/form-productos.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ItemService<Producto> productoService = new ProductoServiceJdbcImpl(conn);
        ItemService<Curso> cursoService = new CursoServiceJdbcImpl(conn);
        Map<String, String> errores = new HashMap<>();
        String nombre = req.getParameter("nombre");
        Double precio;
        String sku = req.getParameter("sku");
        Long categoria_id = Long.parseLong(req.getParameter("categoria"));
        String fechaStr = req.getParameter("fecha_registro");
        if (nombre == null || nombre.isBlank()){
            errores.put("nombre", "El nombre no puedes estar vacio.");
        }
        if (sku == null || sku.isBlank()){
            errores.put("sku", "El sku es requerido.");
        } else if (sku.length()>10){
            errores.put("sku", "El sku no puede tener mas de 10 caracteres.");
        }
        if (fechaStr == null || fechaStr.isBlank()){
            errores.put("fecha_registro", "La fecha de registro es requerida.");
        }
        Long categoria;
        try {
            categoria = Long.parseLong(req.getParameter("categoria"));
        } catch (NullPointerException e){
            categoria = 0L;
            e.printStackTrace();
        }
        if (categoria.equals(0L)){
            errores.put("categoria", "La categoria es requerida.");
        }
        if (categoria==5){
            Curso curso = new Curso();
            Categoria c1 = new Categoria();
            String descripcion = req.getParameter("descripcion");
            String instructor = req.getParameter("instructor");
            Double duracion;
            try {
                duracion = Double.parseDouble(req.getParameter("duracion"));
                precio = Double.parseDouble(req.getParameter("precio"));
            }catch (NumberFormatException e){
                duracion = 0.0;
                precio = 0.0;
            }
            if (descripcion == null || descripcion.isBlank()){
                errores.put("descripcion", "La descripcion es requerida.");
            }
            if (instructor == null || instructor.isBlank()){
                errores.put("instructor", "el instructor es requerido.");
            }
            if (precio.equals(0.0)){
                errores.put("precio", "El precio es requerido.");
            }
            if (duracion.equals(0.0)){
                errores.put("duracion", "Debes predeterminar una duracion para el curso.");
            }

            if (!errores.isEmpty()){
                req.setAttribute("errores", errores);
                doGet(req, resp);
            }else{
                LocalDate fecha_registro = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                curso.setNombre(nombre);
                curso.setPrecio(precio);
                curso.setSku(sku);
                curso.setDescripcion(descripcion);
                curso.setDuracion(duracion);
                curso.setInstructor(instructor);
                curso.setFechaRegistro(fecha_registro);
                c1.setId(Long.parseLong(req.getParameter("categoria")));
                curso.setCategoria(c1);
                cursoService.guardar(curso);
                resp.sendRedirect(req.getContextPath() + "/comprar/cursos");
            }

        }else{
            Producto producto = new Producto();
            Categoria c2 = new Categoria();
            try {
                precio = Double.parseDouble(req.getParameter("precio"));
            } catch (NumberFormatException e){
                precio = 0.0;
            }
            if (precio.equals(0.0)){
                errores.put("precio", "El precio es requerido.");
            }
            if (!errores.isEmpty()){
                req.setAttribute("errores", errores);
                doGet(req, resp);
            }else{
                LocalDate fecha_registro = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setSku(sku);
                c2.setId(categoria_id);
                producto.setCategoria(c2);
                producto.setFechaRegistro(fecha_registro);
                productoService.guardar(producto);
                resp.sendRedirect(req.getContextPath() + "/comprar/productos");
            }

        }

    }
}
