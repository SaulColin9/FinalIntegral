package finalPC;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class ThreadManager {
	
	public static int multiThreadIntegral() {
		
		int a = 0;
		int b = 0;
		IntegralClass.delta = Main.maxThreads;
		//Recibe la entrada de a, b y sub-delta
		do {

			try {
				
				//System.out.print("Ingresa el valor de a: ");
				//Ingresa el valor de a
				a = 2;
				
				//System.out.print("Ingresa el valor de b: ");
				//Ingresa el valor de b
				b = 20;
				
				if(a>b) {
					System.out.println("El valor de a debe ser menor que b.");
					return -1;
				}
				
				//System.out.print("Ingresa el valor de sub-delta: ");
				IntegralClass.subDelta = 1;
				
			}catch(InputMismatchException e)	{
				System.out.println("Los intervalos a y b deben ser ambos enteros. ");
				return -1;
				
			}

			
		}while((a > b));
		
	
		//Esto es tan sólo para mostrar los tiempos de inicio y fin del calculo
		Date inicio = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		System.out.println("Inicio: " + sdf.format(inicio));
		
		IntegralClass.sum = (b-a)/(double)IntegralClass.delta;
		
		List<Thread> threadList = new ArrayList<Thread>();
		
		//Se ocupara de almacenar el valor anterior de a
		double a_prev = a;
		//Se ocupara de almacenar el valor creciente de a
		double a_next = a;

		//La variable jmps es el numero de segmentos completados
		//Se terminará de ejecutar una vez se hallan completado los delta segmentos
		
		for(int jmps = 0; jmps < IntegralClass.delta ;)
		{
		/*
		 * i es el numero de threads que se han añadido en la lista
		 * hasta el momento dentro de ese ciclo
		 */
			for(int i = 0; i < Main.maxThreads; i++, jmps++) {
				if(jmps >= IntegralClass.delta) {
					/*
					 * Si ya el numero de saltos es igual o supera al numero de segmentos,
					 * entonces salte del forloop y ejecuta los threads que estan ahora
					 * mismo en la lista
					 * 
					 * Esto de tener dos varirables contadoras pierde sentido
					 * cuando delta y maxThrerads son iguales
					 * que es el caso de este momento...
					 * 
					 */
					
					break;
				}
				//a_next es el intervalo "b" que se pasará a al calculo de la integral
				a_next += IntegralClass.sum;
				//Se van añadiendo los threads a la threadList para no perder referencia de ellos
				threadList.add(new Thread(new IntegralThread(a_prev, a_next)));
				/**
				 * a_prev es el intervalo "a" 
				 * Esto se hace para mantener referencia del ultimo intervalo superior 
				 * para en la siguiente iteración tomarlo como el intervalo inicial 
				 * debido a que a_next irá cambiando en cada iteración
				 */
				a_prev = a_next;
			}
			//Se ejecutan todos los threads que están actualmente en la lista
			threadList.forEach((thread) -> thread.start());
			//Se vacía la lista puesto que los threads que están ahora mismo ya no nos interesan
			//por que ya se ejecutaron
			threadList.clear();
		}
		//Muestra el tiempo final del calculo
		Date fin = new Date();
		System.out.println("Fin: " + sdf.format(fin));
		
		return 0;	
	}
}
