public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private double precio;

    // Constructor con validaciones
    public Vehiculo(String marca, String modelo, int anio, double precio) {
        // Validamos que marca y modelo no sean null ni vacíos
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("La marca no puede ser null ni vacia");
        }
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede ser null ni vacio");
        }
        // Validamos rango del año
        if (anio < 1900 || anio > 2025) {
            throw new IllegalArgumentException("El año debe estar entre 1900 y 2025");
        }
        // Validamos precio positivo
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
    }

    // Método abstracto: cada subclase lo implementará a su manera
    public abstract double calcularImpuestoCirculacion();

    // Getter para precio (lo usaremos en Concesionaria)
    public double getPrecio() {
        return precio;
    }

    // toString para mostrar información básica
    @Override
    public String toString() {
        return marca + " " + modelo + " (" + anio + ") - Precio: " + precio;
    }
}




