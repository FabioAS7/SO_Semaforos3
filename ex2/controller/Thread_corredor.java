package controller;

import java.util.concurrent.Semaphore;

public class Thread_corredor extends Thread
{
	int pessoa;
	Semaphore semaforo;
	
	public Thread_corredor(int pessoa, Semaphore semaforo)
	{
		this.pessoa = pessoa;
		this.semaforo = semaforo;
	}

	public void run()
	{
		corredor();
		try 
		{
			semaforo.acquire();
			porta();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			semaforo.release();
		}
	}
	
	private void corredor()
	{
		int movimento, posicao=0;
		
		while(posicao <= 200)
		{
			movimento = (int)(Math.random()*(7-4)+4);
			posicao = posicao + movimento;
			
			System.out.println("Thread #" + getId() + ", pessoa " + pessoa + " andou +" + movimento + "m e esta atualmente na posicao " + posicao +"m");
			
			try 
			{
				sleep(1000);
			} 
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
		}
		System.out.println("Thread #" + getId() + ", pessoa " + pessoa + " chegou na porta");
	}
	
	private void porta()
	{
		int tempo = (int)(Math.random()*(2001-1000)+1000);
		
		System.out.println("Thread #" + getId() + ", pessoa " + pessoa + " esta abrindo a porta");
		
		try 
		{
			sleep(tempo);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		System.err.println("Thread #" + getId() + ", pessoa " + pessoa + " atravessou a porta");
	}
}