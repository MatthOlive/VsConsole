package VS_console;

public class Consumidor implements Runnable {
	
	Cesto tirar;
	int identidade;
	
	Consumidor(Cesto cesto, int id)
	{
		tirar=cesto;
		identidade=id;
	}
	
	public void consumir() 
	{
		try 
		{
			Main.bufferCheio.acquire();
		} 
		catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		Integer consumido = tirar.retirada();
		if(consumido != null)
		{
			System.out.println("A thread: "+identidade+" Consumiu: "+consumido);			
		}
		try {
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
			consumir();
		}
		System.out.println("A thread consumidora "+identidade +" acabou");
		
	}

}
