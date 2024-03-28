package view;

import java.util.concurrent.Semaphore;
import controller.SemaforosECarros;

public class Main 
{
	public static void main (String args[])
	{
		Semaphore semaforo = new Semaphore(1);
		
		for(int i=0; i<4; i++)
		{
			SemaforosECarros Thread = new SemaforosECarros(i, semaforo);
			Thread.start();
		}
	}
}
