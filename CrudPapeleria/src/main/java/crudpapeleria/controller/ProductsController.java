package crudpapeleria.controller;

import crudpapeleria.model.Product;
import crudpapeleria.model.User;
import crudpapeleria.dao.ProductDao;
import crudpapeleria.dao.UserDao;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod method)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        // Rutas públicas
        if (action.equals("login") || action.equals("register")) {
            switch (action) {
                case "login":
                    if (method == HttpMethod.GET) {
                        showLoginForm(request, response);
                    } else {
                        login(request, response);
                    }
                    break;
                case "register":
                    if (method == HttpMethod.GET) {
                        dispatch(request, response, "register");
                    } else {
                        register(request, response); // Llamada al método register
                    }
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
            case "add":
                if (method == HttpMethod.GET) {
                    showProductsForm(request, response);
                } else {
                    addProduct(request, response);
                }
                break;
            case "edit":
                if (method == HttpMethod.GET) {
                    showProductsEditForm(request, response);
                } else {
                    updateProduct(request, response);
                }
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                dispatch(request, response, "index");
                break;
        }
    }

    // Método para registrar un nuevo usuario
    private void register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);
        boolean isRegistered = userDao.register(user);

        if (isRegistered) {
            redirect(response, "Products?action=login");
        } else {
            request.setAttribute("error", "Error al registrar el usuario");
            dispatch(request, response, "register");
        }
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

    // Métodos existentes para manejar productos
    private void showProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        dispatch(request, response, "index");
    }

    private void showProductsForm(HttpServletRequest request, HttpServletResponse response) {
        dispatch(request, response, "add");
    }

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

        request.setAttribute("product", product);
        dispatch(request, response, "edit");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");

        Integer id = null;
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = 0.0;
        int quantity = 0;

        try {
            price = Double.parseDouble(priceParam);
            quantity = Integer.parseInt(quantityParam);
            id = Integer.parseInt(idParam);
        } catch (Exception ex) {}

        if (id == null) {
            redirect(response, "Products");
        }

        try {
            Product product = new Product(name, description, price, quantity);
            productDao.updateProduct(id, product);
            redirect(response, "Products");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            request.setAttribute("error", "Ocurrió un error al intentar editar el producto");
            dispatch(request, response, "edit");
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");
        double price = 0.0;
        int quantity = 0;

        try {
            price = Double.parseDouble(priceParam);
            quantity = Integer.parseInt(quantityParam);
        } catch (Exception ex) {}

        try {
            Product product = new Product(name, description, price, quantity);
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