import java.util.Arrays;

/**
 * Provides methods to analyze sorting algorithms.
 * 
 * @author Mateus Pires Lustosa
 */
public class Analyzer<T extends Comparable<T>> {

	private SortAlgorithmRunner[] algorithms;
	
	public Analyzer(SortAlgorithmRunner[] algorithms) {
		this.algorithms = algorithms;
	}
	
	/**
	 * Analyzes each algorithm on the data.
	 * @param items data.
	 * @return the Analyze of each algorithm.
	 * @throws AlgorithmResultException if the result of an algorithm is
	 * 		   different from the result of the
	 * 		   {@link Arrays#sort(Object[]) Java's sorting algorithm}
	 */
	public Analysis[] analyze(T[] items)
	{
		Analysis[] analyses = new Analysis[this.algorithms.length];
		
		T[] sortedData = Arrays.copyOf(items, items.length);
		Arrays.sort(sortedData);
		
		for (int alg = 0; alg < this.algorithms.length; alg++)
		{
			T[] data = Arrays.copyOf(items, items.length);
			long time = System.currentTimeMillis();
			
			this.algorithms[alg].run(data);
			
			analyses[alg] = new Analysis(System.currentTimeMillis() - time, 1);
			
			if (!Arrays.equals(data, sortedData))
				throw new AlgorithmResultException();
		}
		
		return analyses;
	}
	
}