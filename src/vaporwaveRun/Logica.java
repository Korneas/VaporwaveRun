package vaporwaveRun;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import serial.Movement;

public class Logica implements Observer {

	private PApplet app;
	private Comunicacion c;
	private ConectadosRed cR;
	private Tiempo time;

	private int pantalla;
	private boolean cambiarPantalla;

	private PImage logo, fondoM, fondoG, fondoFinal, instruc;
	private float xFondoM, xFondoM2, xFondoG, xFondoG2;
	private PFont texto;

	private Astronauta as;

	private PImage[] elementos;

	private ArrayList<Elemento> elem;

	private int puntuacion, mult;

	/**
	 * Constructor de Logica el cual genera toda la interaccion de la animacion
	 * @param app PApplet
	 */
	public Logica(PApplet app) {
		this.app = app;

		c = new Comunicacion();
		Thread th = new Thread(c);
		th.start();

		c.addObserver(this);

		cR = new ConectadosRed();
		cR.addObserver(this);

		time = new Tiempo(app);

		logo = app.loadImage("data/VaporwaveLet.png");
		fondoM = app.loadImage("data/montana.png");
		fondoG = app.loadImage("data/neogalaxy.jpg");
		fondoFinal = app.loadImage("data/FondoFinal.png");
		instruc = app.loadImage("data/insturc.png");

		texto = app.loadFont("data/Pixeled-24.vlw");

		xFondoM2 = 2127;
		xFondoG2 = 2137;

		as = new Astronauta(app, app.loadImage("data/Personaje.png"));

		elementos = new PImage[3];

		elementos[0] = app.loadImage("data/Arizona.png");
		elementos[1] = app.loadImage("data/Gameboy.png");
		elementos[2] = app.loadImage("data/Windows.png");

		elem = new ArrayList<Elemento>();

		mult = 1;
	}

	/**
	 * Metodo para pintar todas las interfaces
	 */
	public void pintar() {
		app.background(255);

		fondo();

		if (app.frameCount % 60 == 0) {
			cambiarPantalla = true;
		}

		switch (pantalla) {
		case 0:
			inicio();
			break;
		case 1:
			instrucciones();
			break;
		case 2:
			game();
			break;
		case 3:
			resultados();
			break;
		}

	}

	/**
	 * Metodo par pintar el fondo que es continuo e infinito
	 */
	private void fondo() {
		if (xFondoM >= -2136) {
			xFondoM -= 2;
		} else {
			xFondoM = 2127;
		}

		if (xFondoM2 >= -2136) {
			xFondoM2 -= 2;
		} else {
			xFondoM2 = 2127;
		}

		if (xFondoG >= -2136) {
			xFondoG--;
		} else {
			xFondoG = 2136;
		}

		if (xFondoG2 >= -2136) {
			xFondoG2--;
		} else {
			xFondoG2 = 2136;
		}

		app.imageMode(0);
		app.image(fondoG, xFondoM, 0);
		app.image(fondoG, xFondoM2, 0);
		if (pantalla == 3) {
			app.image(fondoFinal, 0, 0);
		}
		app.image(fondoM, xFondoG, 0);
		app.image(fondoM, xFondoG2, 0);
	}

	/**
	 * Pantalla de inicio
	 */
	private void inicio() {
		app.imageMode(3);
		app.image(logo, 500, 350);

		app.fill(50, 0, 100);
		app.textAlign(PApplet.RIGHT);
		app.textFont(texto);
		app.text("Presione -Espacio-\n para empezar", 970, 650);
	}

	/*
	 * Pantalla de instrucciones
	 */
	private void instrucciones() {
		app.fill(50, 0, 100);
		app.textAlign(PApplet.RIGHT);
		app.textFont(texto);
		app.text("Presione -Espacio-\n para continuar", 970, 650);
		app.textAlign(PApplet.CENTER);
		app.textSize(32);
		app.text("Instrucciones", 500, 120);
		app.imageMode(3);
		app.image(instruc, 500, 350);
		app.textSize(12);
		app.text("¡Te mueves utilizando tu dispositivo Android!\nTienes un minuto para lograr la mejor puntuacion", 500,
				470);
	}

