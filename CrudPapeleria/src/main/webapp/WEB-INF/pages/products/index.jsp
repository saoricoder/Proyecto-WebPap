
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />

<t:layout title="Inicio">
    <div class="d-flex flex-row align-items-center justify-content-between mb-5">
        <h2 class="mb-0">Lista de productos</h2>
        <a class="btn btn-primary" href="${rootUrl}?action=add">Agregar producto</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nombre</th>
                <th scope="col">Descripción</th>
                <th scope="col">Precio</th>
                <th scope="col">Cantidad</th> <!-- Nueva columna para la cantidad -->
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td> <!-- Mostrar la cantidad del producto -->
                    <td>
                        <div class="d-flex align-items-center gap-1">
                            <a class="btn btn-secondary button-sm" href="${rootUrl}?action=edit&id=${product.id}">Editar</a>
                            <a class="btn btn-danger button-sm" onclick="deleteProduct(event)" data-target="${rootUrl}?action=delete&id=${product.id}">Eliminar</a>
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