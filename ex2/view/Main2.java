package view;

import java.util.concurrent.Semaphore;
import controller.Thread_corredor;

public class Main2 
{
	public static void main(String args[])
	{
		Semaphore semaforo = new Semaphore(1);
		
		for(int i=0; i<4; i++)
		{
			Thread_corredor Thread = new Thread_corredor(i, semaforo);
			Thread.start();
		}
	}
}
