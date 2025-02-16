<%-- 
    Document   : perfil
    Created on : 15 feb. 2025, 22:36:44
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        form { width: 50%; margin: auto; }
        input { width: 100%; padding: 10px; margin: 5px 0; }
        button { padding: 10px 20px; background: #4285F4; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .logout { text-align: center; margin-top: 20px; }\n    </style>
</head>
<body>
    <h2 style="text-align:center;">Perfil de Usuario</h2>
    <% model.Usuario usuario = (model.Usuario) request.getAttribute("usuario"); %>
    <form action="../UsuarioController" method="post">
        <input type="hidden" name="id" value="<%= usuario.getId() %>">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= usuario.getNombre() %>" required>
        <label>Contraseña:</label>
        <input type="password" name="contraseña" value="<%= usuario.getContrasena() %>" required>
        <button type="submit">Actualizar Perfil</button>
    </form>
    <div class="logout">
        <a href="../LogoutController">Cerrar Sesión</a>
    </div>
</body>
</html>
