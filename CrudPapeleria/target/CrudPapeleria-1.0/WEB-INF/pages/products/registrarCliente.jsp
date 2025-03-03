<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />
<c:set var="error" value="${error}" />

<t:layout title="Agregar nuevo cliente">
    <div class="w-75 mx-auto">
        <h2 class="mb-5">Nuevo cliente</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="${rootUrl}?action=registrarCliente">
            <div class="mb-5">
                <label for="numeroIdentificacion">Número de Identificación</label>
                <input type="text" class="form-control" id="numeroIdentificacion" name="numeroIdentificacion" placeholder="Número de identificación del cliente" required />
            </div>
            <div class="mb-5">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre completo del cliente" required />
            </div>
            <div class="mb-5">
                <label for="direccion">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Dirección del cliente" required />
            </div>
            <div class="mb-5">
                <label for="telefono">Teléfono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Número de teléfono del cliente" required />
            </div>
            <div class="mb-5">
                <label for="correo">Correo</label>
                <input type="email" class="form-control" id="correo" name="correo" placeholder="Correo electrónico del cliente" required />
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="${rootUrl}?action=home" class="btn btn-secondary ms-3">Cancelar</a>
            </div>
        </form>
    </div>
</t:layout>
