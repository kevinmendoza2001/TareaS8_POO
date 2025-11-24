package tienda;

import excepciones.ProductoNoEncontradoException;
import excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.Random;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    private String nombreTienda;

    public Inventario(String nombreTienda) {
        if (nombreTienda == null || nombreTienda.isEmpty()) {
            throw new IllegalArgumentException("Nombre de tienda inválido");
        }
        this.nombreTienda = nombreTienda;
    }

    public static String generarCodigo(String categoria) {
        Random r = new Random();
        int num = 1000 + r.nextInt(9000);
        return categoria.toUpperCase() + "-" + num;
    }

    public void agregarProducto(Producto p) {
        if (p == null) {
            throw new NullPointerException("Producto no puede ser null");
        }
        productos.add(p);
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("Producto con código " + codigo + " no encontrado");
    }

    public double venderProducto(String codigo, int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad inválida");
        }
        Producto p = buscarPorCodigo(codigo);
        if (p.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para " + p.getNombre());
        }
        p.setStock(p.getStock() - cantidad);
        return p.calcularPrecioFinal() * cantidad;
    }

    public double calcularValorInventario() {
        if (productos.isEmpty()) {
            throw new IllegalStateException("Inventario vacío");
        }
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularPrecioFinal() * p.getStock();
        }
        return total;
    }

    public ArrayList<Producto> listarProductosBajoStock(int minimo) {
        ArrayList<Producto> bajos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() < minimo) {
                bajos.add(p);
            }
        }
        return bajos;
    }

    public void ordenarPorPrecio() {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productos.get(j).calcularPrecioFinal() > productos.get(j + 1).calcularPrecioFinal()) {
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inventario de " + nombreTienda + ":\n");
        for (Producto p : productos) {
            sb.append(p).append("\n");
        }
        return "";
    }
}
