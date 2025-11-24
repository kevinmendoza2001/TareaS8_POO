package tienda;

public class ProductoRopa extends Producto {
    private String talla;
    private String material;

    public ProductoRopa(String codigo, String nombre, double precioBase, int stock, String talla, String material) {
        super(codigo, nombre, precioBase, stock);
        if (talla == null || talla.isEmpty()) {
            throw new IllegalArgumentException("Talla inválida");
        }
        if (material == null || material.isEmpty()) {
            throw new IllegalArgumentException("Material inválido");
        }
        this.talla = talla;
        this.material = material;
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase * 1.12; // IVA 12%
    }

    @Override
    public String toString() {
        return super.toString() + " - Talla: " + talla + " - Material: " + material;
    }
}
