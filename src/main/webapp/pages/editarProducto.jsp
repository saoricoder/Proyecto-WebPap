<%-- 
    Document   : editarProducto
    Created on : 15 feb. 2025, 22:36:29
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        form { width: 50%; margin: auto; }
        input, textarea { width: 100%; padding: 10px; margin: 5px 0; }
        button { padding: 10px 20px; background: #4285F4; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Editar Producto</h2>
    <% model.Producto producto = (model.Producto) request.getAttribute("producto"); %>
    <form action="../ProductosController" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= producto.getId_producto() %>">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= producto.getNombre_producto() %>" required>
        <label>Descripción:</label>
        <textarea name="descripcion" required><%= producto.getDescripcion_producto() %></textarea>
        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="<%= producto.getPrecio_producto() %>" required>
        <label>Cantidad:</label>
        <input type="number" name="cantidad" value="<%= producto.getCantidad_producto() %>" required>
        <button type="submit">Actualizar Producto</button>
    </form>
</body>
</html>
