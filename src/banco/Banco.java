package banco;

import excepciones.CuentaInactivaException;
import excepciones.MontoInvalidoException;
import excepciones.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.Random;

public class Banco {
    // Inventario de cuentas del banco
    private final ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
    private final String nombre;

    public Banco(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre de banco inválido");
        }
        this.nombre = nombre;
    }

    public static String generarNumeroCuenta() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public void abrirCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no puede ser null");
        }
        cuentas.add(cuenta);
    }

    public void transferir(String origen, String destino, double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto inválido");
        }

        CuentaBancaria cOrigen = buscarCuenta(origen);
        CuentaBancaria cDestino = buscarCuenta(destino);

        if (cOrigen == null || cDestino == null) {
            throw new IllegalArgumentException("Cuenta origen o destino no existe");
        }

        cOrigen.retirar(monto);   // puede lanzar SaldoInsuficienteException
        cDestino.depositar(monto);
    }

    public void aplicarInteresesAhorros() {
        for (CuentaBancaria c : cuentas) {
            if (c instanceof CuentaAhorros) {
                if (c.getSaldo() == 0) {
                    throw new CuentaInactivaException("Cuenta de ahorros inactiva");
                }
                ((CuentaAhorros) c).aplicarInteres();
            }
        }
    }

    public double obtenerSaldoTotal() {
        if (cuentas.isEmpty()) {
            throw new IllegalStateException("No hay cuentas en el banco");
        }
        double total = 0.0;
        for (CuentaBancaria c : cuentas) {
            total += c.getSaldo();
        }
        return total;
    }

    public void ordenarPorSaldo() {
        int n = cuentas.size();
        if (n < 2) return; // nada que ordenar

        for (int i = 0; i < n - 1; i++) {
            boolean huboIntercambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (cuentas.get(j).getSaldo() > cuentas.get(j + 1).getSaldo()) {
                    CuentaBancaria temp = cuentas.get(j);
                    cuentas.set(j, cuentas.get(j + 1));
                    cuentas.set(j + 1, temp);
                    huboIntercambio = true;
                }
            }
            if (!huboIntercambio) break; // optimización: si no hubo swaps, ya está ordenado
        }
    }

    public void mostrarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas para mostrar.");
            return;
        }
        for (CuentaBancaria c : cuentas) {
            System.out.println(c);
        }
    }

    private CuentaBancaria buscarCuenta(String numero) {
        if (numero == null) return null;
        for (CuentaBancaria c : cuentas) {
            if (numero.equals(c.getNumeroCuenta())) {
                return c;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }
}