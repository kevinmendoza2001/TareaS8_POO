package banco;

import excepciones.MontoInvalidoException;
import excepciones.SaldoInsuficienteException;

public class CuentaAhorros extends CuentaBancaria {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String titular, double saldo, double tasaInteres) {
        super(numeroCuenta, titular, saldo);
        if (tasaInteres < 0 || tasaInteres > 1) {
            throw new IllegalArgumentException("La tasa de interés debe estar entre 0 y 1");
        }
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto inválido");
        }
        if (saldo - monto < 50) {
            throw new SaldoInsuficienteException("Saldo insuficiente, debe quedar al menos 50");
        }
        saldo -= monto;
    }

    public void aplicarInteres() {
        saldo += saldo * tasaInteres;
    }
}
