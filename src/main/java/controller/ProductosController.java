/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductoDAO;
import model.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 *
 * @author User
 */

@WebServlet("/ProductosController")
public class ProductosController extends HttpServlet {
    
    ProductoDAO productoDAO = new ProductoDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "list";
        }
        switch(action){
            case "list":
                List<Producto> lista = productoDAO.listarProductos();
                request.setAttribute("productos", lista);
                request.getRequestDispatcher("WEB-INF/pages/productos.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Producto producto = productoDAO.obtenerProducto(id);
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("WEB-INF/pages/editarProducto.jsp").forward(request, response);
                break;
            case "delete":
                int idProducto = Integer.parseInt(request.getParameter("id"));
                productoDAO.eliminarProducto(idProducto);
                response.sendRedirect("ProductosController?action=list");
                break;
            default:
                response.sendRedirect("ProductosController?action=list");
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "add";
        }
        switch(action){
            case "add":
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                Producto nuevo = new Producto();
                nuevo.setNombre_producto(nombre);
                nuevo.setDescripcion_producto(descripcion);
                nuevo.setPrecio_producto(precio);
                nuevo.setCantidad_producto(cantidad);
                productoDAO.agregarProducto(nuevo);
                response.sendRedirect("ProductosController?action=list");
                break;
            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                String nombreU = request.getParameter("nombre");
                String descripcionU = request.getParameter("descripcion");
                double precioU = Double.parseDouble(request.getParameter("precio"));
                int cantidadU = Integer.parseInt(request.getParameter("cantidad"));
                Producto prod = new Producto();
                prod.setId_producto(id);
                prod.setNombre_producto(nombreU);
                prod.setDescripcion_producto(descripcionU);
                prod.setPrecio_producto(precioU);
                prod.setCantidad_producto(cantidadU);
                productoDAO.actualizarProducto(prod);
                response.sendRedirect("ProductosController?action=list");
                break;
            default:
                response.sendRedirect("ProductosController?action=list");
                break;
        }
    }
}