package VS_console;

import java.util.Random;

public class Produtor implements Runnable {
	
	Cesto colocar;
	int identidade;
	
	Produtor(Cesto cesto, int id)
	{
		colocar = cesto;
		identidade = id;
	}
	
	public void produzir() 
	{
		try 
		{
			Main.bufferVazio.acquire();
		} 
		catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		int produzido = new Random().nextInt();
		System.out.println("A thread: "+identidade+" Produziu: "+produzido);	
		colocar.inserir(produzido);
		try
		{
			Thread.sleep(500);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		while(Main.timer) 
		{
			produzir();
		}
		System.out.println("A thread produtora "+identidade +" acabou");
		
	}
}
