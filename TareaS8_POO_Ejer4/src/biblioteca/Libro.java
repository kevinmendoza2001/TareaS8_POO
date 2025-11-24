package biblioteca;

public class Libro extends MaterialBiblioteca {
    private int numPaginas;
    private String editorial;
    private boolean esDigital;

    public Libro(String codigo, String titulo, String autor, int anio, int numPaginas, String editorial, boolean esDigital) {
        super(codigo, titulo, autor, anio);
        if (numPaginas <= 0) throw new IllegalArgumentException("Número de páginas inválido");
        if (editorial == null || editorial.isEmpty()) throw new IllegalArgumentException("Editorial inválida");
        this.numPaginas = numPaginas;
        this.editorial = editorial;
        this.esDigital = esDigital;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if (diasRetraso < 0) throw new IllegalArgumentException("Días de retraso inválidos");
        return esDigital ? diasRetraso * 0.50 : diasRetraso * 1.00;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return esDigital ? 7 : 15;
    }
}
