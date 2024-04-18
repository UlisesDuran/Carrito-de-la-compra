package com.uduran.apiserverlet.webapp10.controllers;

import com.uduran.apiserverlet.webapp10.service.LoginService;
import com.uduran.apiserverlet.webapp10.service.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            getServletContext().getRequestDispatcher("/carrito-compra.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
