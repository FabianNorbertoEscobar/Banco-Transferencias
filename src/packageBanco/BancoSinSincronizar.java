package packageBanco;

/**
 * La clase BancoSinSincronizar es quien contiene al método main del proyecto.
 * <br>
 * Crea un nuevo banco e indica que todas las cuentas del banco comiencen a
 * realizar transferencias.
 * 
 * @author Escobar
 *
 */
public class BancoSinSincronizar {

	public static void main(String[] args) {

		// instanciamos un nuevo banco
		Banco banco = new Banco();

		// todas las cuentas realizan transferencias de dinero
		for (int i = 0; i < 100; i++) {
			EjecucionTransferencias runnableTransferencia = new EjecucionTransferencias(banco, i, 2000);
			Thread thread = new Thread(runnableTransferencia);
			thread.start();
		}
	}

}
