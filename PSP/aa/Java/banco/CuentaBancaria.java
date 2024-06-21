class CuentaBancaria {
    private int saldo = 0;

    public void transferir(String cliente, int cantidad) {
        System.out.println(cliente + " inicia la transferencia.");
        System.out.println(cliente + " tiene un saldo de: " + saldo);

        // Simulación de trabajo que toma tiempo (para resaltar el problema sin
        // sincronización)
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saldo += cantidad;
        System.out.println(cliente + " completa la transferencia.");
        System.out.println(cliente + " tiene un saldo actualizado de: " + saldo);
    }

    public int getSaldo() {
        return saldo;
    }
}
