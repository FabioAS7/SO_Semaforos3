package view;

import java.util.concurrent.Semaphore;
import controller.Controle_pista;

public class Main4
{
	
	// Esse exercicio nao foi terminado
	// nao consegui criar os times 1,2,3,4,5,6 e 7
	// nao consegui controlar a entrada na pista por times mas consegui limitar a entrada a 5
	
	public static void main(String args[])
	{
		Semaphore semaforo_controle_time = new Semaphore(1);
		Semaphore semaforo_controle_pista = new Semaphore(5);
		
		for(int i=0; i<14; i++)
		{
			Controle_pista Thread = new Controle_pista(i,semaforo_controle_pista);
			Thread.start();	
		}
	}
}