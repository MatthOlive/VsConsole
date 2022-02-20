package VS_console;

import java.util.Random;

public class Produtor {
	
	Cesto colocar;
	
	Produtor(Cesto cesto)
	{
		colocar = cesto;
	}
	
	public void produzir() 
	{
		Main.bufferVazio.tryAcquire();
		colocar.inserir(new Random().nextDouble());
		try
		{
			Thread.sleep(500);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
