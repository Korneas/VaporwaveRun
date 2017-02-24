package vaporwaveRun;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer{

	private PApplet app;
	private Comunicacion c;

	public Logica(PApplet app) {
		this.app = app;
		
		c = new Comunicacion();
		Thread th = new Thread(c);
		th.start();
		
		c.addObserver(this);

	}

	public void pintar() {

	}

	public void tecla() {

	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
