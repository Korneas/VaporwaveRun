package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Astronauta {

	private PApplet app;
	private PImage astro;
	private PVector pos;
	private float x, y, vel;

	public Astronauta(PApplet app, PImage astro) {
		this.app = app;
		this.astro = astro;

		x = 150;
		y = 350;

		pos = new PVector(x, y);
	}

	public void pintar() {
		app.imageMode(3);
		app.image(astro, x, y);
	}

	public void mover() {

	}

	public boolean validar(PVector e) {
		if (PVector.dist(e, pos) < 120) {
			return true;
		}
		return false;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
