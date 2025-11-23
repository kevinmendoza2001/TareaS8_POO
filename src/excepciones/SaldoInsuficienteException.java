package excepciones;

// Checked: obliga a manejarla con try-catch o throws
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
