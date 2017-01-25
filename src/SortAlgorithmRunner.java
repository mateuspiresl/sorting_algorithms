/**
 * Interface para executar algorítmo com lista.
 * 
 * @author Mateus Pires Lustosa
 */

public interface SortAlgorithmRunner {

	public <T extends Comparable<T>> void run(T[] items);
	
}
