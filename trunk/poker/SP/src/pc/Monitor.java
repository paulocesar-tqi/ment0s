package pc;

public class Monitor {

	public static void main(String[] args) {
		Thread threadCDN = new Thread(new CDNMonitor());
		Thread threadGatry = new Thread(new GatryMonitor());
		
		threadCDN.start();
		threadGatry.start();

	}

}
