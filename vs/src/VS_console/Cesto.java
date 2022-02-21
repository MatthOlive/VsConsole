package VS_console;

public class Cesto {
	
	private int [] arrey;
	private int maxComp, posicao, numElemento, quantElemento;
	 
	Cesto(int valorComp) 
	{
		arrey = new int[valorComp];
		maxComp = valorComp;
		posicao = 0;	
		numElemento = 0;
		quantElemento = 0;
	}
	 
	public void inserir(int elemento) 
	{
		try 
		{
			Main.mutex.acquire();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}		
		if(quantElemento < maxComp) 
		{
			
		arrey[numElemento] = elemento;
		numElemento =  numElemento+1 ;
		quantElemento = quantElemento +1 ;		
		}
		if(numElemento>=maxComp)
		{
			numElemento=0;
			Main.bufferCheio.release(maxComp);
		}
		Main.mutex.release();
	
	
	
	}
	
	public Integer retirada() 
	{
		try 
		{
			Main.mutex.acquire();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		Integer temporario=null;		
		if(quantElemento > 0) 		
		{
		temporario = arrey[posicao];
		posicao ++;
		quantElemento --;
		if(posicao>=maxComp){
			posicao = 0;
		}

		}
		if(quantElemento==0) 
		{
			Main.bufferVazio.release(maxComp);
		}
		Main.mutex.release();
		return temporario;
		
	}
	
	

}
