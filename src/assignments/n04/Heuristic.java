package assignments.n04;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import algorithms.Algorithms;
import algorithms.DoubleSortingAlgorithms;
import algorithms.IntSortingAlgorithms;
import algorithms.SortingAlgorithms;
import algorithms.analysis.AlgorithmResultException;

public class Heuristic
{
	public static boolean all = false;
	public static boolean check = false;
	public static boolean resultOnly = false;
	
	public static void main(String[] args)
	{
		boolean choiceOnly = false;
		
		int index = 0;
		while (index < args.length)
		{
			if (args[index].equalsIgnoreCase("--all"))
				all = true;
			
			else if (args[index].equalsIgnoreCase("--check"))
				check = true;
			
			else if (args[index].equalsIgnoreCase("--result"))
				resultOnly = true;
			
			else if (args[index].equalsIgnoreCase("--choice"))
				choiceOnly = true;
			
			else break;
			
			index++;
		}
		
		Result result = heuristic(System.in, choiceOnly);
		
		if (resultOnly) System.out.println(result.time);
		else
		{
			System.out.println("Chosen algorithm: " + result.algorithm);
			if (!choiceOnly) System.out.println("Time: " + result.time + " ms");
		}
	}
	
	public static Result heuristic(InputStream stream, boolean choiceOnly) throws AlgorithmResultException
	{
		Scanner scanner = new Scanner(stream);
		int size = scanner.nextInt();
		
		if (scanner.hasNextInt())
		{
			if (!resultOnly) System.out.println("Found an integer");
			if (!resultOnly) System.out.println("Size: " + size);
			
			Input input = processInteger(size, scanner);
			if (input.ordered) return new Result(null, 0);
			if (!resultOnly) System.out.println("Range: " + input.range);
			
			Algorithms algorithm = chooseForInteger(size, input.range);
			if (choiceOnly) return new Result(algorithm, -1);
			
			int[] data = (int[]) input.data;
			int[] sorted = null;
			
			if (check)
			{
				sorted = Arrays.copyOf(data, data.length);
				Arrays.sort(sorted);
			}
			
			long time = run(algorithm, data, sorted);
			return new Result(algorithm, time);
		}
		else if (scanner.hasNextDouble())
		{
			if (!resultOnly) System.out.println("Found a double");
			if (!resultOnly) System.out.println("Size: " + size);
			
			Input input = processDouble(size, scanner);
			if (input.ordered) return new Result(null, 0);
			
			Algorithms algorithm = chooseForDouble(size);
			if (choiceOnly) return new Result(algorithm, -1);
			
			double[] data = (double[]) input.data;
			double[] sorted = null;
			
			if (check)
			{
				sorted = Arrays.copyOf(data, data.length);
				Arrays.sort(sorted);
			}
			
			long time = run(algorithm, data, sorted);
			return new Result(algorithm, time);
		}
		else
		{
			if (!resultOnly) System.out.println("Found a string");
			if (!resultOnly) System.out.println("Size: " + size);
			
			Input input = processString(size, scanner);
			if (input.ordered) return new Result(null, 0);
			
			Algorithms algorithm = chooseForString(size);
			if (choiceOnly) return new Result(algorithm, -1);
			
			String[] data = (String[]) input.data;
			String[] sorted = null;
			
			if (check)
			{
				sorted = Arrays.copyOf(data, data.length);
				Arrays.sort(sorted);
			}
			
			long time = run(algorithm, data, sorted);
			return new Result(algorithm, time);
		}
	}

	private static Input processInteger(int size, Scanner scanner)
	{
		int[] vector = new int[size];
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		boolean ordered = true;
		
		try { scanner.skip("\\s"); } catch (Exception e) { }
		
		for(int i = 0; i < size; i++)
		{
			vector[i] = scanner.nextInt();
			
			if (vector[i] > max)
				max = vector[i];
			
			else if (vector[i] < max && ordered)
				ordered = false;
			
			if (vector[i] < min)
				min = vector[i];
		}
		
		int range = max - min;
		return new Input(vector, ordered, range);
	}
	
	public static Algorithms chooseForInteger(int size, int range)
	{
		if (range <= size * 25)
			return Algorithms.CountingSort;
		else
			return Algorithms.QuickSort;
	}
	
	public static long run(Algorithms algorithm, int[] vector, int[] sorted) throws AlgorithmResultException
	{
		long time = System.currentTimeMillis();
		IntSortingAlgorithms.sort(algorithm, vector);
		time = System.currentTimeMillis() - time;
		
		if (sorted != null && !Arrays.equals(sorted, vector))
			throw new AlgorithmResultException();
		
		return time;
	}
	
	private static Input processDouble(int size, Scanner scanner)
	{
		double[] vector = new double[size];
		boolean ordered = true;
		
		try { scanner.skip("\\s"); } catch (Exception e) { }
		vector[0] = scanner.nextDouble();
		
		for(int i = 1; i < size; i++)
		{
			vector[i] = scanner.nextDouble();
			if (vector[i] < vector[i - 1]) ordered = false;
		}
		
		return new Input(vector, ordered);
	}
	
	public static Algorithms chooseForDouble(int size) {
		return Algorithms.QuickSort;
	}
	
	public static long run(Algorithms algorithm, double[] vector, double[] sorted)
	{
		long time = System.currentTimeMillis();
		DoubleSortingAlgorithms.sort(algorithm, vector);
		time = System.currentTimeMillis() - time;
		
		if (sorted != null && !Arrays.equals(sorted, vector))
			throw new AlgorithmResultException();
		
		return time;
	}
	
	private static Input processString(int size, Scanner scanner)
	{
		String[] vector = new String[size];
		boolean ordered = true;
		
		try { scanner.skip("\\s+"); } catch (Exception e) { }
		vector[0] = scanner.nextLine();
		
		for(int i = 1; i < size; i++)
		{
			vector[i] = scanner.nextLine();
			if (vector[i].compareTo(vector[i - 1]) < 0) ordered = false;
		}
		
		return new Input(vector, ordered);
	}
	
	public static Algorithms chooseForString(int size) {
		return Algorithms.QuickSort;
	}
	
	public static long run(Algorithms algorithm, String[] vector, String[] sorted) throws AlgorithmResultException
	{
		long time = System.currentTimeMillis();
		SortingAlgorithms.sort(algorithm, vector);
		time = System.currentTimeMillis() - time;
		
		if (sorted != null && !Arrays.equals(sorted, vector))
			throw new AlgorithmResultException();
		
		return time;
	}
}
