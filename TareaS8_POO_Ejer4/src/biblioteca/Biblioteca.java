package biblioteca;

import excepciones.CodigoInvalidoException;
import excepciones.MaterialNoDisponibleException;
import excepciones.MaterialNoEncontradoException;

import java.util.ArrayList;
import java.util.Random;

public class Biblioteca {
    private ArrayList<MaterialBiblioteca> catalogo = new ArrayList<>();
    private String nombre;

    public Biblioteca(String nombre) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre inválido");
        this.nombre = nombre;
    }

    // Valida que el código tenga prefijo correcto
    public static void validarCodigo(String codigo) throws CodigoInvalidoException {
        if (!(codigo.startsWith("LIB-") || codigo.startsWith("REV-") || codigo.startsWith("DVD-"))) {
            throw new CodigoInvalidoException("Código inválido: " + codigo);
        }
    }

    // Genera código aleatorio con prefijo
    public static String generarCodigoAleatorio(String tipo) {
        Random r = new Random();
        int num = 100 + r.nextInt(900);
        return tipo.toUpperCase() + "-" + num;
    }

    // Agregar material al catálogo
    public void agregarMaterial(MaterialBiblioteca m) throws CodigoInvalidoException {
        if (m == null) throw new NullPointerException("Material no puede ser null");
        validarCodigo(m.getCodigo());
        catalogo.add(m);
    }

    // Buscar material por código
    public MaterialBiblioteca buscarMaterial(String codigo) {
        for (MaterialBiblioteca m : catalogo) {
            if (m.getCodigo().equals(codigo)) return m;
        }
        throw new MaterialNoEncontradoException("Material no encontrado: " + codigo);
    }

    // Prestar material
    public void prestarMaterial(String codigo) {
        MaterialBiblioteca m = buscarMaterial(codigo);
        if (m.isPrestado()) throw new MaterialNoDisponibleException("Material ya prestado: " + codigo);
        m.prestar();
    }

    // Devolver material y calcular multa
    public double devolverMaterial(String codigo, int diasRetraso) {
        if (diasRetraso < 0) throw new IllegalArgumentException("Días de retraso inválidos");
        MaterialBiblioteca m = buscarMaterial(codigo);
        m.devolver();
        return m.calcularMultaPorRetraso(diasRetraso);
    }

    // Listar materiales disponibles
    public void listarMaterialesDisponibles() {
        for (MaterialBiblioteca m : catalogo) {
            if (!m.isPrestado()) System.out.println(m);
        }
    }

    // Ordenar catalogo por año de publicacion
    public void ordenarPorAnio() {
        int n = catalogo.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (catalogo.get(j).getAnioPublicacion() > catalogo.get(j + 1).getAnioPublicacion()) {
                    MaterialBiblioteca temp = catalogo.get(j);
                    catalogo.set(j, catalogo.get(j + 1));
                    catalogo.set(j + 1, temp);
                }
            }
        }
    }

    //mostrar catalogo
    public void mostrarCatalogo() {
        for (MaterialBiblioteca m : catalogo) {
            System.out.println(m);
        }
    }
}