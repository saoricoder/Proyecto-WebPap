<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />

<t:layout title="Lista de Clientes">
    <div class="w-100 mx-auto">
        <h2 class="mb-5">Clientes</h2>
        <c:if test="${not empty clientes}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Número de Identificación</th>
                        <th>Nombre</th>
                        <th>Dirección</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${clientes}">
                        <tr>
                            <td>${cliente.id}</td>
                            <td>${cliente.numeroIdentificacion}</td>
                            <td>${cliente.nombre}</td>
                            <td>${cliente.direccion}</td>
                            <td>${cliente.telefono}</td>
                            <td>${cliente.correo}</td>
                            <td>
                                <a href="${rootUrl}?action=editarCliente&id=${cliente.id}" class="btn btn-warning btn-sm">Editar</a>
                                <a href="${rootUrl}?action=eliminarCliente&id=${cliente.id}" class="btn btn-danger btn-sm ms-3" onclick="return confirm('¿Estás seguro de eliminar este cliente?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty clientes}">
            <div class="alert alert-info">
                No hay clientes registrados.
            </div>
        </c:if>

        <div class="d-flex justify-content-end mt-4">
            <a href="${rootUrl}?action=registrarCliente" class="btn btn-primary">Agregar Cliente</a>
        </div>
    </div>
</t:layout>