package com.uduran.apiserverlet.webapp10.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.uduran.apiserverlet.webapp10.models.Carro;
import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.models.ItemCarro;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.service.CursoServiceJdbcImpl;
import com.uduran.apiserverlet.webapp10.service.ItemService;
import com.uduran.apiserverlet.webapp10.service.ProductoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemJson = req.getParameter("item");

        String decodedItemJson = URLDecoder.decode(itemJson, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(decodedItemJson, JsonObject.class);
        String tipo = jsonObject.get("tipo").getAsString();
        JsonElement itemElement = jsonObject.get("item");
        Connection conn = (Connection) req.getAttribute("conn");
        if (tipo.equals(Producto.class.getName())){
            Producto productoItem = gson.fromJson(itemElement, Producto.class);
            ItemService<Producto> productoService = new ProductoServiceJdbcImpl(conn);
            Optional<Producto> producto = productoService.porId(productoItem.getId());

            if (producto.isPresent()){
                ItemCarro item = new ItemCarro(1, producto.get());
                HttpSession session = req.getSession();
                Carro carro = (Carro) session.getAttribute("carro");
                carro.addItemCarro(item);
            }
        }
        if (tipo.equals(Curso.class.getName())){
            Curso cursoItem = gson.fromJson(itemElement, Curso.class);
            ItemService<Curso> cursoService = new CursoServiceJdbcImpl(conn);
            Optional<Curso> curso = cursoService.porId(cursoItem.getId());

            if (curso.isPresent()){
                ItemCarro item = new ItemCarro(1, curso.get());
                HttpSession session = req.getSession();
                Carro carro = (Carro) session.getAttribute("carro");
                carro.addItemCarro(item);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

}
