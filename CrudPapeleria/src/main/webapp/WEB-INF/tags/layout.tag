<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Tag utilizado para renderizar las paginas en una capa comun" pageEncoding="UTF-8"%>
<%@attribute name="title" required="false" type="java.lang.String" %>
<c:set var="rootUrl" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <title><%= (title != null ? "El mundo del Papel - " + title : "El mundo del Papel") %></title>
        
        <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
            crossorigin="anonymous" />
            
        <link 
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" 
            rel="stylesheet" />
        
        <style>
            :root {
                --primary-color: #6F4F28; 
                --secondary-color: #f3d250;
                --accent-color: #ff6b6b;
                --highlight-color: #f39c12;
                --background-light: #f5f5dc;
                --text-color: #ecf0f1; 
            }

            body {
                font-family: 'Helvetica Neue', Arial, sans-serif;
                background-color: var(--background-light);
                color: #333;
            }

            .navbar {
                background-color: var(--primary-color);
                padding: 1.2rem;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
                transition: all 0.3s ease;
            }

            .navbar-brand {
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;
                font-size: 1rem;
                font-weight: 700;
                color: var(--text-color) !important;
                text-transform: uppercase;
                letter-spacing: 1px;
                transition: all 0.3s ease;
            }

            .navbar-brand i {
                color: var(--secondary-color);
                margin-right: 10px;
            }

           
            .navbar-brand span {
                font-size: 1.2rem;
                font-weight: bold;
                letter-spacing: 1px;
                text-shadow: 1px 1px 2px rgba(0,0,0,0.5); 
            }

           
            .letra-e { color: #00FFFF; } 
            .letra-l { color: #7FFF00; } 
            .letra-m { color: #FF69B4; } 
            .letra-u { color: #FFFF00; }  
            .letra-n { color: #FF4500; } 
            .letra-d { color: #00FF00; }  
            .letra-o { color: #FF8C00; } 
            .letra-d2 { color: #FF00FF; } 
            .letra-e2 { color: #1E90FF; } 
            .letra-l2 { color: #ADFF2F; } 
            .letra-p { color: #FF1493; }  
            .letra-a { color: #00BFFF; } 
            .letra-p2 { color: #FFA500; } 
            .letra-e3 { color: #7FFFD4; } 
            .letra-l3 { color: #FFEB3B; } 
            
            .letra-space { 
                color: transparent; 
                display: inline-block;
                width: 0.5em;
            }

            .navbar-brand:hover {
                color: var(--highlight-color) !important;
                transform: translateY(-2px);
            }

            .nav-link {
                font-size: 1.1rem;
                padding: 0.8rem 1.5rem;
                margin: 0 5px;
                border-radius: 20px;
                color: var(--text-color);
                transition: all 0.3s ease;
            }

            .nav-link:hover {
                background-color: var(--highlight-color);
                color: white;
                transform: translateY(-2px);
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            }

            .nav-link.active {
                background-color: var(--accent-color);
                color: white;
            }

            .main-container {
                background-color: white;
                border-radius: 15px;
                box-shadow: 0 5px 15px rgba(0,0,0,0.1);
                padding: 2rem;
                margin-top: 3rem;
            }

            .product-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 1.5rem;
                padding: 1.5rem 0;
            }

            .category-tag {
                background-color: var(--secondary-color);
                color: #333;
                padding: 0.4rem 1rem;
                border-radius: 15px;
                font-size: 0.9rem;
                display: inline-block;
                margin: 0.3rem;
            }
        </style>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark">
                <div class="container">
                    <img src="/CrudProductos/resources/images/papeleria.png" alt="Logo" width="200" class="me-2">
                    <a class="navbar-brand" href="${rootUrl}">
                        
                        <i class="fas fa-pencil-alt"></i>
                        
                        <span class="letra-e">E</span> 
                        <span class="letra-l">L</span> 
                        
                        <span class="letra-space">&nbsp;</span>
                        
                        <span class="letra-m">M</span> 
                        <span class="letra-u">U</span>
                        <span class="letra-n">N</span>
                        <span class="letra-d">D</span>
                        <span class="letra-o">O</span>
                       
                        <span class="letra-space">&nbsp;</span>
                        
                        <span class="letra-d2">D</span> 
                        <span class="letra-e2">E</span> 
                        <span class="letra-l2">L</span>
                       
                        <span class="letra-space">&nbsp;</span>
                      
                        <span class="letra-p">P</span> 
                        <span class="letra-a">A</span> 
                        <span class="letra-p2">P</span> 
                        <span class="letra-e3">E</span> 
                        <span class="letra-l3">L</span> 
                    </a>
                    
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link text-white" href="Products?action=home">
                                    <i class="fas fa-home"></i> Inicio
                                </a>
                            </li>

                            <c:if test="${not empty sessionScope.user}">
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="Products?action=categories">
                                        <i class="fas fa-tags"></i> Categorias
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="Products?action=index">
                                        <i class="fas fa-box-open"></i> Productos
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="Products?action=clientes_lista">
                                        <i class="fas fa-users"></i> Clientes
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="Products?action=logout">
                                        <i class="fas fa-sign-out-alt"></i> Cerrar Sesión
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        
        <main class="container my-4">
            <div class="main-container fade-in">
                <jsp:doBody/>
            </div>
        </main>
        
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous"></script>
    </body>
</html>