	/*
	 * Pantalla de juego
	 */
	private void game() {
		app.fill(255);
		app.noStroke();
		app.ellipse(500, 0, 200, 200);
		app.fill(50, 0, 100);
		app.textAlign(PApplet.CENTER);
		app.textFont(texto);
		app.textSize(16);
		app.text("Puntuacion\n" + puntuacion, 500, 50);

		app.textAlign(PApplet.RIGHT);
		app.textSize(24);
		app.text(PApplet.nf(time.minute(), 1) + ":" + PApplet.nf(time.second(), 2), 900, 50);

		app.textAlign(PApplet.LEFT);
		app.textSize(24);
		app.text("x" + mult, 100, 50);

		if (elem.size() > 0) {
			for (int i = 0; i < elem.size(); i++) {
				elem.get(i).pintar();
				elem.get(i).mover();

				if (elem.get(i).colision(as.getX(), as.getY())) {
					if (elem.get(i) instanceof Arizona) {
						mult++;
						elem.remove(i);
					} else if (elem.get(i) instanceof Gameboy) {
						puntuacion -= 50;
						mult = 1;
						elem.remove(i);
					} else if (elem.get(i) instanceof Windows) {
						puntuacion -= 100;
						mult = 1;
						elem.remove(i);
					}
				}

				if (elem.get(i).getX() < -100) {
					elem.remove(i);
				}
			}
		}

		as.pintar();

		if (app.frameCount % 5 == 0) {
			puntuacion += mult;
		}

		if (puntuacion < 0) {
			pantalla = 3;
			time.detener();
		}

		if (time.minute() >= 1) {
			pantalla = 3;
			time.detener();
		}

	}

	/*
	 * Pantalla de resultados
	 */
	private void resultados() {
		app.fill(50,0,100);
		app.textAlign(PApplet.CENTER);
		app.textSize(38);
		app.text("Resultados", 500, 180);
		
		app.fill(0,0,200);

		app.textAlign(PApplet.RIGHT);
		app.textSize(20);
		app.text("Tiempo:\n\nPuntuacion:\n\nMax multi:", 500, 300);

		app.textAlign(PApplet.LEFT);
		app.textSize(20);
		app.text(PApplet.nf(time.minute(), 1) + ":" + PApplet.nf(time.second(), 2) + "\n\n" + puntuacion + "\n\nx" + mult,
				500, 300);
	}

	/*
	 * Metodo para utilizar la barra espaciadora para ir entre pantallas
	 */
	public void tecla() {
		if (app.keyPressed) {
			if (app.key == ' ') {
				if (cambiarPantalla && pantalla == 0) {
					pantalla = 1;
					cambiarPantalla = false;
				}

				if (cambiarPantalla && pantalla == 1) {
					pantalla = 3;
					cambiarPantalla = false;
					time.empezar();
					Thread nt = new Thread(cR);
					nt.start();
				}
			}
		}
	}

	/**
	 * Metodo implementado e Observer para conocer las notificaciones de los observable que este tiene
	 * @param o Observable
	 * @param arg Object
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String msg = (String) arg;
			if (msg.contains(".")) {
				int r = (int) app.random(3);

				switch (r) {
				case 0:
					elem.add(new Arizona(app, elementos[0]));
					break;
				case 1:
					elem.add(new Gameboy(app, elementos[1]));
					break;
				case 2:
					elem.add(new Windows(app, elementos[2]));
					break;
				}
			}
		}

		if (arg instanceof Movement) {
			Movement mov = (Movement) arg;
			if (mov.getContenido().contains("up")) {
				as.setY(as.getY() - 15);
			} else if (mov.getContenido().contains("down")) {
				as.setY(as.getY() + 15);
			}
		}
	}
}
