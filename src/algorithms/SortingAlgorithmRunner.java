package algorithms;
/**
 * Interface to run an sorting algorithm on an array.
 * 
 * @author Mateus Pires Lustosa
 */
public interface SortingAlgorithmRunner {
	public <T extends Comparable<T>> void run(T[] items);
}