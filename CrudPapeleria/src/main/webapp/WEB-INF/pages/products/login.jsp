<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Login">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm border-0">
                <div class="card-body p-4 p-md-5">
                    <div class="text-center mb-4">
                        <i class="fas fa-user-lock fa-3x text-muted mb-3"></i>
                        <h2 class="fw-bold" style="color: #5D4037;">Iniciar Sesión</h2>
                        <p class="text-muted">Accede a tu cuenta para gestionar productos</p>
                    </div>
                    
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger d-flex align-items-center mb-4" role="alert">
                            <i class="fas fa-exclamation-circle me-2"></i>
                            <div>${error}</div>
                        </div>
                    </c:if>
                    
                    <form method="POST" action="Products?action=login" class="needs-validation" novalidate>
                        <div class="mb-4">
                            <label for="username" class="form-label" style="color: #5D4037;">
                                <i class="fas fa-user me-2"></i>Usuario
                            </label>
                            <input type="text" class="form-control form-control-lg" 
                                   id="username" name="username" placeholder="Ingrese su usuario" required />
                            <div class="invalid-feedback">
                                Por favor ingrese su nombre de usuario
                            </div>
                        </div>
                        
                        <div class="mb-4">
                            <label for="password" class="form-label" style="color: #5D4037;">
                                <i class="fas fa-lock me-2"></i>Contraseña
                            </label>
                            <div class="input-group">
                                <input type="password" class="form-control form-control-lg" 
                                       id="password" name="password" placeholder="Ingrese su contraseña" required />
                                <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                                    <i class="fas fa-eye" id="eyeIcon"></i>
                                </button>
                            </div>
                            <div class="invalid-feedback">
                                Por favor ingrese su contraseña
                            </div>
                        </div>
                        
                        <div class="d-grid gap-2 mt-5">
                            <button type="submit" class="btn" style="background-color: #6D4C41; color: white;">
                                <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        (function() {
            'use strict';
            var forms = document.querySelectorAll('.needs-validation');
            Array.prototype.slice.call(forms).forEach(function(form) {
                form.addEventListener('submit', function(event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
        
        document.getElementById('togglePassword').addEventListener('click', function() {
            const passwordInput = document.getElementById('password');
            const eyeIcon = document.getElementById('eyeIcon');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                eyeIcon.classList.remove('fa-eye');
                eyeIcon.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                eyeIcon.classList.remove('fa-eye-slash');
                eyeIcon.classList.add('fa-eye');
            }
        });
    </script>
</t:layout>
