/**
 * Classe principal.
 * Programa que gera dados e testa cada algoritmo para um dado tamanho da lista.
 * 
 * @author Mateus Pires Lustosa (Matrícula: 11408115) 
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Program {

	private static final String ERROR_MESSAGE = "Uso: sort_algorithms n\nParâmetros:\n\tn - número de itens na lista, n >= 1"; 
	
	public static void main(String[] args)
	{	
		if (args.length == 0)
		{
			Scanner scanner = new Scanner(System.in);
			analyse(scanner.nextInt());
			scanner.close();
		}
		else if (args.length == 1)
		{
			try {
				analyse(Integer.parseInt(args[1]));
			}
			catch (NumberFormatException nfe) {
				System.out.println(ERROR_MESSAGE);
			}
		}
		else
		{
			System.out.println(ERROR_MESSAGE);
		}
	}
	
	private static void analyse(int n)
	{
		Student[] data = Student.generateData(n);
		Analyse[] analyses = new Analyser(algorithms).analyse(data);
		
		printArray(data);
		printAnalyses(analyses);
	}
	
	private static void printAnalyses(Analyse[] analyses)
	{
		for (int i = 0; i < analyses.length; i++)
			System.out.println(
					algorithmsNames[i] + ": " +
					new DecimalFormat("#.##").format(analyses[i].getSwapCount()).replace(",", ".") + " " +
					new DecimalFormat("#.##").format(analyses[i].getTime()).replace(",", ".")
				);
	}
	
	public static <T> void printArray(T[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
		
		System.out.println("");
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
