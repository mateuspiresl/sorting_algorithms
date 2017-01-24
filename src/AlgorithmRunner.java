/**
 * Interface para executar algorítmo com lista.
 * 
 * @author Mateus Pires Lustosa
 */

public interface AlgorithmRunner {

	public <T extends Comparable<T>> long run(T[] items);
	
}
