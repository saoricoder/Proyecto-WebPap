<%-- 
    Document   : dashboard
    Created on : 15 feb. 2025, 22:34:47
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        header {
            background: #4285F4;
            color: white;
            padding: 10px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        nav ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 0;
        }
        nav ul li {
            margin-right: 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        .banner {
            background: url('../resources/images/banner.jpg') no-repeat center center;
            background-size: cover;
            height: 300px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 32px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <header>
        <div class="logo">
            <img src="../resources/images/logo.png" alt="Logo Papelería" height="50">
        </div>
        <nav>
            <ul>
                <li><a href="#">Categorías</a></li>
                <li><a href="../ProductosController?action=list">Productos</a></li>
                <li><a href="../UsuarioController">Perfil</a></li>
                <li><a href="../LogoutController">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <div class="banner">
        Bienvenido a Papelería COLIMAS
    </div>
    <div class="content" style="text-align:center; padding:20px;">
        <h2>Dashboard</h2>
        <p>Aquí se muestran las categorías y productos destacados.</p>
    </div>
</body>
</html>
