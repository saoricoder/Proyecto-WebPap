package crudpapeleria.model;

public class Product {
    private int id; // id_producto en la base de datos
    private String name; // nombre_producto en la base de datos
    private String description; // descripcion_producto en la base de datos
    private double price; // precio_producto en la base de datos
    private int quantity; // cantidad_producto en la base de datos
    private Category category; // Relación con la categoría

    // Constructor vacío
    public Product() {}

    // Constructor con todos los atributos
    public Product(int id, String name, String description, double price, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Constructor sin el ID (útil para crear nuevos productos)
    public Product(String name, String description, double price, int quantity, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter y Setter para la categoría
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category.getName() + // Mostramos el nombre de la categoría
                '}';
    }
}