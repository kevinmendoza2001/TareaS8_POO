package banco;

import excepciones.MontoInvalidoException;

public class CuentaInversion extends CuentaBancaria {
    private double montoMinimo;
    private double rendimientoAnual;

    public CuentaInversion(String numeroCuenta, String titular, double saldo, double montoMinimo, double rendimientoAnual) {
        super(numeroCuenta, titular, saldo);
        if (montoMinimo <= 0 || rendimientoAnual < 0) {
            throw new IllegalArgumentException("Monto mínimo > 0 y rendimiento ≥ 0");
        }
        this.montoMinimo = montoMinimo;
        this.rendimientoAnual = rendimientoAnual;
    }

    @Override
    public void depositar(double monto) {
        if (monto < montoMinimo) {
            throw new MontoInvalidoException("El depósito debe ser al menos " + montoMinimo);
        }
        saldo += monto;
    }

    public double calcularRendimiento() {
        return saldo * (rendimientoAnual / 12);
    }

    public double calcularRendimiento(int meses) {
        return saldo * (rendimientoAnual / 12) * meses;
    }
}
