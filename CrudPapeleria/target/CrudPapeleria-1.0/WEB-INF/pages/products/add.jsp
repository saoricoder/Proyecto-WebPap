<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />
<c:set var="error" value="${error}" />

<t:layout title="Agregar producto">
    <div class="w-75 mx-auto">
        <h2 class="mb-5">Agregar producto</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="${rootUrl}?action=add">
            <div class="mb-5">
                <label for="name">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Nombre del producto" required />
            </div>
            <div class="mb-5">
                <label for="price">Precio</label>
                <input type="number" class="form-control" step="0.01" min="0" id="price" name="price" required />
            </div>
            <div class="mb-5">
                <label for="quantity">Cantidad</label>
                <input type="number" class="form-control" min="0" id="quantity" name="quantity" required />
            </div>
            <div class="mb-5">
                <label for="description">Descripción</label>
                <textarea id="description" class="form-control" name="description" required></textarea>
            </div>
            <div class="mb-5">
                <label for="category">Categoría</label>
                <select id="category" name="category" class="form-control" required>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="Products?action=home" class="btn btn-secondary ms-3">Cancelar</a>
            </div>
        </form>
    </div>
</t:layout>