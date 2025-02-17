/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author User
 */



@WebFilter("/*") // Filtra todas las URLs
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro (opcional)
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String loginURI = request.getContextPath() + "/pages/login.jsp";
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);
        boolean isLoginPage = request.getRequestURI().endsWith("login.jsp");

        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (loggedIn || isLoginRequest || isLoginPage) {
            chain.doFilter(request, response); // Permite el acceso
        } else {
            response.sendRedirect(loginURI); // Redirige al login
        }
    }

    @Override
    public void destroy() {
        // Limpieza del filtro (opcional)
    }
}