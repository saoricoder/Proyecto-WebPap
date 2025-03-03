<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />
<t:layout title="Inicio">
    <style>
        .product-table {
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-collapse: separate;
            border-spacing: 0;
        }

        .product-table thead {
            background-color: #47B5FF;
            color: white;
        }

        .product-table th {
            padding: 15px;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.9rem;
            letter-spacing: 0.5px;
            border: none;
        }

        .product-table td {
            padding: 15px;
            border-bottom: 1px solid #e0e0e0;
            vertical-align: middle;
        }

        .product-table tbody tr {
            background-color: white;
            transition: all 0.3s ease;
        }

        .product-table tbody tr:hover {
            background-color: #f8f9fa;
            transform: scale(1.01);
        }

        .product-table tbody tr:last-child td {
            border-bottom: none;
        }

       
        .btn-add {
            background-color: #FF4B91;
            color: white;
            border-radius: 25px;
            padding: 10px 25px;
            border: none;
            box-shadow: 0 4px 15px rgba(255, 75, 145, 0.2);
            transition: all 0.3s ease;
        }

        .btn-add:hover {
            background-color: #ff3381;
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(255, 75, 145, 0.3);
            color: white;
        }

        .btn-edit {
            background-color: #47B5FF;
            color: white;
            border-radius: 20px;
            padding: 8px 20px;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-edit:hover {
            background-color: #3da5ea;
            transform: translateY(-2px);
            color: white;
        }

        .btn-delete {
            background-color: #FF6B6B;
            color: white;
            border-radius: 20px;
            padding: 8px 20px;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-delete:hover {
            background-color: #ff5252;
            transform: translateY(-2px);
            color: white;
        }

        .page-header {
            background-color: white;
            padding: 25px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            margin-bottom: 30px;
        }

        .page-header h2 {
            color: #1B2F45;
            font-weight: 600;
            margin: 0;
        }

        .category-badge {
            background-color: #FFD93D;
            color: #1B2F45;
            padding: 5px 15px;
            border-radius: 15px;
            font-size: 0.9rem;
            font-weight: 500;
        }
    </style>

    <div class="page-header d-flex flex-row align-items-center justify-content-between">
        <h2>Lista de productos</h2>
        <a class="btn btn-add" href="${rootUrl}?action=add">
            <i class="fas fa-plus"></i> Agregar producto
        </a>
    </div>

    <table class="table product-table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre</th>
                <th scope="col">Descripción</th>
                <th scope="col">Precio</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Categoría</th>
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td><strong>${product.name}</strong></td>
                    <td>${product.description}</td>
                    <td>$${product.price}</td>
                    <td>${product.quantity}</td>
                    <td><span class="category-badge">${product.category.name}</span></td>
                    <td>
                        <div class="d-flex align-items-center gap-2">
                            <a class="btn btn-edit" href="${rootUrl}?action=edit&id=${product.id}">
                                <i class="fas fa-edit"></i> Editar
                            </a>
                            <a class="btn btn-delete" onclick="deleteProduct(event)" data-target="${rootUrl}?action=delete&id=${product.id}">
                                <i class="fas fa-trash"></i> Eliminar
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script async="true">
        function deleteProduct(ev) {
            const link = ev.target;
            const href = link.getAttribute('data-target');
            const confirmed = window.confirm("¿Está seguro que desea eliminar este producto?");
            
            if (confirmed) {
                location.href = href;
            }
        }
    </script>
</t:layout>