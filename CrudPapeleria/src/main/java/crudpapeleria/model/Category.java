/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudpapeleria.model;

/**
 *
 * @author USUARIO
 */
public class Category {
    private int id; // id_categoria en la base de datos
    private String name; // nombre_categoria en la base de datos

    // Constructor sin ID (para cuando se crea una nueva categoría)
    public Category(String name) {
        this.name = name;
    }

    // Constructor con todos los atributos
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
