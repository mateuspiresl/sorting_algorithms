import java.text.DecimalFormat;

/**
 * Main class.
 * Program that analyzes sorting algorithms and sorts data.
 * 
 * @author Mateus Pires Lustosa 
 */
public class Util {
	
	private Util() { }
	
	public static void printAnalysis(Analysis[] analyses)
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
	public static final String[] algorithmsNames = new String[] {
			"Selection",
			"Insertion",
			"Quick",
			"Merge",
			"Heap"
		};
	
	// List of the algorithm's running interfaces
	public static final SortingAlgorithmRunner[] algorithms = new SortingAlgorithmRunner[] {
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.selection(items);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.insertion(items);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.quick(items, new SortingAlgorithms.PivotFinder<T>() {
						public int find(T[] items, int left, int right) {
							return (left + right) / 2; 
						}
					});
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.merge(items);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] items) {
					SortingAlgorithms.heap(items);
				}
			}
		};
	
}
