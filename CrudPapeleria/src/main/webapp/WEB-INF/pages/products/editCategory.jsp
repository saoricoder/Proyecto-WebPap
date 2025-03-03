<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />
<c:set var="error" value="${error}" />

<t:layout title="Editar categoría">
    <div class="w-75 mx-auto">
        <h2 class="mb-5">Editar categoría</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="${rootUrl}?action=editCategory">
            <div class="mb-5">
                <label for="name">Nombre de la categoría</label>
                <input value="${category.name}" type="text" class="form-control" id="name" name="name" placeholder="Nombre de la categoría" required />
            </div>
            <div class="d-flex justify-content-end">
                <input type="hidden" name="id" value="${category.id}" />
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="Products?action=categories" class="btn btn-secondary ms-3">Cancelar</a>
            </div>
        </form>
    </div>
</t:layout>
