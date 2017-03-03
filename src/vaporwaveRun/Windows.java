package vaporwaveRun;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Windows extends Elemento{

	public Windows(PApplet app, PImage style) {
		super(app, style);
	}

	@Override
	public void mover() {
		x -= vel;
	}

}
