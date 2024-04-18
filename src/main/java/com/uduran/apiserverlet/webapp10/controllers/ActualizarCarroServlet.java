package com.uduran.apiserverlet.webapp10.controllers;

import com.uduran.apiserverlet.webapp10.models.Carro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(ActualizarCarroServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("carro")!=null){
            Carro carro = (Carro) session.getAttribute("carro");
            updateItems(req, carro);
            updateCantidades(req, carro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

    private void updateItems(HttpServletRequest request, Carro carro){
        String[] deleteItems = request.getParameterValues("deleteItems");
        if (deleteItems != null && deleteItems.length >0){
            List<String> itemsNombres = Arrays.asList(deleteItems);
            carro.removeItems(itemsNombres);
        }
    }

    private void updateCantidades(HttpServletRequest request, Carro carro){
        Enumeration<String> enumer = request.getParameterNames();
        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String nombre = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    if (Integer.parseInt(cantidad) > 0){
                        carro.updateCantidad(nombre, Integer.parseInt(cantidad));
                    }else{
                        carro.removeItem(nombre);
                    }

                }
            }
        }
    }
}

