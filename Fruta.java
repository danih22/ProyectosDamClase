package org.example;

public class Fruta {
    // Atributos
    private int id;
    private String nombre;
    private double precioKg;
    private int stockKg;

    // Constructor vacío

    public Fruta() {
    }

    // Constructor con parámetros
    public Fruta(int id, String nombre, double precioKg, int stockKg) {
        this.id = id;
        this.nombre = nombre;
        this.precioKg = precioKg;
        this.stockKg = stockKg;
    }


    // Getters y Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double precioKg) {
        this.precioKg = precioKg;
    }

    public int getStockKg() {
        return stockKg;
    }

    public void setStockKg(int stockKg) {
        this.stockKg = stockKg;
    }

    // toString


    @Override
    public String toString() {
        return "Fruta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioKg=" + precioKg +
                ", stockKg=" + stockKg +
                '}';
    }
}
