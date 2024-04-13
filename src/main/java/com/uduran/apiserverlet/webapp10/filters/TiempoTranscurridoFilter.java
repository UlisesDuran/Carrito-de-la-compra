package com.uduran.apiserverlet.webapp10.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter({"/carro/*"})
public class TiempoTranscurridoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Long inicio = System.currentTimeMillis();
        chain.doFilter(request, response);
        Long fin = System.currentTimeMillis();
        long tiempoTranscurrido = fin - inicio;
        ServletContext servletContext = request.getServletContext();
        servletContext.log("El tiempo transcurrido es: " + tiempoTranscurrido + "ms");
    }

}
