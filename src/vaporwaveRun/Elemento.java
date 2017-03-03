package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Elemento {
	protected PApplet app;
	protected PImage style;

	protected float x, y, vel;

	public Elemento(PApplet app, PImage style) {
		this.app = app;
		this.style = style;

		x = 1200;
		y = app.random(100, 600);

		vel = app.random(2, 6);
	}

	/*
	 * Metodo para pintar los elementos
	 */
	public void pintar() {
		app.imageMode(PApplet.CENTER);
		app.image(style, x, y);
	}

	/*
	 * Metodo abstracto que define el movimiento de los elementos
	 */
	public abstract void mover();

	/*
	 * Metodo abstracto para saber si se coliciono y restar puntuacion
	 */
	public boolean colision(float xA, float yA) {
		if (PApplet.dist(xA, yA, x, y) < 80) {
			return true;
		}
		return false;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

}
