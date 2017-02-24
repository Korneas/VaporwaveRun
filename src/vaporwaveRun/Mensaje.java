package vaporwaveRun;

import java.io.Serializable;

public class Mensaje implements Serializable {
	public String data;

	public Mensaje(String data) {
		this.data = data;
	}

	public String getContenido() {
		return data;
	}

	public void setContenido(String data) {
		this.data = data;
	}
}

