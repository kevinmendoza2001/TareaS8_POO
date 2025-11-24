package tienda;

import excepciones.StockInsuficienteException;
import excepciones.ProductoNoEncontradoException;
import excepciones.PrecioInvalidoException;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario("Mi Tienda");

        // 1. Probar crear producto con precio negativo (PrecioInvalidoException)
        try {
            Producto p1 = new ProductoElectronico(
                    Inventario.generarCodigo("ELEC"), "Laptop", -1000, 5, "Dell", 12);
        } catch (PrecioInvalidoException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 2. Probar crear producto con código vacío (IllegalArgumentException)
        try {
            Producto p2 = new ProductoRopa("", "Camisa", 20, 10, "M", "Algodón");
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 3. Agregar 10 productos válidos (mix de tipos)
        Producto e1 = new ProductoElectronico(Inventario.generarCodigo("ELEC"), "Smartphone", 500, 10, "Samsung", 12);
        Producto e2 = new ProductoElectronico(Inventario.generarCodigo("ELEC"), "Televisor", 800, 3, "LG", 24);

        Producto a1 = new ProductoAlimenticio(Inventario.generarCodigo("ALIM"), "Leche", 1.2, 20, "2025-12-01", true);
        Producto a2 = new ProductoAlimenticio(Inventario.generarCodigo("ALIM"), "Pan", 0.5, 2, "2025-11-30", false);

        Producto r1 = new ProductoRopa(Inventario.generarCodigo("ROPA"), "Pantalón", 25, 15, "L", "Denim");
        Producto r2 = new ProductoRopa(Inventario.generarCodigo("ROPA"), "Chaqueta", 60, 4, "M", "Cuero");

        Producto e3 = new ProductoElectronico(Inventario.generarCodigo("ELEC"), "Tablet", 300, 8, "Apple", 12);
        Producto a3 = new ProductoAlimenticio(Inventario.generarCodigo("ALIM"), "Queso", 5, 6, "2025-12-15", true);
        Producto r3 = new ProductoRopa(Inventario.generarCodigo("ROPA"), "Zapatos", 40, 7, "42", "Cuero");
        Producto r4 = new ProductoRopa(Inventario.generarCodigo("ROPA"), "Bufanda", 15, 12, "Única", "Lana");

        inventario.agregarProducto(e1);
        inventario.agregarProducto(e2);
        inventario.agregarProducto(a1);
        inventario.agregarProducto(a2);
        inventario.agregarProducto(r1);
        inventario.agregarProducto(r2);
        inventario.agregarProducto(e3);
        inventario.agregarProducto(a3);
        inventario.agregarProducto(r3);
        inventario.agregarProducto(r4);

        // 4. Realizar ventas exitosas
        try {
            double totalVenta = inventario.venderProducto(e1.getCodigo(), 2);
            System.out.println("Venta exitosa de 2 smartphones. Total: " + totalVenta);
        } catch (Exception e) {
            System.out.println("Error en venta: " + e.getMessage());
        }

        // 5. Probar venta con cantidad negativa (IllegalArgumentException)
        try {
            inventario.venderProducto(e2.getCodigo(), -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otro error: " + e.getMessage());
        }

        // 6. Probar venta con stock insuficiente (StockInsuficienteException)
        try {
            inventario.venderProducto(a2.getCodigo(), 5); // pan solo tiene stock 2
        } catch (StockInsuficienteException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 7. Probar búsqueda de producto inexistente (ProductoNoEncontradoException)
        try {
            inventario.buscarPorCodigo("ROPA-9999");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 8. Calcular valor total del inventario
        try {
            System.out.println("Valor total del inventario: " + inventario.calcularValorInventario());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 9. Listar productos con stock < 5
        System.out.println("Productos con stock menor a 5:");
        for (Producto p : inventario.listarProductosBajoStock(5)) {
            System.out.println(p);
        }

        // 10. Ordenar por precio y mostrar
        inventario.ordenarPorPrecio();
        System.out.println("Inventario ordenado por precio:");
        System.out.println(inventario);
    }
}
