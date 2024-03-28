package controller;

import java.util.concurrent.Semaphore;

public class SemaforosECarros extends Thread
{
	int carro;
	Semaphore semaforo;
	static int sentido=0;
	
	public SemaforosECarros(int carro, Semaphore semaforo)
	{
		this.carro = carro;
		this.semaforo = semaforo;
	}

	public void run()
	{
		try
		{
			semaforo.acquire();
			atravessar_cruzamento();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			semaforo.release();
		}
	}
	
	private void atravessar_cruzamento()
	{
		String diracao = (sentido==0?"Norte":(sentido==1?"Sul":(sentido==2?"Leste":"Oeste")));
		
		System.out.println("Thread #" + getId() + ", Carro " + carro + " atravessa o cruzamento seguindo para o " + diracao);
		
		sentido = sentido + 1;
	}
}
