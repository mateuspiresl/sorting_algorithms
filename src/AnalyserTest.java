/**
 * Classe que gera os dados dos testes.
 * 
 * @author Mateus Pires Lustosa
 */

import java.text.DecimalFormat;

public class AnalyserTest {

	public static final boolean SHEETS_PARSE = true;
	
	public static void main(String[] args)
	{
		Analyser analyser = new Analyser(algorithms);
		
		for (int len = 200; len <= 10000; len += 200)
		{
			Analyse[] analyses = analyser.analyse(len, 100);
			
			if (SHEETS_PARSE)
				printAnalysesForSheetsParse(len, 100, analyses);
			else
				printAnalyses(len, 100, analyses);
		}
	}
	
	/* Imprime os dados das Analyse.
	 * @param length - tamanho das listas.
	 * @param runs - número de listas para cada algoritmo.
	 * @param analyses - dados dos testes. */
	private static void printAnalyses(int length, int runs, Analyse[] analyses)
	{
		System.out.println("Analyse (" + length + " items, 100 runs):");

		for (int i = 0; i < analyses.length; i++)
			System.out.println(
					algorithmsNames[i] + ": " +
					new DecimalFormat("#.##").format(analyses[i].getSwapCount()).replace(",", ".") + " swaps, " +
					new DecimalFormat("#.##").format(analyses[i].getTime()).replace(",", ".") + " ms"
				);
		
		System.out.println();
	}
	
	/* Imprime os dados das Analyse em formato apropriado para .csv.
	 * @param length - tamanho das listas.
	 * @param runs - número de listas para cada algoritmo.
	 * @param analyses - dados dos testes. */
	private static void printAnalysesForSheetsParse(int length, int runs, Analyse[] analyses)
	{
		System.out.print(length);
		
		for (int i = 0; i < analyses.length; i++)
		{
			System.out.print(", " +
					((long) (analyses[i].getSwapCount() * 100)) + " " +
					((long) (analyses[i].getTime() * 100))
				);
		}
		
		System.out.println();
	}

	private static String[] algorithmsNames = new String[] {
			"Bubble",
			"Selection",
			"Insertion",
			"Shell"
	};
	
	private static AlgorithmRunner[] algorithms = new AlgorithmRunner[] {
			new AlgorithmRunner() {
				public <T extends Comparable<T>> long run(T[] items) {
					return SortAlgorithms.selection(items);
				}
			},
			new AlgorithmRunner() {
				public <T extends Comparable<T>> long run(T[] items) {
					return SortAlgorithms.insertion(items);
				}
			},
			new AlgorithmRunner() {
				public <T extends Comparable<T>> long run(T[] items) {
					return SortAlgorithms.shell(items);
				}
			}
	};

}
