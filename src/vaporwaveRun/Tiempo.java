package vaporwaveRun;

import processing.core.PApplet;

public class Tiempo {

	private PApplet app;

	// Objeto para contabilizar tiempo y que no se vea afectado por un frame
	// rate
	// Declaración de las variables que ejecutaran el tiempo
	int comenzar = 0, parar = 0;
	boolean reproducir = false;

	public Tiempo(PApplet app) {
		this.app = app;
		// TODO Auto-generated constructor stub
	}

	// Si el reloj comienza
	void empezar() {
		comenzar = app.millis();
		reproducir = true;
	}

	// Si el reloj se detiene
	void detener() {
		parar = app.millis();
		reproducir = false;
	}

	// Reproducira el tiempo que se este pasando
	int timepoReproducido() {
		int tiempo;
		if (reproducir) {
			tiempo = (app.millis() - comenzar);
		} else {
			tiempo = (parar - comenzar);
		}
		return tiempo;
	}

	// Retorna los segundos reproducidos
	int second() {
		return (timepoReproducido() / 1000) % 60;
	}

	// Retorna los minutos reproducidos
	int minute() {
		return (timepoReproducido() / (1000 * 60)) % 60;
	}
}
