package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import algorithms.IntSortingAlgorithmRunner;
import algorithms.IntSortingAlgorithms;
import algorithms.SortingAlgorithmRunner;
import algorithms.SortingAlgorithms;
import algorithms.Util;

public class Assignment04
{
	public static final IntSortingAlgorithmRunner[] algorithms = new IntSortingAlgorithmRunner[] {
			new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.quick(items);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] list) {
					IntSortingAlgorithms.shell(list);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] list) {
					IntSortingAlgorithms.counting(list);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] list) {
					IntSortingAlgorithms.bucket(list);
				}
			},
			new IntSortingAlgorithmRunner() {
				public void run(int[] list) {
					IntSortingAlgorithms.radix(list);
				}
			}
	};
	
	public static final SortingAlgorithmRunner[] stringAlgorithms = new SortingAlgorithmRunner[] {
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] list) {
					SortingAlgorithms.quick(list);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] list) {
					SortingAlgorithms.merge(list);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] list) {
					SortingAlgorithms.heap(list);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] list) {
					SortingAlgorithms.shell(list);
				}
			},
			new SortingAlgorithmRunner() {
				public <T extends Comparable<T>> void run(T[] list)
				{
					if (!(list instanceof String[]))
						throw new UnsupportedOperationException();
					
					SortingAlgorithms.radix((String[]) list);
				}
			}
	};
	
	
	public static final String[] algorithmsNames = new String[] {
			"Quicksort",
			"Shell sort",
			"Counting sort",
			"Bucket sort",
			"Radix sort"
	};
	
	public static final String[] stringAlgorithmsNames = new String[] {
			"Quicksort",
			"Merge sort",
			"Heap sort",
			"Shell sort",
			"Radix sort"
	};
	
	public static final int ALL = 0x11111;
	
	public static boolean commentsAllowed = false;
	public static boolean csvOutput = false;
	public static boolean stringInput = false;
	public static boolean ignoring = false;
	public static int numberOfRuns = 1;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		int index = 0;
		int algorithmNumber = ALL;
		
		while (index < args.length)
		{
			if (args[index].equalsIgnoreCase("--commented"))
			{
				commentsAllowed = true;
				comment("Output commented");
			}
			else if (args[index].equalsIgnoreCase("--csv"))
			{
				csvOutput = true;
				comment("CSV output");
			}
			else if (args[index].equalsIgnoreCase("--string"))
			{
				stringInput = true;
				comment("String input");
			}
			else if (args[index].startsWith("--runs="))
			{
				numberOfRuns = Integer.parseInt(args[index].substring(7, args[index].length()));
				comment("Set number of runs to " + numberOfRuns);
			}
			else
			{
				boolean ignore;
				
				if (args[index].startsWith("--include="))
				{
					comment("Including algorithms");
					ignore = false;
					algorithmNumber = 0;
				}
				else if (args[index].startsWith("--ignore="))
				{
					comment("Ignoring algorithms");
					ignore = true;
					algorithmNumber = ALL;
				}
				else break;
				
				// First number index
				int ignoreIndex = ignore ? 9 : 10;
				
				while (ignoreIndex < args[index].length())
				{
					int number = Integer.parseInt(String.valueOf(args[index].charAt(ignoreIndex)));
					
					comment((ignore ? "Ignoring " : "Including ") + number);
					algorithmNumber += (ignore ? -1 : 1) * (1 << (number - 1));
					
					ignoreIndex++;
				}
			}
			
			index++;
		}
		
		comment("All flags processed");
		
		if (args.length == 0 || args[index].equalsIgnoreCase("--test"))
		{
			comment("Testing");
			
			int length, range;
			
			if (args.length == 0)
			{
				Scanner scanner = new Scanner(System.in);
				
				length = scanner.nextInt();
				range = scanner.nextInt();
				
				scanner.close();
			}
			else
			{
				length = Integer.parseInt(args[++index]); 
				range = Integer.parseInt(args[++index]);
			}
			
			int[][] data = new int[numberOfRuns][];
			
			for (int i = 0; i < data.length; i++)
				data[i] = Util.generateRandomIntegers(length, false, range);
			
			runAll(data, algorithmNumber);
		}
		else if (args[index].equalsIgnoreCase("--file"))
		{
			index++;
			
			comment("Reading file " + args[index]);
			File file = new File(args[index]);
			
			Scanner scanner = new Scanner(file);
			
			if (stringInput)
			{
				String[] data = readStrings(scanner);
				runAll(data, algorithmNumber);
			}
			else
			{
				int[] data = read(scanner);
				runAll(new int[][] { data }, algorithmNumber);
			}
			
			scanner.close();
		}
		else // if (args[index].equalsIgnoreCase("--std"))
		{
			comment("Reading from stdin");
			
			Scanner scanner = new Scanner(System.in);
			
			if (stringInput)
			{
				String[] data = readStrings(scanner);
				runAll(data, algorithmNumber);
			}
			else
			{
				int[] data = read(scanner);
				runAll(new int[][] { data }, algorithmNumber);
			}
			
			scanner.close();
		}
	}
	
	private static int[] read(Scanner scanner)
	{
		int size = scanner.nextInt();
		int[] data = new int[size];
		
		for (int i = 0; i < size; i++)
			data[i] = scanner.nextInt();
		
		return data;
	}
	
	private static String[] readStrings(Scanner scanner)
	{
		int size = 0;
		
		do try { size = scanner.nextInt(); }
		catch (InputMismatchException ime) { }
		while (size == 0 && scanner.hasNext());
		
		comment("Reading " + size + " entries");
		
		String[] data = new String[size];
		
		scanner.skip("\\s");
		
		for (int i = 0; i < size; i++)
		{
			data[i] = scanner.nextLine();
			
			// Fix std input
			if (data[i].isEmpty()) i--;
		}
		
		return data;
	}
	
	private static void runAll(int[][] data, int algorithmNumber)
	{
		boolean recurrence = false;
		
		int[] sorted = Arrays.copyOf(data[0], data[0].length);
		Arrays.sort(sorted);
		
		for (int i = 0; i < algorithms.length; i++) {
			if ((algorithmNumber & (0x1 << i)) > 0)
			{
				int[][] copy = new int[data.length][];
				
				for (int r = 0; r < data.length; r++)
					copy[r] = Arrays.copyOf(data[r], data[r].length);
				
				try {
					long time = run(copy, algorithms[i]);
					print(algorithmsNames[i], time, recurrence);
					
					if (!Arrays.equals(sorted, copy[0]))
						System.out.print(" (FAIL)");
				}
				catch (StackOverflowError e)
				{
					print(algorithmsNames[i], -1, recurrence);
					System.out.print(" (STACK OVERFLOW)");
				}
				
				recurrence = true;
				
				copy = null;
				System.gc();
				System.runFinalization();
			}
		}
	}
	
	private static long run(int[][] data, IntSortingAlgorithmRunner runner)
	{
		long time = System.currentTimeMillis();
		
		for (int r = 0; r < data.length; r++)
			runner.run(data[r]);
		
		return (System.currentTimeMillis() - time) / numberOfRuns;
	}
	
	private static void runAll(String[] data, int algorithmNumber)
	{
		boolean recurrence = false;
		
		String[] sorted = Arrays.copyOf(data, data.length);
		Arrays.sort(sorted);
		
		for (int i = 0; i < stringAlgorithms.length; i++) {
			if ((algorithmNumber & (0x1 << i)) > 0)
			{
				String[] copy = Arrays.copyOf(data, data.length);
				
				try {
					long time = run(copy, stringAlgorithms[i]);
					print(stringAlgorithmsNames[i], time, recurrence);
					
					if (!Arrays.equals(sorted, copy))
						System.out.print(" (FAIL)");
				}
				catch (StackOverflowError e)
				{
					print(stringAlgorithmsNames[i], -1, recurrence);
					System.out.print(" (STACK OVERFLOW)");
				}
				
				recurrence = true;
			}
		}
	}
	
	private static long run(String[] data, SortingAlgorithmRunner runner)
	{
		long time = System.currentTimeMillis();
		runner.run(data);
		return System.currentTimeMillis() - time;
	}
	
	private static void print(String name, long time, boolean recurrence)
	{
		if (csvOutput)
			System.out.print((recurrence ? ", " : "\n") + time);
		else
			System.out.println(name + ": " + time);
	}
	
	private static void comment(String comment) {
		if (commentsAllowed) System.out.println(">> " + comment);
	}
}
