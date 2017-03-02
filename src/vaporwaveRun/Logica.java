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

	private PImage logo, fondoM, fondoG;
	private float xFondoM, xFondoM2, xFondoG, xFondoG2;
	private PFont texto;

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
	}

	public void pintar() {
		app.background(255);
		switch (pantalla) {
		case 0:
			inicio();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}

	}

	private void inicio() {
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
			xFondoG --;
		} else {
			xFondoG = 2136;
		}
		
		if (xFondoG2 >= -2136) {
			xFondoG2 --;
		} else {
			xFondoG2 = 2136;
		}

		app.imageMode(0);
		app.image(fondoG, xFondoM, 0);
		app.image(fondoG, xFondoM2, 0);
		app.image(fondoM, xFondoG, 0);
		app.image(fondoM, xFondoG2, 0);

		app.imageMode(3);
		app.image(logo, 500, 350);

		app.fill(255);
		app.textAlign(PApplet.RIGHT);
		app.textFont(texto);
		app.text("Presione -Espacio-\n para empezar", 970, 650);
	}

	public void tecla() {

	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {

		}

		if (arg instanceof Movement) {
			System.out.println("Me llego el mover");
		}
	}
}
