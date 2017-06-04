package packageBanco;

/**
 * La clase Banco contiene cuentas bancarias y permite realizar transferencias
 * entre ellas. <br>
 * En cada transferencia, se informará el movimiento por consola, junto al saldo
 * total del banco
 * 
 * @author Escobar
 *
 */
public class Banco {

	private final double[] cuentas;

	/**
	 * Crea un banco con 100 cuentas con un saldo inicial de 2000 euros
	 */
	public Banco() {
		cuentas = new double[100];

		for (int i = 0; i < cuentas.length; i++) {
			cuentas[i] = 2000;
		}
	}

	/**
	 * Realiza transferencias entre dos cuentas
	 * 
	 * @param cuentaOrigen
	 *            es la cuenta de origen
	 * @param cuentaDestino
	 *            es la cuenta de destino
	 * @param cantidad
	 *            es la suma de dinero que será transferida desde la cuenta
	 *            origen hacia la cuenta destino
	 */
	public void transferencias(int cuentaOrigen, int cuentaDestino, double cantidad) {
		// evalúa que sea posible realizar la transferencia
		if (this.cuentas[cuentaOrigen] < cantidad) {
			return;
		}

		System.out.println(Thread.currentThread());

		this.cuentas[cuentaOrigen] -= cantidad; // sale el dinero de
												// cuentaOrigen
		System.out.printf("Transferencia de %10.2f euros de la cuenta %d para la cuenta %d. ", cantidad, cuentaOrigen,
				cuentaDestino);

		this.cuentas[cuentaDestino] += cantidad; // entra el dinero a
													// cuentaDestino

		System.out.printf("Saldo total: %10.2f euros \n", this.getSaldoTotal());
	}

	/**
	 * Calcula el saldo total del banco
	 * 
	 * @return Retorna el saldo total de todas las cuentas del banco
	 */
	public double getSaldoTotal() {
		double saldoTotal = 0;

		for (double saldo : this.cuentas) {
			saldoTotal += saldo;
		}

		return saldoTotal;
	}
}
