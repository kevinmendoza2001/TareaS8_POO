package banco;

import excepciones.MontoInvalidoException;
import excepciones.SaldoInsuficienteException;

public class CuentaCorriente extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo, double limiteCredito) {
        super(numeroCuenta, titular, saldo);
        if (limiteCredito < 0) {
            throw new IllegalArgumentException("El límite de crédito no puede ser negativo");
        }
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto inválido");
        }
        if (monto > saldo + limiteCredito) {
            throw new SaldoInsuficienteException("Saldo insuficiente incluso con crédito");
        }
        saldo -= monto;
    }
}
