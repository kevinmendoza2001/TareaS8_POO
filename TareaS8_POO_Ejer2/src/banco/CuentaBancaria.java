package banco;

import excepciones.MontoInvalidoException;
import excepciones.SaldoInsuficienteException;

public abstract class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (numeroCuenta == null || numeroCuenta.isEmpty()) {
            throw new IllegalArgumentException("Número de cuenta inválido");
        }
        if (titular == null || titular.isEmpty()) {
            throw new IllegalArgumentException("Titular inválido");
        }
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto a depositar debe ser positivo");
        }
        saldo += monto;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto a retirar debe ser positivo");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        saldo -= monto;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " - Titular: " + titular + " - Saldo: " + saldo;
    }
}
