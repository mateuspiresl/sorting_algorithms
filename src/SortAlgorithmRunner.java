/**
 * Interface to run an sorting algorithm on an array.
 * 
 * @author Mateus Pires Lustosa
 */
public interface SortAlgorithmRunner {
	public <T extends Comparable<T>> void run(T[] items);
}
