# Sistema de Gestión de Papelería

Sistema web para la gestión de una papelería que permite administrar productos, categorías y clientes.

## Características

- **Autenticación de Usuarios**
  - Sistema de inicio de sesión seguro
  - Gestión de sesiones

- **Gestión de Productos**
  - Visualización de productos en tabla responsive
  - Agregar nuevos productos
  - Editar productos existentes
  - Eliminar productos
  - Detalles del producto: nombre, descripción, precio, cantidad y categoría

- **Gestión de Categorías**
  - Ver todas las categorías
  - Agregar nuevas categorías
  - Editar nombres de categorías
  - Categorías vinculadas a productos

- **Gestión de Clientes**
  - Registro de clientes
  - Lista de clientes
  - Edición de información de clientes
  - Eliminación de clientes
  - Datos del cliente: número de identificación, nombre, dirección, teléfono y correo

## Tecnologías Utilizadas

- **Backend**
  - Java EE (Enterprise Edition)
  - JSP (JavaServer Pages)
  - Servlets
  - JSTL (JavaServer Pages Standard Tag Library)

- **Frontend**
  - HTML5
  - CSS3
  - JavaScript
  - Bootstrap 5
  - Iconos de Font Awesome

## Requisitos Previos

- JDK 8 o superior
- Apache Tomcat 8 o superior
- Base de datos MySQL

## Instalación y Configuración

1. Clonar el repositorio

git clone [https://github.com/saoricoder/Proyecto-WebPap.git]


# Estructura del Proyecto CrudPapeleria

```plaintext
CrudPapeleria/
├── src/
│   └── main/
│       ├── java/
│       │   └── crudpapeleria/
│       │       ├── controller/
│       │       │   └── ProductsController.java    # Controlador principal
│       │       ├── core/
│       │       │   └── Database.java              # Configuración de base de datos
│       │       ├── dao/
│       │       │   ├── ProductDao.java            # Acceso a datos de productos
│       │       │   ├── CategoryDao.java           # Acceso a datos de categorías
│       │       │   ├── ClienteDao.java            # Acceso a datos de clientes
│       │       │   └── UserDao.java               # Acceso a datos de usuarios
│       │       └── model/
│       │           ├── Product.java               # Modelo de productos
│       │           ├── Category.java              # Modelo de categorías
│       │           ├── Cliente.java               # Modelo de clientes
│       │           └── User.java                  # Modelo de usuarios
│       └── webapp/
│           └── WEB-INF/
│               ├── pages/
│               │   └── products/                   # Vistas JSP
│               │       ├── add.jsp                 # Formulario agregar producto
│               │       ├── categories.jsp          # Lista de categorías
│               │       ├── clientes_lista.jsp      # Lista de clientes
│               │       ├── edit.jsp                # Editar producto
│               │       ├── editarCliente.jsp       # Editar cliente
│               │       ├── editCategory.jsp        # Editar categoría
│               │       ├── home.jsp                # Página principal
│               │       ├── index.jsp               # Lista de productos
│               │       ├── login.jsp               # Inicio de sesión
│               │       ├── newCategory.jsp         # Nueva categoría
│               │       └── registrarCliente.jsp    # Registro de cliente
│               └── tags/                          # Carpeta para tags personalizados