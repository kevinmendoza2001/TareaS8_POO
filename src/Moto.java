public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String marca, String modelo, int anio, double precio, int cilindrada) {
        super(marca, modelo, anio, precio);
        if (cilindrada <= 0) {
            throw new IllegalArgumentException("La cilindrada debe ser mayor a 0");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public double calcularImpuestoCirculacion() {
        if (cilindrada <= 250) {
            return getPrecio() * 0.02;
        } else {
            return getPrecio() * 0.04;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - Cilindrada: " + cilindrada + "cc";
    }
}

