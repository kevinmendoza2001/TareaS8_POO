package banco;

import excepciones.SaldoInsuficienteException;
import excepciones.MontoInvalidoException;
import excepciones.CuentaInactivaException;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Central");

        // 1. Probar crear cuenta con titular vacío (IllegalArgumentException)
        try {
            CuentaBancaria c1 = new CuentaAhorros(Banco.generarNumeroCuenta(), "", 1000, 0.05);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 2. Probar crear cuenta con saldo negativo (IllegalArgumentException)
        try {
            CuentaBancaria c2 = new CuentaCorriente(Banco.generarNumeroCuenta(), "Kevin", -500, 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 3. Agregar 6 cuentas válidas (2 de cada tipo)
        CuentaBancaria ah1 = new CuentaAhorros(Banco.generarNumeroCuenta(), "Ana", 1000, 0.05);
        CuentaBancaria ah2 = new CuentaAhorros(Banco.generarNumeroCuenta(), "Luis", 2000, 0.03);

        CuentaBancaria cc1 = new CuentaCorriente(Banco.generarNumeroCuenta(), "Pedro", 500, 1000);
        CuentaBancaria cc2 = new CuentaCorriente(Banco.generarNumeroCuenta(), "Maria", 1500, 500);

        CuentaBancaria ci1 = new CuentaInversion(Banco.generarNumeroCuenta(), "Carlos", 3000, 200, 0.12);
        CuentaBancaria ci2 = new CuentaInversion(Banco.generarNumeroCuenta(), "Sofia", 4000, 500, 0.10);

        banco.abrirCuenta(ah1);
        banco.abrirCuenta(ah2);
        banco.abrirCuenta(cc1);
        banco.abrirCuenta(cc2);
        banco.abrirCuenta(ci1);
        banco.abrirCuenta(ci2);

        // 4. Realizar depósitos y retiros
        ah1.depositar(500);
        try {
            ah1.retirar(1200); // válido
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        try {
            cc1.retirar(2000); // excede saldo + crédito
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 5. Probar retiro con saldo insuficiente (SaldoInsuficienteException)
        try {
            ah2.retirar(1955); // dejaría menos de 50
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // 6. Probar transferencia exitosa y fallida
        try {
            banco.transferir(ah1.getNumeroCuenta(), cc2.getNumeroCuenta(), 200);
            System.out.println("Transferencia exitosa de " + ah1.getNumeroCuenta() + " a " + cc2.getNumeroCuenta());
        } catch (SaldoInsuficienteException | IllegalArgumentException | MontoInvalidoException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        try {
            banco.transferir("0000000000", cc2.getNumeroCuenta(), 100); // cuenta origen inexistente
        } catch (Exception e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        // 7. Probar monto negativo (MontoInvalidoException)
        try {
            banco.transferir(ah1.getNumeroCuenta(), cc2.getNumeroCuenta(), -50);
        } catch (Exception e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        }

        // 8. Calcular saldo total del banco
        try {
            System.out.println("Saldo total del banco: " + banco.obtenerSaldoTotal());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 9. Aplicar intereses y probar CuentaInactivaException
        try {
            banco.aplicarInteresesAhorros();
            System.out.println("Intereses aplicados a cuentas de ahorro.");
        } catch (CuentaInactivaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 10. Ordenar por saldo y mostrar cuentas
        banco.ordenarPorSaldo();
        System.out.println("Cuentas ordenadas por saldo:");
        banco.mostrarCuentas();
    }
}