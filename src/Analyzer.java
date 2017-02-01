import java.util.Arrays;

/**
 * Provides methods to analyze sorting algorithms.
 * 
 * @author Mateus Pires Lustosa
 */
public class Analyzer {

	private SortingAlgorithmRunner[] algorithms;
	
	public Analyzer(SortingAlgorithmRunner[] algorithms) {
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
	public <T extends Comparable<T>> Analysis[] analyze(T[] items, boolean check)
	{
		Analysis[] analyses = new Analysis[this.algorithms.length];
		
		T[] sortedData = null;
		if (check)
		{
			sortedData = Arrays.copyOf(items, items.length);
			Arrays.sort(sortedData);
		}
		
		for (int alg = 0; alg < this.algorithms.length; alg++)
		{
			T[] data = Arrays.copyOf(items, items.length);
			long time = System.currentTimeMillis();
			
			this.algorithms[alg].run(data);
			
			time = System.currentTimeMillis() - time;
			analyses[alg] = new Analysis(time, 1);
			
			if (check && !Arrays.equals(data, sortedData))
				throw new AlgorithmResultException();
		}
		
		return analyses;
	}
	
}
