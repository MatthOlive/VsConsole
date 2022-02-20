package VS_console;

public class Consumidor {
	
	Cesto tirar;
	
	Consumidor(Cesto cesto)
	{
		tirar=cesto;
	}
	
	public void consumir() 
	{
		Main.bufferCheio.tryAcquire();
		System.out.println("foi consumido: "+tirar.retirada());
		try {
			Thread.sleep(500);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
