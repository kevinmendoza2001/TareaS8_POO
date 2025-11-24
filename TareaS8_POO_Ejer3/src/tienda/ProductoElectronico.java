package tienda;

public class ProductoElectronico extends Producto {
    private String marca;
    private int garantiaMeses;

    public ProductoElectronico(String codigo, String nombre, double precioBase, int stock, String marca, int garantiaMeses) {
        super(codigo, nombre, precioBase, stock);
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("Marca inválida");
        }
        if (garantiaMeses <= 0) {
            throw new IllegalArgumentException("Garantía inválida");
        }
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase * 1.12; // IVA 12%
    }

    public boolean aplicarGarantia() {
        return garantiaMeses == 12;
    }

    public double aplicarGarantia(int mesesExtras) {
        if (mesesExtras <= 0) {
            throw new IllegalArgumentException("Meses extras inválidos");
        }
        return mesesExtras * 10.0; // costo adicional arbitrario
    }

    @Override
    public String toString() {
        return super.toString() + " - Marca: " + marca + " - Garantía: " + garantiaMeses + " meses";
    }
}
