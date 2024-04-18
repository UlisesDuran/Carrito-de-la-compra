package com.uduran.apiserverlet.webapp10.controllers;

import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/comprar"})
public class ComprarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ItemService<Producto> serviceProductos = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = serviceProductos.listar();
        ItemService<Curso>  serviceCursos = new CursoServiceJdbcImpl(conn);
        List<Curso> cursos = serviceCursos.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos", productos);
        req.setAttribute("cursos", cursos);
        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
