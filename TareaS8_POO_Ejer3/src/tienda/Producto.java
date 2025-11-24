package tienda;

import excepciones.PrecioInvalidoException;

public abstract class Producto {
    private String codigo;
    private String nombre;
    protected double precioBase;
    protected int stock;

    public Producto(String codigo, String nombre, double precioBase, int stock) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("Código inválido");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        if (precioBase <= 0) {
            throw new PrecioInvalidoException("El precio base debe ser mayor a 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public abstract double calcularPrecioFinal();

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - Precio base: " + precioBase + " - Stock: " + stock;
    }
}
