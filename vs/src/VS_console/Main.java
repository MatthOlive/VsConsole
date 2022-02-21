package VS_console;

import java.util.concurrent.Semaphore;


public class Main 
{
	public static boolean timer = true;
	private static int capacidadeCesto=5;
	public static Cesto cesto = new Cesto(capacidadeCesto);
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore bufferCheio;
	public static Semaphore bufferVazio;
	public static int consumidores, produtores;
	public static void main(String[] args) 
	{	 		
		produtores = Integer.parseInt(args[1]);
		consumidores = Integer.parseInt(args[2]);
		
		bufferCheio = new Semaphore(capacidadeCesto);
		bufferVazio = new Semaphore(capacidadeCesto);
		try
		{
			bufferCheio.acquire(capacidadeCesto);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
		for(int i = 0; i < produtores;i++) 
		{
			Produtor produtor = new Produtor(cesto,i);
			new Thread(produtor).start();
		}
		for(int i = 0; i < consumidores;i++) 
		{
			Consumidor consumidor = new Consumidor(cesto,i);
			new Thread(consumidor).start();
		}
		try {
		Thread.sleep(Long.parseLong(args[0]));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Thread Main acabou");
		
		timer = false;
		return;
		
	}
	
	
}
