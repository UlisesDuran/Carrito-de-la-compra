package com.uduran.apiserverlet.webapp10.listeners;

import com.uduran.apiserverlet.webapp10.models.Carro;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {


    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la app");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "algun valor global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la app");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Creando la sesion.");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la sesion.");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "Guardando algun valor para el request.");
        sre.getServletRequest().setAttribute("nombre", "Ulises Duran");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo el request!");
    }


}
