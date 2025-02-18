<%-- 
    Document   : login
    Created on : 18 feb. 2025, 13:38:05
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Login">
    <div class="w-50 mx-auto">
        <h2 class="mb-5">Iniciar Sesión</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mb-5">
                ${error}
            </div>
        </c:if>
        <form method="POST" action="Products?action=login">
            <div class="mb-5">
                <label for="username">Usuario</label>
                <input type="text" class="form-control" id="username" name="username" required />
            </div>
            <div class="mb-5">
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required />
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
            </div>
        </form>
    </div>
</t:layout>
