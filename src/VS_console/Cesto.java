package VS_console;

public class Cesto {
	
	double [] arrey;
	int maxComp, posicao, numElemento, quantElemento;
	 
	Cesto(int valorComp) 
	{
		arrey = new double[valorComp];
		maxComp = valorComp;
		posicao = 0;	
		numElemento = 0;
		quantElemento = 0;
	}
	 
	public void inserir(double elemento) 
	{
		Main.mutex.tryAcquire();
		System.out.println("Arrey elemento"+ numElemento);
		if(quantElemento < maxComp) 
		{
			
		arrey[numElemento] = elemento;
		numElemento ++;
		quantElemento ++;
		if(numElemento>=maxComp)
		{
			numElemento=0;
			Main.bufferCheio.release(maxComp);
		}
		Main.mutex.release();
		}
	
	
	
	}
	
	public double retirada() 
	{
		double temporario=-1;
		Main.mutex.tryAcquire();
		if(quantElemento > 0) 
		
		{
		
		temporario = arrey[posicao];
		posicao ++;
		quantElemento --;
		if(posicao>=maxComp){
			posicao = 0;
		}
		}
		else 
		{
			Main.bufferVazio.release(maxComp);
		}
		Main.mutex.release();
		return temporario;
		
		
	}
	
	

}
