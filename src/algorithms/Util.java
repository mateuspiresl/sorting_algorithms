package algorithms;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Random;

import algorithms.analysis.Analysis;

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
	
	public static void printAnalysis(Analysis[] analyses)
	{
		for (int i = 0; i < analyses.length; i++)
			System.out.println(
					algorithmsNames[i] + ": " +
					new DecimalFormat("#.##").format(analyses[i].getTime()).replace(",", ".")
				);
	}
	
	public static void printArray(int[] array)
	{
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
	
	public static void printArrayInline(int[] array)
	{
		System.out.print(array[0]);
		
		for (int i = 1; i < array.length; i++)
			System.out.print(", " + array[i]);
		
		System.out.println();
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
	
	public static int[] generateRandomIntegers(int size) {
		return generateRandomIntegers(size, true);
	}
	
	public static int[] generateRandomIntegers(int size, boolean signed) {
		return generateRandomIntegers(size, signed, Integer.MAX_VALUE);
	}
	
	public static int[] generateRandomIntegers(int size, boolean signed, int mod)
	{
		Random rnd = new Random();
		int[] data = new int[size];
		
		while (--size >= 0)
		{			
			data[size] = rnd.nextInt() % mod;
			if (!signed && data[size] < 0) data[size] = -data[size];
		}
		
		return data;
	}
	
	public static InputStream generateRandomIntegersInput(int size, boolean signed, int mod)
	{
		StringBuilder inputString = new StringBuilder();
		inputString.append(size + "\n");
		
		Random rnd = new Random();
		
		while (--size >= 0)
		{			
			int value = rnd.nextInt() % mod;
			if (!signed && value < 0) value = -value;
			
			inputString.append(value + "\n");
		}
		
		byte[] inputBytes = inputString.toString().getBytes(StandardCharsets.UTF_8);
		return new ByteArrayInputStream(inputBytes);
	}
	
}
