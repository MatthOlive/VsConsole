package VS_console;

import java.util.concurrent.Semaphore;


public class Main 
{
	private static boolean timer = true;
	private static int capacidadeCesto=5;
	public static Cesto cesto;
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore bufferCheio;
	public static Semaphore bufferVazio;
	public static int consumidores, produtores;
	public static void main(String[] args) 
	{	 
		
		
		cesto = new Cesto(capacidadeCesto);
		produtores = Integer.parseInt(args[1]);
		consumidores = Integer.parseInt(args[2]);
		
		bufferCheio = new Semaphore(capacidadeCesto);
		bufferVazio = new Semaphore(capacidadeCesto);
		
		for(int i = 0; i < produtores;i++) 
		{
			new Thread(produtor).start();
		}
		for(int i = 0; i < consumidores;i++) 
		{
			new Thread(consumidor).start();
		}
		try {
		Thread.sleep(Long.parseLong(args[0]));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		timer = false;
		return;
		
	}
	
	private static Runnable produtor = new Runnable() 
	{
		public void run() 
		{
			Produtor produtor = new Produtor(cesto);
			
			while(timer) 
			{
				produtor.produzir();
			}
		}
	};
	
	private static Runnable consumidor = new Runnable()
	{
		public void run()
		{
			Consumidor consumidor = new Consumidor(cesto);
			
			while(timer)
			{
				consumidor.consumir();
			}
		}
	};
	
	
}
