/**
 * Classe principal.
 * Programa que gera dados e testa cada algoritmo para um dado tamanho da lista.
 * 
 * @author Mateus Pires Lustosa (Matrícula: 11408115) 
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	private static final String ERROR_MESSAGE = "Uso:\n\tn - número do algorítmo"; 
	
	public static void main(String[] args)
	{
		try {
			if (args.length == 0)
			{
				Scanner scanner = new Scanner(System.in);
				int n = scanner.nextInt();
				scanner.close();
				
				Example[] data = Example.generateData(n);
				Analyse[] analyses = new Analyser<Example>(algorithms).analyse(data);
				
				printAnalyses(analyses);
			}
			else if (args.length == 1)
			{
				int algorithmNumber = Integer.parseInt(args[0]);
				analyse(new Integer[0], algorithms[algorithmNumber]);
			}
			else
			{
				System.out.println(ERROR_MESSAGE);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private static <T extends Comparable<T>> void analyse(T[] type, SortAlgorithmRunner algorithm)
	{
		Scanner scanner = new Scanner(System.in);
		List<T> list = new ArrayList<T>();
		
		while (scanner.hasNextInt())
		{
			if (type instanceof Integer[])
				list.add((T) (Integer) scanner.nextInt());
			
			else if (type instanceof String[])
				list.add((T) scanner.nextLine());
		}
		
		scanner.close();
		
		T[] data = list.toArray(type);
		algorithm.run(data);
		printArray(data);
	}
	
	private static void printAnalyses(Analyse[] analyses)
	{
		for (int i = 0; i < analyses.length; i++)
			System.out.println(
					algorithmsNames[i] + ": " +
					new DecimalFormat("#.##").format(analyses[i].getTime()).replace(",", ".")
				);
	}
	
	public static <T> void printArray(T[] array)
	{
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
	
	public static <T> void printArray(T[] array, int begin, int end)
	{
		for (int i = begin; i <= end; i++)
			System.out.println(array[i]);
	}

	private static final String[] algorithmsNames = new String[] {
			"Selection",
			"Insertion",
			"Quick",
			"Merge",
			"Heap"
		};
	
	private static final SortAlgorithmRunner[] algorithms = new SortAlgorithmRunner[] {
			new SortAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.selection(items);
				}
			},
			new SortAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.insertion(items);
				}
			},
			new SortAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.quick(items, new SortingAlgorithms.PivotFinder<T>() {
						public int find(T[] items, int left, int right) {
							return (left + right) / 2; 
						}
					});
				}
			},
			new SortAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.merge(items);
				}
			},
			new SortAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.heap(items);
				}
			}
		};
	
}
