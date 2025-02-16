/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author User
 */

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");
        // Se podría usar el id almacenado en sesión
        if(usuario != null){
            Usuario u = usuarioDAO.obtenerUsuario(usuario.getId());
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("WEB-INF/views/perfil.jsp").forward(request, response);
        } else {
            response.sendRedirect("WEB-INF/views/login.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String contraseña = request.getParameter("contraseña");
        Usuario usuario = new Usuario(id, nombre, contraseña);
        usuarioDAO.actualizarUsuario(usuario);
        response.sendRedirect("UsuarioController");
    }
}
