package controller;

import java.util.concurrent.Semaphore;

public class Banco_controle extends Thread
{
	int cod_conta;
	Semaphore semaforo_saque;
	Semaphore semaforo_deposito;
	
	public Banco_controle(int cod_conta, Semaphore semaforo_saque, Semaphore semaforo_deposito)
	{
		this.cod_conta = cod_conta;
		this.semaforo_saque = semaforo_saque;
		this.semaforo_deposito = semaforo_deposito;
	}
	
	public void run()
	{
		int saldo_conta = (int)(Math.random()*10001);
		int valor_transacao = (int)(Math.random()*(saldo_conta)+1);
		int tipo_transacao = (int)(Math.random()*2+1);
		
		if(tipo_transacao == 1)
		{
			try 
			{
				semaforo_saque.acquire(1);
				saque(cod_conta, saldo_conta, valor_transacao);
			} 
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
			finally
			{
				semaforo_saque.release(1);
			}
		}
		else
		{
			try 
			{
				semaforo_deposito.acquire(1);
				deposito(cod_conta, saldo_conta, valor_transacao);
			} 
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
			finally
			{
				semaforo_deposito.release(1);
			}
		}
	}
	
	private void saque(int cod_conta, int saldo, int valor_transacao)
	{
		System.err.println("Thread #" + getId() + ", conta: " + cod_conta + ", iniciou um saque \nvalor atual: RS" + saldo + ", valor de saque: RS" + valor_transacao +"\n");
		try 
		{
			sleep(2000);
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}

	private void deposito(int cod_conta, int saldo, int valor_transacao)
	{
		System.out.println("Thread #" + getId() + ", conta: " + cod_conta + ", iniciou um deposito \nvalor atual: RS" + saldo + ", valor de deposito: RS" + valor_transacao +"\n");

		try 
		{
			sleep(2000);
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}

}