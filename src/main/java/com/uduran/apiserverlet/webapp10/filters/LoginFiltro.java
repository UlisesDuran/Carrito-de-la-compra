package com.uduran.apiserverlet.webapp10.filters;

import com.uduran.apiserverlet.webapp10.service.LoginService;
import com.uduran.apiserverlet.webapp10.service.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*"})
public class LoginFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED
                    , "No estas autorizado para ver esta pagina!");
        }
    }
}
