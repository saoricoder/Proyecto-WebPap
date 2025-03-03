
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />

<t:layout title="Editar Cliente">
    <div class="w-75 mx-auto">
        <h2 class="mb-5">Editar Cliente</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="${rootUrl}?action=editarCliente&id=${cliente.id}">
            <div class="mb-5">
                <label for="numeroIdentificacion">Número de Identificación</label>
                <input type="text" class="form-control" id="numeroIdentificacion" name="numeroIdentificacion" value="${cliente.numeroIdentificacion}" required />
            </div>
            <div class="mb-5">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" required />
            </div>
            <div class="mb-5">
                <label for="direccion">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.direccion}" required />
            </div>
            <div class="mb-5">
                <label for="telefono">Teléfono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" required />
            </div>
            <div class="mb-5">
                <label for="correo">Correo</label>
                <input type="email" class="form-control" id="correo" name="correo" value="${cliente.correo}" required />
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="${rootUrl}?action=clientes_lista" class="btn btn-secondary ms-3">Cancelar</a>
            </div>
        </form>
    </div>
</t:layout>