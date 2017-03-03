package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Astronauta {

	private PApplet app;
	private PImage astro;
	private float x, y, vel;

	public Astronauta(PApplet app, PImage astro) {
		this.app = app;
		this.astro = astro;

		x = 150;
		y = 350;
	}

	public void pintar() {
		app.imageMode(3);
		app.image(astro, x, y);
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
