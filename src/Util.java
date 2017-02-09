import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Main class.
 * Program that analyzes sorting algorithms and sorts data.
 * 
 * @author Mateus Pires Lustosa 
 */
public class Util {
	
	private Util() { }

	// List of algorithms
	public static final String[] algorithmsNames = new String[] {
			"Java's",
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
					Arrays.sort(items);
				}
			},
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
					SortingAlgorithms.quick(items);
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
	
	public static final IntSortingAlgorithmRunner[] intAlgorithms = new IntSortingAlgorithmRunner[] {
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					Arrays.sort(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.selection(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.insertion(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.quick(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.merge(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.heap(items);
				}
			}
		};
	
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
	
}
