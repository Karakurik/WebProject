package ru.kpfu.webproject.fayzrakhmanov.filters;

import ru.kpfu.webproject.fayzrakhmanov.services.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.kpfu.webproject.fayzrakhmanov.constants.ServicesConstants.SECURITY_SERVICE;
import static ru.kpfu.webproject.fayzrakhmanov.constants.ServletsConstants.AUTH;

@WebFilter("*")
public class AuthFilter implements Filter {

    private String[] protectedPaths;
    private SecurityService securityService;
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedPaths = new String[]{"/login", "/registration", "/books"};
        context = filterConfig.getServletContext();
        securityService = (SecurityService) context.getAttribute(SECURITY_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (securityService.isAuthenticated(request, request.getSession())) {
            request.setAttribute(AUTH, true);
        } else {
            request.setAttribute(AUTH, false);
            boolean isProtected = false;
            for(String s : protectedPaths){
                if(request.getRequestURI().startsWith(request.getContextPath() + s)){
                    isProtected = true;
                }
            }
            if(isProtected){
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect(request.getContextPath() + "/login");

        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
