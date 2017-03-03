package vaporwaveRun;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

public class ConectadosRed extends Observable implements Runnable {
	
	public ConectadosRed() {
	}

	@Override
	public void run() {
		try {
			InetAddress local = InetAddress.getLocalHost();
			String hostIP = local.getHostAddress();
			String[] hosting = hostIP.split("\\.");
			hostIP = hosting[0] + "." + hosting[1] + "." + hosting[2];

			int espera = 250;

			for (int i = 1; i < 255; i++) {
				String host = hostIP + "." + i;
				if (InetAddress.getByName(host).isReachable(espera)) {
					System.out.println(host + " es alcanzable");
					
					setChanged();
					notifyObservers(host);
					clearChanged();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
