package crudpapeleria.controller;

import crudpapeleria.model.Product;
import crudpapeleria.model.User;
import crudpapeleria.dao.ProductDao;
import crudpapeleria.dao.UserDao;
import crudpapeleria.model.Category;
import crudpapeleria.dao.CategoryDao; 
import crudpapeleria.dao.ClienteDao;
import crudpapeleria.model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductsController", urlPatterns = {"/Products"})
public class ProductsController extends HttpServlet {
    private enum HttpMethod {
        POST,
        GET
    }
    private final ProductDao productDao = new ProductDao();
    private final UserDao userDao = new UserDao();
    private final CategoryDao categoryDao = new CategoryDao(); // Instancia de CategoryDao
    private final ClienteDao clienteDao = new ClienteDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod method)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "home";
        }

        // Rutas públicas
        if (action.equals("login") || action.equals("home")) {
            switch (action) {
                case "login":
                    if (method == HttpMethod.GET) {
                        showLoginForm(request, response);
                    } else {
                        login(request, response);
                    }
                    break;

                case "home": 
                    showHome(request, response);  // Método para mostrar la página de inicio
                    break;
            }
            return;
        }

        // Rutas protegidas
        if (!isAuthenticated(request)) {
            redirect(response, "Products?action=login");
            return;
        }

        switch (action) {
            case "index":
                showProducts(request, response);
                break;

            case "clientes_lista":  // Nueva acción para listar clientes
                showClientsList(request, response);
                break;

            case "add":
                if (method == HttpMethod.GET) {
                    showProductsForm(request, response);
                } else {
                    addProduct(request, response);
                }
                break;

            case "newCategory":
                if (method == HttpMethod.GET) {
                    showCategoryForm(request, response);
                } else {
                    newCategory(request, response);
                }
                break;

            case "edit":
                if (method == HttpMethod.GET) {
                    showProductsEditForm(request, response);
                } else {
                    updateProduct(request, response);
                }
                break;

            case "editCategory":
                if (method == HttpMethod.GET) {
                    showCategoryEditForm(request, response);
                } else {
                    updateCategory(request, response);
                }
                break;

            case "registrarCliente":  // Nueva acción para agregar cliente
                if (method == HttpMethod.GET) {
                    showClientForm(request, response);
                } else {
                    registrarCliente(request, response);
                }
                break;

            case "editarCliente":  // Nueva acción para editar cliente
                if (method == HttpMethod.GET) {
                    showEditClientForm(request, response); // Mostrar formulario de edición
                } else {
                    updateClient(request, response); // Actualizar cliente en la base de datos
                }
                break;

            case "eliminarCliente":  // Nueva acción para eliminar cliente
                deleteClient(request, response);
                break;

            case "delete":
                deleteProduct(request, response);
                break;

            case "categories":
                showCategories(request, response);
                break;

            case "logout":
                logout(request, response);
                break;

            default:
                dispatch(request, response, "index");
                break;
        }
    }

    
    private void showHome(HttpServletRequest request, HttpServletResponse response) {
        dispatch(request, response, "home"); 
    }
    
    private void showCategories(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryDao.getAllCategories();
        request.setAttribute("categories", categories);
        dispatch(request, response, "categories"); 
    }
    
    
       // Mostrar formulario de agregar cliente
    private void showClientForm(HttpServletRequest request, HttpServletResponse response) {
        dispatch(request, response, "registrarCliente");
    }
    //Metodo editar cliente
    private void showEditClientForm(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (id == null) {
            redirect(response, "Products?action=clientes_lista");
            return;
        }

        Cliente cliente = clienteDao.getClienteById(id);
        if (cliente == null) {
            redirect(response, "Products?action=clientes_lista");
            return;
        }

        request.setAttribute("cliente", cliente);
        dispatch(request, response, "editarCliente");
    }
    
    private void updateClient(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (id == null) {
            redirect(response, "Products?action=clientes_lista");
            return;
        }

        String numeroIdentificacion = request.getParameter("numeroIdentificacion");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Cliente cliente = new Cliente(numeroIdentificacion, nombre, direccion, telefono, correo);
        cliente.setId(id);

        boolean isUpdated = clienteDao.actualizarCliente(cliente);

        if (isUpdated) {
            redirect(response, "Products?action=clientes_lista");
        } else {
            request.setAttribute("error", "Error al actualizar el cliente.");
            showEditClientForm(request, response);
        }
    }
    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) {
    try {
        String numeroIdentificacion = request.getParameter("numeroIdentificacion");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Cliente cliente = new Cliente(numeroIdentificacion, nombre, direccion, telefono, correo);
        boolean isAdded = clienteDao.registrarCliente(cliente);

        if (isAdded) {
            response.sendRedirect("Products?action=clientes_lista"); // Redirección en éxito
        } else {
            request.setAttribute("error", "Error al agregar el cliente.");
            request.getRequestDispatcher("clientes_lista").forward(request, response);
        }
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Ocurrió un error inesperado.");
        try {
            request.getRequestDispatcher("clientes_lista").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    private void deleteClient(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (id == null) {
            redirect(response, "Products?action=clientes_lista");
            return;
        }

        boolean isDeleted = clienteDao.eliminarCliente(id);

        if (isDeleted) {
            redirect(response, "Products?action=clientes_lista");
        } else {
            request.setAttribute("error", "Error al eliminar el cliente.");
            showClientsList(request, response);
        }
    }
    private void showCategoryForm(HttpServletRequest request, HttpServletResponse response) {
        dispatch(request, response, "newCategory"); 
    }

    // Método para mostrar el formulario de login
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        dispatch(request, response, "login");
    }

    // Método para autenticar al usuario
    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.authenticate(username, password);

        if (user != null) {
            // Guardar el usuario en la sesión
            request.getSession().setAttribute("user", user);
            redirect(response, "Products");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            dispatch(request, response, "login");
        }
    }

    // Método para cerrar sesión
    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate(); // Invalidar la sesión
        redirect(response, "Products?action=login");
    }

    // Método para verificar si el usuario está autenticado
    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

   


        // Mostrar todos los productos
    private void showProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        dispatch(request, response, "index");
    }

        // Mostrar lista de clientes
    private void showClientsList(HttpServletRequest request, HttpServletResponse response) {
        List<Cliente> clientes = clienteDao.getAllClientes();
        request.setAttribute("clientes", clientes);
        dispatch(request, response, "clientes_lista");
    }
    // Mostrar el formulario de agregar producto
    private void showProductsForm(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryDao.getAllCategories(); // Obtener todas las categorías
        request.setAttribute("categories", categories);
        dispatch(request, response, "add");
    }
    
    
    private void showCategoryEditForm(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {}

        if (id == null) {
            redirect(response, "Products?action=categories");
            return;
        }

        Category category = categoryDao.getCategory(id);
        if (category == null) {
            redirect(response, "Products?action=categories");
            return;
        }

        request.setAttribute("category", category);
        dispatch(request, response, "editCategory");
    }


    // Mostrar formulario de editar producto
    private void showProductsEditForm(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {}

        if (id == null) {
            redirect(response, "Products");
        }

        Product product = productDao.getProduct(id);
        if (product == null) {
            redirect(response, "Products");
        }

        List<Category> categories = categoryDao.getAllCategories(); // Obtener todas las categorías
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        dispatch(request, response, "edit");
    }
    
    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");

        Integer id = null;
        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {}

        if (id == null || name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "El nombre de la categoría no puede estar vacío.");
            showCategoryEditForm(request, response);
            return;
        }

        Category category = new Category(id, name);
        boolean success = categoryDao.updateCategory(category);

        if (success) {
            redirect(response, "Products?action=categories");
        } else {
            request.setAttribute("error", "Ocurrió un error al actualizar la categoría.");
            showCategoryEditForm(request, response);
        }
    }

    
    

    // Actualizar un producto
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");
        String categoryIdParam = request.getParameter("category");

        Integer id = null;
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = 0.0;
        int quantity = 0;
        int categoryId = 0;

        try {
            price = Double.parseDouble(priceParam);
            quantity = Integer.parseInt(quantityParam);
            id = Integer.parseInt(idParam);
            categoryId = Integer.parseInt(categoryIdParam);
        } catch (Exception ex) {}

        if (id == null) {
            redirect(response, "Products");
        }

        try {
            Category category = categoryDao.getCategory(categoryId); // Obtener la categoría por ID
            Product product = new Product(name, description, price, quantity, category);
            productDao.updateProduct(id, product);
            redirect(response, "Products");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            request.setAttribute("error", "Ocurrió un error al intentar editar el producto");
            dispatch(request, response, "edit");
        }
    }

    private void newCategory(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("name");

        if (categoryName == null || categoryName.trim().isEmpty()) {
            request.setAttribute("error", "El nombre de la categoría no puede estar vacío.");
            dispatch(request, response, "newCategory");
            return;
        }

        Category category = new Category(categoryName);
        boolean success = categoryDao.createCategory(category);

        if (success) {
            redirect(response, "Products?action=categories");
        } else {
            request.setAttribute("error", "Ocurrió un error al agregar la categoría.");
            dispatch(request, response, "newCategory");
        }
    }    

// Agregar un producto
    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");
        String categoryIdParam = request.getParameter("category");
        double price = 0.0;
        int quantity = 0;
        int categoryId = 0;

        try {
            price = Double.parseDouble(priceParam);
            quantity = Integer.parseInt(quantityParam);
            categoryId = Integer.parseInt(categoryIdParam);
        } catch (Exception ex) {}

        try {
            Category category = categoryDao.getCategory(categoryId); // Obtener la categoría por ID
            Product product = new Product(name, description, price, quantity, category);
            productDao.createProduct(product);
            redirect(response, "Products");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            request.setAttribute("error", "Ocurrió un error al intentar agregar el producto");
            dispatch(request, response, "add");
        }
    }


    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {}

        if (id == null) {
            redirect(response, "index");
        }

        productDao.deleteProduct(id);
        redirect(response, "Products");
    }

    private String viewPath(String viewName) {
        return String.format("WEB-INF/pages/products/%s.jsp", viewName);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String viewName) {
        try {
            request.getRequestDispatcher(viewPath(viewName)).forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void redirect(HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, HttpMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, HttpMethod.POST);
    }

    @Override
    public String getServletInfo() {
        return "Products controller servlet";
    }
}