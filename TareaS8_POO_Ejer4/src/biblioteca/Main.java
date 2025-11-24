package biblioteca;

import excepciones.CodigoInvalidoException;
import excepciones.MaterialNoDisponibleException;
import excepciones.MaterialNoEncontradoException;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // 1. Probar crear material con título vacio (IllegalArgumentException)
        try {
            Libro libroInvalido = new Libro("LIB-001", "", "Autor X", 2020, 200, "Editorial X", false);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 2. Probar crear material con año < 1000 (IllegalArgumentException)
        try {
            Revista revistaInvalida = new Revista("REV-001", "Revista Ciencia", "Autor Y", 500, 10, "Enero", true);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 3. Probar agregar material con código inválido (CodigoInvalidoException)
        try {
            Libro libroCodigoInvalido = new Libro("XXX-123", "Libro Invalido", "Autor Z", 2010, 150, "Editorial Z", false);
            biblioteca.agregarMaterial(libroCodigoInvalido);
        } catch (CodigoInvalidoException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 4. Agregar 8 materiales válidos
        try {
            Libro l1 = new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "El Quijote", "Cervantes", 1605, 863, "Editorial A", false);
            Libro l2 = new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "1984", "Orwell", 1949, 328, "Editorial B", true);

            Revista r1 = new Revista(Biblioteca.generarCodigoAleatorio("REV"), "National Geographic", "Varios", 2021, 200, "Junio", false);
            Revista r2 = new Revista(Biblioteca.generarCodigoAleatorio("REV"), "Nature", "Varios", 2022, 150, "Mayo", true);

            DVD d1 = new DVD(Biblioteca.generarCodigoAleatorio("DVD"), "Inception", "Nolan", 2010, 148, "Sci-Fi", true);
            DVD d2 = new DVD(Biblioteca.generarCodigoAleatorio("DVD"), "Interstellar", "Nolan", 2014, 169, "Sci-Fi", true);

            Libro l3 = new Libro(Biblioteca.generarCodigoAleatorio("LIB"), "Cien Años de Soledad", "García Marquez", 1967, 417, "Editorial C", false);
            Revista r3 = new Revista(Biblioteca.generarCodigoAleatorio("REV"), "Scientific American", "Varios", 2020, 300, "Julio", false);

            biblioteca.agregarMaterial(l1);
            biblioteca.agregarMaterial(l2);
            biblioteca.agregarMaterial(r1);
            biblioteca.agregarMaterial(r2);
            biblioteca.agregarMaterial(d1);
            biblioteca.agregarMaterial(d2);
            biblioteca.agregarMaterial(l3);
            biblioteca.agregarMaterial(r3);

            // 5. Realizar 4 prestamos exitosos
            biblioteca.prestarMaterial(l1.getCodigo());
            biblioteca.prestarMaterial(r1.getCodigo());
            biblioteca.prestarMaterial(d1.getCodigo());
            biblioteca.prestarMaterial(l2.getCodigo());
            System.out.println("Prestamos realizados con exito.");

            // 6. Probar préstamo de material ya prestado (MaterialNoDisponibleException)
            try {
                biblioteca.prestarMaterial(l1.getCodigo());
            } catch (MaterialNoDisponibleException e) {
                System.out.println("Error capturado: " + e.getMessage());
            }

            // 7. Probar buscar material inexistente (MaterialNoEncontradoException)
            try {
                biblioteca.buscarMaterial("LIB-999");
            } catch (MaterialNoEncontradoException e) {
                System.out.println("Error capturado: " + e.getMessage());
            }

            // 8. Devolver 2 materiales con diferentes días de retraso y mostrar multas
            double multa1 = biblioteca.devolverMaterial(l1.getCodigo(), 3);
            System.out.println("Multa por devolver " + l1.getCodigo() + ": " + multa1);

            double multa2 = biblioteca.devolverMaterial(d1.getCodigo(), 5);
            System.out.println("Multa por devolver " + d1.getCodigo() + ": " + multa2);

            // 9. Listar materiales disponibles
            System.out.println("\nMateriales disponibles:");
            biblioteca.listarMaterialesDisponibles();

            // 10. Ordenar por año y mostrar
            biblioteca.ordenarPorAnio();
            System.out.println("\nCatalogo ordenado por año:");
            biblioteca.listarMaterialesDisponibles();

        } catch (CodigoInvalidoException e) {
            System.out.println("Error al agregar material: " + e.getMessage());
        }
    }
}