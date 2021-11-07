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
        protectedPaths = new String[]{"/crudPanel", "/books"};
        context = filterConfig.getServletContext();
        securityService = (SecurityService) context.getAttribute(SECURITY_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().equals("/")) {
            response.sendRedirect("/books");
            return;
        }

        if (securityService.isAuthenticated(request, request.getSession())) {
            request.setAttribute(AUTH, true);
            //если не админ, то не пускаем в панель
            if (request.getRequestURI().startsWith("/crudPanel") && !securityService.isAdmin(request)) {
                response.sendRedirect("/books");
                return;
            }
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute(AUTH, false);
            boolean isProtected = false;
            for(String s : protectedPaths){
                if(request.getRequestURI().startsWith(s)){
                    isProtected = true;
                }
            }
            if(!isProtected){
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
