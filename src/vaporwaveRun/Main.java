package vaporwaveRun;

import processing.core.PApplet;

public class Main extends PApplet{
	//Camilo Jose Montoya A. PER - Taller 1 - VaporwaveRun
	private Logica log;
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		PApplet.main("vaporwaveRun.Main");
	}
	
	@Override
	public void settings(){
		size(1000,700);
	}
	
	@Override
	public void setup(){
		log = new Logica(this);
	}
	
	@Override
	public void draw(){
		background(0);
		log.pintar();
	}
	
	@Override
	public void keyPressed(){
		log.tecla();
	}

}