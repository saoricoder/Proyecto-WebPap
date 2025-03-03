<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />
<c:set var="error" value="${error}" />

<t:layout title="Editar producto">
    <div class="w-75 mx-auto">
        <h2 class="mb-5">Editar producto</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="${rootUrl}?action=edit">
            <div class="mb-5">
                <label for="name">Nombre</label>
                <input value="${product.name}" type="text" class="form-control" id="name" name="name" placeholder="Nombre del producto" required />
            </div>
            <div class="mb-5">
                <label for="price">Precio</label>
                <input value="${product.price}" type="number" class="form-control" step="0.01" min="0" id="price" name="price" required />
            </div>
            <div class="mb-5">
                <label for="quantity">Cantidad</label>
                <input value="${product.quantity}" type="number" class="form-control" min="0" id="quantity" name="quantity" required />
            </div>
            <div class="mb-5">
                <label for="description">Descripción</label>
                <textarea id="description" class="form-control" name="description" required>${product.description}</textarea>
            </div>
            <div class="mb-5">
                <label for="category">Categoría</label>
                <select id="category" name="category" class="form-control" required>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex justify-content-end">
                <input type="hidden" name="id" value="${product.id}" />
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="Products?" class="btn btn-secondary ms-3">Cancelar</a>
            </div>
        </form>
    </div>
</t:layout>