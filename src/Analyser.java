/**
 * Classe que faz a análise dos algoritmos.
 * 
 * @author Mateus Pires Lustosa
 */

import java.util.Arrays;

public class Analyser<T extends Comparable<T>> {

	private SortAlgorithmRunner[] algorithms;
	
	public Analyser(SortAlgorithmRunner[] algorithms) {
		this.algorithms = algorithms;
	}
	
	/**
	 * Analisa cada algoritmo com a array de items dada.
	 * @param items - dados.
	 * @return a Analyse de cada algoritmo.
	 */
	public Analyse[] analyse(T[] items)
	{
		Analyse[] analyses = new Analyse[this.algorithms.length];
		
		T[] sortedData = Arrays.copyOf(items, items.length);
		Arrays.sort(sortedData);
		
		for (int alg = 0; alg < this.algorithms.length; alg++)
		{
			T[] data = Arrays.copyOf(items, items.length);
			long time = System.currentTimeMillis();
			
			this.algorithms[alg].run(data);
			
			analyses[alg] = new Analyse(System.currentTimeMillis() - time, 1);
			
			if (!Arrays.equals(data, sortedData))
				throw new AlgorithmResultException();
		}
		
		return analyses;
	}
	
}
