<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />

<t:layout title="Categories">
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
            background-color: #4CAF50; 
            color: white;
            border-radius: 25px;
            padding: 10px 25px;
            border: none;
            box-shadow: 0 4px 15px rgba(76, 175, 80, 0.2);
            transition: all 0.3s ease;
        }

        .btn-add:hover {
            background-color: #45a049;
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(76, 175, 80, 0.3);
            color: white;
        }

        .btn-edit {
            background-color: #1E90FF; 
            color: white;
            border-radius: 20px;
            padding: 8px 20px;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-edit:hover {
            background-color: #187bcd;
            transform: translateY(-2px);
            color: white;
        }

        .btn-delete {
            background-color: #FF6347; 
            color: white;
            border-radius: 20px;
            padding: 8px 20px;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-delete:hover {
            background-color: #ff4f33;
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
    <div class="d-flex flex-row align-items-center justify-content-between mb-5">
        <h2 class="mb-0">Lista de Categorías</h2>
        <a class="btn btn-add" href="${rootUrl}?action=newCategory">Agregar categoría</a>
    </div>
    <table class="product-table table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre de la categoría</th>
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <div class="d-flex align-items-center gap-1">
                            <!-- Enlace para editar categoría -->
                            <a class="btn btn-edit btn-sm" href="${rootUrl}?action=editCategory&id=${category.id}">Editar</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script async="true">
        function deleteCategory(ev) {
            const link = ev.target;
            const href = link.getAttribute('data-target');
            const confirmed = window.confirm("¿Está seguro que desea eliminar esta categoría?");
            
            if (confirmed) {
                location.href = href;
            }
        }
    </script>
</t:layout>
