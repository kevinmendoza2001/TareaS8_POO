public class Main {
    public static void main(String[] args) {
        Concesionaria concesionaria = new Concesionaria();

        // Probar excepciones en constructores
        try {
            Vehiculo v1 = new Auto("Toyota", "Corolla", 1800, 15000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        try {
            Vehiculo v2 = new Moto("Honda", "CBR", 2020, -5000, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        concesionaria.agregarVehiculo(new Auto("Toyota", "Corolla", 2020, 15000));
        concesionaria.agregarVehiculo(new Auto("Ford", "Focus", 2021, 18000, 5));
        concesionaria.agregarVehiculo(new Moto("Yamaha", "YZF", 2019, 12000, 250));
        concesionaria.agregarVehiculo(new Moto("Suzuki", "GSX", 2022, 14000, 600));
        concesionaria.agregarVehiculo(new Camion("Volvo", "FH", 2018, 50000, 20));
        concesionaria.agregarVehiculo(new Camion("Mercedes", "Actros", 2021, 60000, 25));


        Concesionaria concesionariaVacia = new Concesionaria();
        try {
            concesionariaVacia.calcularTotalImpuestos();
        } catch (IllegalStateException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        //total de impuestos con lista llena
        System.out.println("Total impuestos: " + concesionaria.calcularTotalImpuestos());

        // Obtener vehículo más caro
        System.out.println("Vehiculo mas caro: " + concesionaria.obtenerVehiculoMasCaro());

        // Ordenar por precio y mostrar
        concesionaria.ordenarPorPrecio();
        System.out.println("Inventario ordenado por precio:");
        concesionaria.mostrarInventario();
    }
}

