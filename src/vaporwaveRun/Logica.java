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

	private PImage logo, fondoM, fondoM2, fondoG, fondoG2;
	private float xFondo, xFondo2;
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
		fondoM2 = fondoM;

		fondoG = app.loadImage("data/neogalaxy.jpg");
		fondoG2 = fondoG;

		texto = app.loadFont("data/Pixeled-24.vlw");
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
		if (xFondo >= -2200) {
			xFondo -= 1.5;
		} else {
			xFondo = 0;
		}
		xFondo2--;

		System.out.println(xFondo);

		app.imageMode(0);
		app.image(fondoG, xFondo, 0);
		app.image(fondoM, xFondo2, 0);

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

		}
	}
}
