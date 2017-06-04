package packageBanco;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	private Lock cierreBanco = new ReentrantLock();
	private Condition saldoSuficiente;

	/**
	 * Crea un banco con 100 cuentas con un saldo inicial de 2000 euros
	 */
	public Banco() {
		// se crean las 100 cuentas
		cuentas = new double[100];
		// se carga un saldo inicial de 2000 euros a cada una de las cuentas
		for (int i = 0; i < cuentas.length; i++) {
			cuentas[i] = 2000;
		}
		// se establece una condición de bloqueo al banco
		this.saldoSuficiente = this.cierreBanco.newCondition();
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
	 * @throws InterruptedException
	 */
	public void transferencias(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {

		this.cierreBanco.lock();
		try {
			// mientras que sea posible realizar la transferencia, la realiza
			while (this.cuentas[cuentaOrigen] < cantidad) {
				// el hilo actual se pone a la escucha, a la espera
				this.saldoSuficiente.await();
			}

			System.out.println(Thread.currentThread());
			this.cuentas[cuentaOrigen] -= cantidad; // sale el dinero de
													// cuentaOrigen
			System.out.printf("Transferencia de %10.2f euros de la cuenta %d para la cuenta %d. ", cantidad,
					cuentaOrigen, cuentaDestino);
			this.cuentas[cuentaDestino] += cantidad; // entra el dinero a
														// cuentaDestino
			System.out.printf("Saldo total: %10.2f euros \n", this.getSaldoTotal());
			// se envía una señal a los hilos dormidos para que se despierten si
			// se satisface la condición de bloqueo
			this.saldoSuficiente.signalAll();
		} finally {
			this.cierreBanco.unlock(); // desbloquea el hilo actual
		}
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
