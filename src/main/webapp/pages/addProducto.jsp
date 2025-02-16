<%-- 
    Document   : addProducto
    Created on : 15 feb. 2025, 22:36:11
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        form { width: 50%; margin: auto; }
        input, textarea { width: 100%; padding: 10px; margin: 5px 0; }
        button { padding: 10px 20px; background: #4285F4; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Agregar Nuevo Producto</h2>
    <form action="../ProductosController" method="post">
        <input type="hidden" name="action" value="add">
        <label>Nombre:</label>
        <input type="text" name="nombre" required>
        <label>Descripción:</label>
        <textarea name="descripcion" required></textarea>
        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" required>
        <label>Cantidad:</label>
        <input type="number" name="cantidad" required>
        <button type="submit">Agregar Producto</button>
    </form>
</body>
</html>
