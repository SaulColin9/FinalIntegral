package finalPC;

import java.util.concurrent.Semaphore;


public class Main {
	
	public static Semaphore mutex = new Semaphore(1);
	public static int maxThreads = Runtime.getRuntime().availableProcessors()*2;
	
	public static void main(String args[]) {
		
		
		ThreadManager.multiThreadIntegral();
		System.out.println();
		try {
			Thread.sleep(2000);
			System.out.println("Result: " + IntegralClass.result);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
}
