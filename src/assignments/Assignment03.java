package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import algorithms.IntSortingAlgorithmRunner;
import algorithms.IntSortingAlgorithms;
import algorithms.Util;

public class Assignment03
{
	public static final IntSortingAlgorithmRunner[] algorithms = new IntSortingAlgorithmRunner[] {
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
	
	public static final String[] algorithmsNames = new String[] {
			"Counting sort",
			"Bucket sort",
			"Radix sort"
	};
	
	public static boolean commentsAllowed = false;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		int index = 0;
		
		if (args[index].equalsIgnoreCase("--commented"))
		{
			commentsAllowed = true;
			comment("Output commented");
			index++;
		}
		
		// -1 means all the algorithm
		int algorithmNumber = -1;
		
		if (!args[index].equalsIgnoreCase("--all"))
			algorithmNumber = Integer.parseInt(args[index]);
		
		index++;
		
		if (args[index].equalsIgnoreCase("--test"))
		{
			comment("Testing");
			test(Integer.parseInt(args[index + 1]), algorithmNumber);
		}
		else if (args[index].equalsIgnoreCase("--file"))
		{
			index++;
			
			comment("Reading file " + args[index]);
			File file = new File(args[index]);
			
			Scanner scanner = new Scanner(file);
			int[] data = read(scanner);
			scanner.close();
			
			if (algorithmNumber == -1)
				runAll(data);
			else
			{
				comment(algorithmsNames[algorithmNumber] + ": " + run(data, algorithmNumber));
				Util.printArray(data);
			}
		}
		else // if (args[index].equalsIgnoreCase("--std"))
		{
			comment("Reading from stdin");
			
			Scanner scanner = new Scanner(System.in);
			int[] data = read(scanner);
			scanner.close();
			
			if (algorithmNumber == -1)
				runAll(data);
			else
			{
				comment(algorithmsNames[algorithmNumber] + ": " + run(data, algorithmNumber));
				Util.printArray(data);
			}
		}
	}
	
	private static int[] read(Scanner scanner)
	{
		int size = scanner.nextInt();
		int[] data = new int[size];
		
		while (--size >= 0)
			data[size] = scanner.nextInt();
		
		return data;
	}
	
	private static void runAll(int[] data)
	{
		for (int i = 0; i < algorithms.length; i++)
		{
			int[] copy = Arrays.copyOf(data, data.length);
			System.out.println(algorithmsNames[i] + ": " + run(copy, i));
		}
	}
	
	private static long run(int[] data, int algorithmNumber)
	{
		long time = System.currentTimeMillis();
		algorithms[algorithmNumber].run(data);
		return System.currentTimeMillis() - time;
	}
	
	private static void comment(String comment) {
		if (commentsAllowed) System.out.println(">> " + comment);
	}

	public static void test(int size, int algorithmNumber)
	{
		int[] data = Util.generateRandomIntegers(size, false, size * 10);
		int[] javaSorted = Arrays.copyOf(data, data.length);
		IntSortingAlgorithms.counting(javaSorted);
		
		if (algorithmNumber == -1)
			for (int i = 0; i < algorithms.length; i++)
				runTest(Arrays.copyOf(data, data.length), javaSorted, i);		
		else
			runTest(data, javaSorted, algorithmNumber);
	}
	
	public static void runTest(int[] data, int[] javaSorted, int algorithmNumber)
	{
		int[] mySorted = Arrays.copyOf(data, data.length);
		long time = run(mySorted, algorithmNumber);
		String result = Arrays.equals(mySorted, javaSorted) ? "success" : "fail";
		
		System.out.print(algorithmsNames[algorithmNumber] + ": ");
		System.out.println(result + " / " + time);
	}
}
