<%-- 
    Document   : login
    Created on : 15 feb. 2025, 22:34:31
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        body {
            background: url('../resources/images/fondo.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
        }
        .login-container {
            width: 300px;
            margin: 100px auto;
            background: rgba(255,255,255,0.9);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px #000;
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container input[type=text], 
        .login-container input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #4285F4;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #4285F4;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form action="${pageContext.request.contextPath}/LoginController" method="post">
            <input type="text" name="username" placeholder="Usuario" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <button type="submit">Iniciar Sesión</button>
            
            <!-- Mensaje de error -->
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
        </form>
        <a href="#">Recuperar Contraseña</a>
        <a href="#">Registrarse</a>
        <% if(request.getParameter("error") != null){ %>
            <p style="color:red; text-align:center;"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>
