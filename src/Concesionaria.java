import java.util.ArrayList;
import java.util.Collections;

public class Concesionaria {
    private ArrayList<Vehiculo> inventario = new ArrayList<>();

    // Agregar vehiculo validando que no sea null
    public void agregarVehiculo(Vehiculo v) {
        if (v == null) {
            throw new NullPointerException("El vehiculo no puede ser null");
        }
        inventario.add(v);
    }

    // Calcular total de impuestos
    public double calcularTotalImpuestos() {
        if (inventario.isEmpty()) {
            throw new IllegalStateException("El inventario esta vacio, no se pueden calcular impuestos");
        }
        double total = 0;
        for (Vehiculo v : inventario) {
            total += v.calcularImpuestoCirculacion();
        }
        return total;
    }
    // Obtener vehiculo más caro
    public Vehiculo obtenerVehiculoMasCaro() {
        if (inventario.isEmpty()) {
            throw new IllegalStateException("El inventario esta vacio, no se puede obtener el mas caro");
        }
        Vehiculo masCaro = inventario.get(0);
        for (Vehiculo v : inventario) {
            if (v.getPrecio() > masCaro.getPrecio()) {
                masCaro = v;
            }
        }
        return masCaro;
    }

    // Ordenar por precio (usamos sort con lambda)
    public void ordenarPorPrecio() {
        Collections.sort(inventario, (v1, v2) -> Double.compare(v1.getPrecio(), v2.getPrecio()));
    }

    // Mostrar inventario
    public void mostrarInventario() {
        for (Vehiculo v : inventario) {
            System.out.println(v);
        }
    }
}



