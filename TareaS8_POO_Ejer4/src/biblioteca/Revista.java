package biblioteca;

public class Revista extends MaterialBiblioteca {
    private int numeroEdicion;
    private String mesPublicacion;
    private boolean esEspecializada;

    public Revista(String codigo, String titulo, String autor, int anio, int numeroEdicion, String mesPublicacion, boolean esEspecializada) {
        super(codigo, titulo, autor, anio);
        if (numeroEdicion <= 0) throw new IllegalArgumentException("Número de edición inválido");
        if (mesPublicacion == null || mesPublicacion.isEmpty()) throw new IllegalArgumentException("Mes inválido");
        this.numeroEdicion = numeroEdicion;
        this.mesPublicacion = mesPublicacion;
        this.esEspecializada = esEspecializada;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if (diasRetraso < 0) throw new IllegalArgumentException("Días de retraso inválidos");
        return esEspecializada ? diasRetraso * 2.00 : diasRetraso * 0.75;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return esEspecializada ? 5 : 7;
    }
}