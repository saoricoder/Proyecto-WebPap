<%-- 
    Document   : dashboard
    Created on : 15 feb. 2025, 22:34:47
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
    <%
        // Verificar si hay una sesión activa
        Usuario usuario = (Usuario) session.getAttribute("user");
        if (usuario == null) {
            // Si no hay sesión, redirigir al login
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        }
    %>

    <header>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo Papelería" height="50">
        </div>
        <nav>
            <ul>
                <li><a href="#">Categorías</a></li>
                <li><a href="${pageContext.request.contextPath}/ProductosController?action=list">Productos</a></li>
                <li><a href="${pageContext.request.contextPath}/UsuarioController">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/LogoutController">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>

    <div class="banner">
        Bienvenido, <%= usuario != null ? usuario.getNombre() : "Invitado" %>
    </div>

    <div class="content">
        <h2>Dashboard</h2>
        <p>Aquí se muestran las categorías y productos destacados.</p>
    </div>
</body>
</html>