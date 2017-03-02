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
	private int pantalla;
	private boolean cambiarPantalla;

	private PImage logo, fondoM, fondoG;
	private float xFondoM, xFondoM2, xFondoG, xFondoG2;
	private PFont texto;

	private PImage[] elementos;

	private ArrayList<Elemento> elem;

	public Logica(PApplet app) {
		this.app = app;

		c = new Comunicacion();
		Thread th = new Thread(c);
		th.start();

		c.addObserver(this);

		logo = app.loadImage("data/VaporwaveLet.png");

		fondoM = app.loadImage("data/montana.png");

		fondoG = app.loadImage("data/neogalaxy.jpg");

		texto = app.loadFont("data/Pixeled-24.vlw");

		xFondoM2 = 2127;
		xFondoG2 = 2137;

		elementos = new PImage[3];

		elementos[0] = app.loadImage("data/Arizona.png");
		elementos[1] = app.loadImage("data/Gameboy.png");
		elementos[2] = app.loadImage("data/Windows.png");

		elem = new ArrayList<Elemento>();

		elem.add(new Arizona(app, elementos[0]));
	}

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
		app.image(fondoM, xFondoG, 0);
		app.image(fondoM, xFondoG2, 0);
	}

	private void inicio() {
		app.imageMode(3);
		app.image(logo, 500, 350);

		app.fill(255);
		app.textAlign(PApplet.RIGHT);
		app.textFont(texto);
		app.text("Presione -Espacio-\n para empezar", 970, 650);
	}

	private void instrucciones() {

	}

	private void game() {
		for (int i = 0; i < elem.size(); i++) {
			elem.get(i).pintar();
			elem.get(i).mover();
		}
	}

	private void resultados() {

	}

	public void tecla() {
		if (app.keyPressed) {
			System.out.println(pantalla);
			if (app.key == ' ') {
				if (cambiarPantalla && pantalla == 0) {
					pantalla = 1;
					cambiarPantalla = false;
				}

				if (cambiarPantalla && pantalla == 1) {
					pantalla = 2;
					cambiarPantalla = false;
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			if (((String) arg).contains("\\.")) {
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
			System.out.println("Me llego el mover");
		}
	}
}
