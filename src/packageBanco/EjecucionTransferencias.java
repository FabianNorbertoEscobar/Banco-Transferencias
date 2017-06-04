package packageBanco;

/**
 * La clase EjecucionTransferencias implementa la interfaz Runnable. <br>
 * Genera y arranca el hilo de ejecución de cada movimiento.
 * 
 * @author Escobar
 *
 */
public class EjecucionTransferencias implements Runnable {

	private Banco banco;
	private int cuentaOrigen;
	private double cantidadMaxima;

	/**
	 * Construye un ejecutable de la tranferencia bancaria
	 * 
	 * @param banco
	 *            es el banco sobre el que se realiza la transferencia
	 * @param cuentaOrigen
	 *            es la cuenta de la que se extrae el dinero
	 * @param cantidadMaxima
	 *            es la màxima cantidad de dinero que puede ser transferida
	 */
	public EjecucionTransferencias(Banco banco, int cuentaOrigen, double cantidadMaxima) {
		this.banco = banco;
		this.cuentaOrigen = cuentaOrigen;
		this.cantidadMaxima = cantidadMaxima;
	}

	/**
	 * Este método es quien arrancará el hilo de cada una de las transferencias
	 */
	@Override
	public void run() {
		int cuentaDestino;
		double cantidad;

		try {
			// se realizan transferencias infinitas
			while (true) {
				// elegimos de forma aleatoria a la cuenta de destino
				cuentaDestino = (int) (100 * Math.random());
				// determino de forma aleatoria el monto a transferir
				cantidad = this.cantidadMaxima * Math.random();
				// el banco se encarga de realizar la transferencia
				this.banco.transferencias(this.cuentaOrigen, cuentaDestino, cantidad);
				// dormimod el hilo para que no se ejecute tan rápido
				Thread.sleep((int) (Math.random() * 10));
			}
		} catch (InterruptedException e) {

		}
	}

}
