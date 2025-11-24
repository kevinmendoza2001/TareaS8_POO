package biblioteca;

public class DVD extends MaterialBiblioteca {
    private int duracionMinutos;
    private String genero;
    private boolean tieneSubtitulos;

    public DVD(String codigo, String titulo, String autor, int anio, int duracionMinutos, String genero, boolean tieneSubtitulos) {
        super(codigo, titulo, autor, anio);
        if (duracionMinutos <= 0) throw new IllegalArgumentException("Duración inválida");
        if (genero == null || genero.isEmpty()) throw new IllegalArgumentException("Género inválido");
        this.duracionMinutos = duracionMinutos;
        this.genero = genero;
        this.tieneSubtitulos = tieneSubtitulos;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if (diasRetraso < 0) throw new IllegalArgumentException("Días de retraso inválidos");
        return diasRetraso * 1.50;
    }

    public double calcularMultaPorRetraso(int diasRetraso, boolean esEstreno) {
        double multa = calcularMultaPorRetraso(diasRetraso);
        return esEstreno ? multa * 2 : multa;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return 3;
    }
}
