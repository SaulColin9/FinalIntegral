package finalPC;


public class IntegralClass {
	//delta es el numero de segmentos
	public static int delta = 1; 
	public static double result = 0;
	public static double sum;
	public static int subDelta = 1;
	
	public static double fun(double x) {
		return 3*x;
	}
	
	//a y b son los limites dentro los cuales queremos integrar
	public static void calcIntegral(double a, double b) {	
		double a_prev = a; 
		double jmpDelta = (b-a)/subDelta;
		
		for(int i = 0; i < subDelta; i++) {
			 a += jmpDelta;
			//f(x)*deltaX: donde f(x) es la altura y deltaX es la base
			result += fun(a)*( Math.abs( a - a_prev) );
			a_prev = a;
			
		}
		
	}
	
}