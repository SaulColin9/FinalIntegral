package finalPC;



public class IntegralThread implements Runnable {
	
	private double a;
	private double b;
	
	
	public IntegralThread(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	
	@Override
	public void run() {
		try {
			
			Main.mutex.acquire();
			IntegralClass.calcIntegral(a, b);
			Main.mutex.release();
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

}
