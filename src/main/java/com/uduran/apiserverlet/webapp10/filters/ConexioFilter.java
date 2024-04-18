package com.uduran.apiserverlet.webapp10.filters;


import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.util.ConexionBaseDatos;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebFilter("/*")
public class ConexioFilter implements Filter{
    private static final Logger logger = Logger.getLogger(ConexioFilter.class.getName());
    public void init(FilterConfig filterConfig) throws ServletException{
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try(Connection conn = ConexionBaseDatos.getInstance()){
            conn.setAutoCommit(false);

            try{
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            }catch (SQLException | ServiceJdbcException e){
                conn.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void destroy(){

    }
}
