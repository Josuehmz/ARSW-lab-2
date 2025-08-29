package arsw.threads;

public class RegistroLlegada {

	private int ultimaPosicionAlcanzada=1;

	private String ganador=null;
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public int getUltimaPosicionAlcanzada() {
		return ultimaPosicionAlcanzada;
	}

	public void setUltimaPosicionAlcanzada(int ultimaPosicionAlcanzada) {
		this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
	}

	/**
	 * Método sincronizado para obtener la posición de llegada de manera atómica
	 * y actualizar el contador. Evita condiciones de carrera.
	 * @return la posición de llegada del galgo
	 */
	public synchronized int obtenerPosicionLlegada() {
		int posicion = ultimaPosicionAlcanzada;
		ultimaPosicionAlcanzada++;
		return posicion;
	}
	
}
