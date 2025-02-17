/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import model.Usuario;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("username");  // Verifica que coincida con el <input name="username">
        String contraseña = request.getParameter("password"); // Verifica que coincida con el <input name="password">
        
        // Autenticación con la base de datos
        Usuario usuario = usuarioDAO.autenticar(nombre, contraseña);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            response.sendRedirect(request.getContextPath()+"/pages/dashboard.jsp"); // ✅ CORREGIDO: Elimina "webapp/"
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response); // ✅ Corrección en la redirección
        }
    }
}
