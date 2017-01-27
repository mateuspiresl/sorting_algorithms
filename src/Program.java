import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class.
 * Program that analyzes sorting algorithms and sorts data.
 * 
 * @author Mateus Pires Lustosa 
 */
public class Program {

	private static final String ERROR_MESSAGE = "Use:\n\tn - algorithm number"; 
	
	public static void main(String[] args)
	{
		try {
			// If no argument is provided, the first input is
			// taken as the amount of random data to generate.
			// The analysis of the sorting algorithms on that
			// data is printed in the end.
			if (args.length == 0)
			{
				Scanner scanner = new Scanner(System.in);
				int n = scanner.nextInt();
				scanner.close();
				
				Example[] data = Example.generateData(n);
				Analysis[] analyses = new Analyzer<Example>(algorithms).analyze(data);
				
				printAnalyses(analyses);
			}
			// If one is given, it's taken as the algorithm index.
			else if (args.length == 1)
			{
				int algorithmNumber = Integer.parseInt(args[0]);
				analyze(new Integer[0], algorithms[algorithmNumber]);
			}
			// Otherwise, the error message will be printed.
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
	
	/**
	 * Sort data from the input.
	 * @param type the data type.
	 * @param algorithm the index of the algorithm to use. 
	 */
	@SuppressWarnings({ "unchecked" })
	private static <T extends Comparable<T>> void analyze(T[] type, SortAlgorithmRunner algorithm)
	{
		Scanner scanner = new Scanner(System.in);
		List<T> list = new ArrayList<T>();
		
		// The first integer is the number of entries
		int n = scanner.nextInt();
		
		// Reads n entries
		while (n-- > 0)
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
	
	private static void printAnalyses(Analysis[] analyses)
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

	// List of algorithms
	private static final String[] algorithmsNames = new String[] {
			"Selection",
			"Insertion",
			"Quick",
			"Merge",
			"Heap"
		};
	
	// List of the algorithm's running interfaces
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
