/**
 * Classe que armazena os dados resultantes da análise.
 * 
 * @author Mateus Pires Lustosa
 */
public class Analyse {

	public final long time;
	public final int runs;
	
	public Analyse(long time, int runs)
	{
		this.time = time;
		this.runs = runs;
	}
	
	/* Retorna a média de tempo. */
	public double getTime() { return time / (double) runs; }
	
}
