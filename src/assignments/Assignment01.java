package assignments;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import algorithms.SortingAlgorithmRunner;
import algorithms.SortingAlgorithms;
import algorithms.Util;

/**
 * APA - Assignment 01 
 * 
 * @author Mateus Pires Lustosa - mateusplpl@gmail.com
 */
public class Assignment01 {

	private static final String ERROR_MESSAGE = "Use:\n\tn - algorithm number"; 

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
	
	public static void main(String[] args)
	{
		try {
			if (args.length == 1)
			{
				int algorithmNumber = Integer.parseInt(args[0]) - 1;
				Scanner scanner = new Scanner(System.in);

				// The first integer is the number of entries
				int n = scanner.nextInt();
				Integer[] data = new Integer[n];
				
				// Reads n entries
				while (n-- > 0) data[n] = scanner.nextInt();
				
				scanner.close();
				
				algorithms[algorithmNumber].run(data);
				Util.printArray(data);
			}
			else System.out.println(ERROR_MESSAGE);
		}
		catch (InputMismatchException ime) {
			ime.printStackTrace();
			System.out.println(ERROR_MESSAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fatal error!");
		}
	}
	
}
