<%-- 
    Document   : productos
    Created on : 15 feb. 2025, 22:35:48
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos - Papelería COLIMAS</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css">
    <style>
        table { width: 80%; margin: auto; border-collapse: collapse; }
        table, th, td { border: 1px solid #ccc; }
        th, td { padding: 10px; text-align: center; }
        .actions a { margin: 0 5px; text-decoration: none; color: #4285F4; }
        .add-product { display: block; width: 80%; margin: 20px auto; text-align: right; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Lista de Productos</h2>
    <div class="add-product">
        <a href="addProducto.jsp">Agregar Nuevo Producto</a>
    </div>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Acciones</th>
        </tr>
        <% 
            List<model.Producto> productos = (List<model.Producto>) request.getAttribute("productos");
            if(productos != null){
                for(model.Producto p : productos){
        %>
        <tr>
            <td><%= p.getId_producto() %></td>
            <td><%= p.getNombre_producto() %></td>
            <td><%= p.getDescripcion_producto() %></td>
            <td><%= p.getPrecio_producto() %></td>
            <td><%= p.getCantidad_producto() %></td>
            <td class="actions">
                <a href="../ProductosController?action=edit&id=<%= p.getId_producto() %>">Editar</a>
                <a href="../ProductosController?action=delete&id=<%= p.getId_producto() %>" onclick="return confirm('¿Está seguro?')">Borrar</a>
            </td>
        </tr>
        <%      
                }
            }
        %>
    </table>
</body>
</html>
