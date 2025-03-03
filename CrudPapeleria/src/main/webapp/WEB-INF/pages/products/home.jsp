<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}/Products" />

<t:layout title="Home">
    <div class="text-center">
        <h2 class="mb-5" style="color: #2F4F4F;">Bienvenido a la página principal</h2>
        <p style="color: #6B8E23;">Este es el sitio de gestión de productos. Desde aquí puedes acceder a todas las funcionalidades principales.</p>

        
        <c:if test="${empty sessionScope.user}">
            
            <a href="${rootUrl}?action=login" class="btn glow-btn" style="background-color: #F4A300; color: white; border-radius: 30px; padding: 12px 30px; text-transform: uppercase; font-weight: bold;">Iniciar sesión</a>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            
            <div class="d-flex justify-content-center mt-4">
                <a href="${rootUrl}?action=add" class="btn glow-btn" style="background-color: #FFB6C1; color: white; border-radius: 30px; padding: 12px 30px; text-transform: uppercase; font-weight: bold; margin-right: 10px;">Agregar producto</a>
                <a href="${rootUrl}?action=index" class="btn glow-btn" style="background-color: #98FB98; color: white; border-radius: 30px; padding: 12px 30px; text-transform: uppercase; font-weight: bold; margin-right: 10px;">Ver productos</a>
                <a href="${rootUrl}?action=categories" class="btn glow-btn" style="background-color: #DDA0DD; color: white; border-radius: 30px; padding: 12px 30px; text-transform: uppercase; font-weight: bold;">Ver categorías</a>
            </div>
        </c:if>
    </div>

    
    <div id="carouselHome" class="carousel slide mt-5 w-50 mx-auto" data-bs-ride="carousel">

        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/resources/images/imagen.png" class="d-block w-100" alt="Imagen 1">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/imagen1.png" class="d-block w-100" alt="Imagen 2">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/resources/images/imagen2.png" class="d-block w-100" alt="Imagen 3">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselHome" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Anterior</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselHome" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Siguiente</span>
        </button>
    </div>
    <style>
        /* Estilo para los botones con efecto de brillo */
        .glow-btn {
            transition: all 0.3s ease;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .glow-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2), 0 0 10px rgba(255, 255, 255, 0.6);
            filter: brightness(1.2);
        }

        .glow-btn:focus {
            outline: none;
        }
    </style>
</t:layout>
