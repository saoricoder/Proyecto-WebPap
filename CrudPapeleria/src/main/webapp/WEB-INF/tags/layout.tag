<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Tag utilizado para renderizar las paginas en una capa comun" pageEncoding="UTF-8"%>

<%@attribute name="title" required="false" type="java.lang.String" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}" />

<!<!doctype html>
<html lang="es">
    <head>
        <title><%= (title != null ? "Crud productos - " + title : "Crud productos") %></title>
        
        <!-- Bootsrap css -->
        <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
            crossorigin="anonymous" />
    </head>
    <body>
        <header>
            <nav class="navbar bg-primary" data-bs-theme="dark">
                <div class="container-fluid">
                  <a class="navbar-brand" href="${rootUrl}">Productos</a>
                </div>
            </nav>
        </header>
        
        <main class="container my-5">
            <jsp:doBody/>
        </main>
        
        <!-- Bootsrap javascript -->
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous"></script>
    </body>
</html>