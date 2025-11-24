package tienda;

public class ProductoAlimenticio extends Producto {
    private String fechaVencimiento;
    private boolean requiereRefrigeracion;

    public ProductoAlimenticio(String codigo, String nombre, double precioBase, int stock, String fechaVencimiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, precioBase, stock);
        if (fechaVencimiento == null || fechaVencimiento.isEmpty()) {
            throw new IllegalArgumentException("Fecha de vencimiento inválida");
        }
        this.fechaVencimiento = fechaVencimiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase; // IVA 0%
    }

    @Override
    public String toString() {
        return super.toString() + " - Vence: " + fechaVencimiento + " - Refrigeración: " + requiereRefrigeracion;
    }
}
