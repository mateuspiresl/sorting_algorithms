package assignments;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import algorithms.DoubleSortingAlgorithms;
import algorithms.IntSortingAlgorithmRunner;
import algorithms.IntSortingAlgorithms;
import algorithms.SortingAlgorithms;
import algorithms.Util;

public class Assignment04Heuristic {

	public static boolean all = false;
	public static boolean check = false;
	public static boolean csv = false;
	public static boolean choiceOnly = false;
	
	public static void main(String[] args)
	{
		int index = 0;
		while (index < args.length)
		{
			if (args[index].equalsIgnoreCase("--all"))
				all = true;
			
			else if (args[index].equalsIgnoreCase("--check"))
				check = true;
			
			else if (args[index].equalsIgnoreCase("--csv"))
				csv = true;
			
			else if (args[index].equalsIgnoreCase("--choice"))
				choiceOnly = true;
			
			else break;
			
			index++;
		}
		
		InputStream input = System.in;
		
		if (index < args.length && args[index].equalsIgnoreCase("--test"))
		{			
			int length = Integer.parseInt(args[++index]); 
			int range = Integer.parseInt(args[++index]);
			
			int[] data = Util.generateRandomIntegers(length, false, range);
			
			StringBuilder inputString = new StringBuilder();
			inputString.append(length + "\n");
			
			for (int element : data)
				inputString.append(element + "\n");
			
			input = new ByteArrayInputStream(inputString.toString().getBytes(StandardCharsets.UTF_8));
		}
		
		heuristic(input);
	}
	
	public static String heuristic(InputStream input)
	{
		Scanner scanner = new Scanner(input);
		int size = scanner.nextInt();
		
		if (scanner.hasNextInt())
		{
			if (!csv) System.out.println("Found an integer");
			return processInteger(size, scanner);
		}
		else if (scanner.hasNextDouble())
		{
			if (!csv) System.out.println("Found a double");
			processDouble(size, scanner);
		}
		else
		{
			if (!csv) System.out.println("Found a string");
			processString(size, scanner);
		}
		
		return "";
	}

	public static String processInteger(int size, Scanner scanner)
	{
		int[] vector = new int[size];
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		
		for(int i = 0; i < size; i++)
		{
			vector[i] = scanner.nextInt();
			
			if (vector[i] > max)
				max = vector[i];
			
			if (vector[i] < min)
				min = vector[i];
		}
		
		int range = max - min;
		if (!csv) System.out.println("Size: " + size + ", Range: " + range);
		
		int[] sorted = null;
		if (check)
		{
			sorted = Arrays.copyOf(vector, vector.length);
			Arrays.sort(sorted);
		}
		
		boolean caught = false;
		String choice = null;
		
		if (all || (caught = (size <= range)))
		{
			choice = "Q";
			if (choiceOnly) return choice;
			
			if (!csv) System.out.println("Chosen algorithm: Quick sort");
			
			run(vector, sorted, new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.quick(items);
				}
			});
		}
		
		if (all || (!caught && (size >= 1000000 && range >= 7000000)))
		{
			choice = "R";
			if (choiceOnly) return choice;
			
			if (!csv) System.out.println("Chosen algorithm: Radix sort");
			
			run(vector, sorted, new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.radix(items);
				}
			});
		}
		
		if (all || !caught)
		{
			choice = "C";
			if (choiceOnly) return choice;
			
			if (!csv) System.out.println("Chosen algorithm: Counting sort");
			
			run(vector, sorted, new IntSortingAlgorithmRunner() {
				public void run(int[] items) {
					IntSortingAlgorithms.counting(items);
				}
			});
		}
		
		return choice;
	}
	
	private static void run(int[] vector, int[] sorted, IntSortingAlgorithmRunner runner)
	{
		if (all) vector = Arrays.copyOf(vector, vector.length);
		
		long time = System.currentTimeMillis();
		runner.run(vector);
		time = System.currentTimeMillis() - time;
		
		if (!check || Arrays.equals(sorted, vector))
			System.out.println(!csv ? "Time: " + time + " ms" : time);
		
		else if (check)
			if (!csv) System.out.println("Failed!");
	}
	
	private static void processDouble(int size, Scanner scanner)
	{
		double[] vector = new double[size];
		
		for(int i = 0; i < size; i++)
			vector[i] = scanner.nextInt();
		
		double[] sorted = null;
		if (check)
		{
			sorted = Arrays.copyOf(vector, vector.length);
			Arrays.sort(sorted);
		}
		
		if (!csv) System.out.println("Chosen algorithm: Quick sort");
		
		long time = System.currentTimeMillis();
		DoubleSortingAlgorithms.quick(vector);
		time = System.currentTimeMillis() - time;
		
		if (!check || Arrays.equals(sorted, vector))
			System.out.println(!csv ? "Time: " + time + " ms" : time);
		
		else if (check)
			if (!csv) System.out.println("Failed!");
	}
	
	public static void processString(int size, Scanner scanner)
	{
		String[] vector = new String[size];
		scanner.skip("\\s");
		
		for(int i = 0; i < size; i++)
			vector[i] = scanner.nextLine();
		
		String[] sorted = null;
		if (check)
		{
			sorted = Arrays.copyOf(vector, vector.length);
			Arrays.sort(sorted);
		}
		
		String[] copy = vector;
		
		if (size >= 10000)
			if (!csv) System.out.println("Chosen algorithm: Quick sort");
		else
			if (!csv) System.out.println("Chosen algorithm: Heap sort");
		
		boolean caught = false;
		
		if (all || (caught = size >= 10000))
		{
			System.out.println("Sorting with Quick sort");
			
			if (all) vector = Arrays.copyOf(copy, copy.length);
			
			long time = System.currentTimeMillis();
			SortingAlgorithms.quick(vector);
			time = System.currentTimeMillis() - time;
			
			if (!check || Arrays.equals(sorted, vector))
				System.out.println(!csv ? "Time: " + time + " ms" : time);
			
			else if (check)
				if (!csv) System.out.println("Failed!");
		}
		
		if (all || !caught)
		{
			System.out.println("Sorting with Heap sort");
			
			if (all) vector = copy;
			
			long time = System.currentTimeMillis();
			SortingAlgorithms.heap(vector);
			time = System.currentTimeMillis() - time;
			
			if (!check || Arrays.equals(sorted, vector))
				System.out.println(!csv ? "Time: " + time + " ms" : time);
			
			else if (check)
				if (!csv) System.out.println("Failed!");
		}
	}
	
}
