package view;

import java.util.concurrent.Semaphore;

import controller.Banco_controle;

public class Main3
{
	public static void main(String args[])
	{
		Semaphore semaforoSaque = new Semaphore(1);
		Semaphore semaforoDeposito = new Semaphore(1);
		
		for(int i=0; i<20; i++)
		{
			Banco_controle Thread = new Banco_controle(i, semaforoSaque, semaforoDeposito);
			Thread.start();
		}
	}
}