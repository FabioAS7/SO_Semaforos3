package controller;

import java.util.concurrent.Semaphore;

public class Controle_pista extends Thread
{
	int idCorredor;
	Semaphore semaforo_controle_pista;
	static int cnt = 0;
	static int[][] gridLargada = new int[3][14];
	
	public Controle_pista(int idCorredor, Semaphore semaforo_controle_pista)
	{
		this.idCorredor = idCorredor;
		this.semaforo_controle_pista = semaforo_controle_pista;
	}
	
	public void run()
	{
		gridLargada[0][idCorredor] = idCorredor;
		
		try
		{
			semaforo_controle_pista.acquire();
			corrida();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			semaforo_controle_pista.release();
		}

		
		if(cnt==14)
		{
			posicao_grid();	
		}
	}
	
	private void corrida() throws Exception
	{
		int volta, melhorVolta=0;
		
		for(int i=1; i<4; i++)
		{
			volta = (int)(Math.random()*(10000-5000)+5000);
			sleep(600);
			System.out.println("Corredor: " + idCorredor + ", fez a " + i + " volta em: " + volta);
			if(melhorVolta < volta)
			{
				melhorVolta = volta;
			}
		}
		cnt++;
		gridLargada[2][idCorredor] = melhorVolta;
	}
	
	private void posicao_grid()
	{
		int aux[][] = new int [3][1];
		
		for(int i=0; i<14; i++)
		{
			for(int j=i+1; j<14; j++)
			{
				if(gridLargada[2][i]>gridLargada[2][j])
				{
					aux[0][0] = gridLargada[0][j];
					aux[1][0] = gridLargada[1][j];
					aux[2][0] = gridLargada[2][j];
					
					gridLargada[0][j] = gridLargada[0][i];
					gridLargada[1][j] = gridLargada[1][i];
					gridLargada[2][j] = gridLargada[2][i];
					
					gridLargada[0][i] = aux[0][0];
					gridLargada[1][i] = aux[1][0];
					gridLargada[2][i] = aux[2][0];
				}
			}
		}
		
		System.err.println("\nGrid de Largada");
		for(int i=0; i<14; i++)
		{
			System.out.println("Corredor: " + gridLargada[0][i] + ", Time: " + gridLargada[1][i] + ", Melhor volta: " + gridLargada[2][i]);
		}
	}
	
}