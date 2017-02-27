package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Elemento {
	private PApplet app;
	private PVector pos;
	private PImage style;

	private float x, y, vel;

	public Elemento(PApplet app) {
		this.app = app;
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

}
