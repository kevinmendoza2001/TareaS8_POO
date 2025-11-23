public class Auto extends Vehiculo {
    private int numeroPuertas;

    // Constructor con número de puertas
    public Auto(String marca, String modelo, int año, double precio, int numeroPuertas) {
        super(marca, modelo, año, precio);
        if (numeroPuertas <= 0) {
            throw new IllegalArgumentException("El número de puertas debe ser mayor a 0");
        }
        this.numeroPuertas = numeroPuertas;
    }

    // Sobrecarga: si no se especifican puertas, por defecto 4
    public Auto(String marca, String modelo, int año, double precio) {
        this(marca, modelo, año, precio, 4);
    }

    // Implementamos impuesto: 5% del precio
    @Override
    public double calcularImpuestoCirculacion() {
        return getPrecio() * 0.05;
    }

    @Override
    public String toString() {
        return super.toString() + " - Puertas: " + numeroPuertas;
    }
}

// verificar commit