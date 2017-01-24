/**
 * Classe que faz a análise dos algoritmos.
 * 
 * @author Mateus Pires Lustosa
 */

import java.util.Arrays;

public class Analyser {

	private AlgorithmRunner[] algorithms;
	
	public Analyser(AlgorithmRunner[] algorithms) {
		this.algorithms = algorithms;
	}
	
	/* Gera n listas de determinado tamanho e testa todos os algoritmos.
	 * @param length - tamanho da lista a ser gerada
	 * @param times - número de listas a serem geradas
	 * @return a Analyse de cada algoritmo */
	public Analyse[] analyse(int length, int times)
	{
		Analyse[] analyses = new Analyse[algorithms.length];
		Student[][] dataSource = new Student[times][];
		Student[][] dataCopy = new Student[times][];
		
		while (--times >= 0)
			dataSource[times] = Student.generateData(length);
		
		for (int alg = 0; alg < algorithms.length; alg++)
		{
			copyBiArray(dataCopy, dataSource);
			
			long swapCount = 0;
			long time = System.currentTimeMillis();
			
			for (int data = 0; data < dataCopy.length; data++)
			{
				swapCount += this.algorithms[alg].run(dataCopy[data]);
			}
			
			analyses[alg] = new Analyse(swapCount, System.currentTimeMillis() - time, dataCopy.length);
		}
		
		return analyses;
	}
	
	/* Analisa cada algoritmo com a array de items dada.
	 * @param items - dados.
	 * @return a Analyse de cada algoritmo. */
	public <T extends Comparable<T>> Analyse[] analyse(T[] items)
	{
		Analyse[] analyses = new Analyse[algorithms.length];
		
		for (int alg = 0; alg < algorithms.length; alg++)
		{
			T[] data;
			
			if (alg == algorithms.length - 1)
				data = items;
			else
				data = Arrays.copyOf(items, items.length);
			
			long time = System.currentTimeMillis();
			long swapCount = this.algorithms[alg].run(data);
			
			analyses[alg] = new Analyse(swapCount, System.currentTimeMillis() - time, 1);
		}
		
		return analyses;
	}
	
	private static <T> void copyBiArray(T[][] dst, T[][] source)
	{
		int n = source.length;
		while (--n >= 0) dst[n] = Arrays.copyOf(source[n], source[n].length);
	}
	
}
