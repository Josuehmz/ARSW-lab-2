package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	
	private static volatile boolean pausado = false;
	private static final Object pauseLock = new Object();

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			synchronized(pauseLock) {
				while (pausado) {
					pauseLock.wait();
				}
			}
			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			
			if (paso == carril.size()) {						
				carril.finish();
				int ubicacion = regl.obtenerPosicionLlegada();
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1){
					regl.setGanador(this.getName());
				}
				
			}
		}
	}
	
	/**
	 * Pausa todos los galgos
	 */
	public static void pausarCarrera() {
		synchronized(pauseLock) {
			pausado = true;
		}
	}
	
	/**
	 * Reanuda todos los galgos
	 */
	public static void reanudarCarrera() {
		synchronized(pauseLock) {
			pausado = false;
			pauseLock.notifyAll();
		}
	}
	
	/**
	 * Reinicia el estado de pausa para una nueva carrera
	 */
	public static void reiniciarEstado() {
		synchronized(pauseLock) {
			pausado = false;
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
