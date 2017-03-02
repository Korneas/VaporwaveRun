package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Elemento {
	protected PApplet app;
	protected PVector pos, vel;
	protected PImage style;

	protected float x, y;

	public Elemento(PApplet app, PImage style) {
		this.app = app;
		this.style = style;

		pos = new PVector(x, y);
	}

	/*
	 * Metodo para pintar los elementos
	 */
	public void pintar() {
		app.imageMode(PApplet.CENTER);
		app.image(style, pos.x, pos.y);
	}

	/*
	 * Metodo abstracto que define el movimiento de los elementos
	 */
	public abstract void mover();

	/*
	 * Metodo abstracto para saber si se coliciono y restar puntuacion
	 */
	public boolean colision(PVector rad) {
		if (PVector.dist(rad, pos) < 120) {
			return true;
		}
		return false;
	}

}
