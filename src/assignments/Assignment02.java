package assignments;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import algorithms.IntSortingAlgorithmRunner;
import algorithms.IntSortingAlgorithms;
import algorithms.Util;
import algorithms.analysis.AlgorithmResultException;
import algorithms.analysis.Analysis;

/**
 * APA - Assignment 02
 * 
 * @author Mateus Pires Lustosa - mateusplpl@gmail.com
 */
public class Assignment02 {
	
	private static final Pattern ARG_IGNORED = Pattern.compile("^--ignored=.*", Pattern.CASE_INSENSITIVE); 
	
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
	
	// Indicates if the output can have comments
	private static boolean commented = false;
	
	/**
	 * Run algorithms and outputs its running times in .csv format.
	 * 
	 * Optional arguments:
	 * --ignore=[algorithms]
	 * 		Ignore algorithms.
	 * 		The [algorithms] parameter should be the numbers of the
	 * 		algorithms to ignore, without spaces. To ignore the
	 * 		algorithms 1 and 2, for example, write '--ignore=12'.
	 * --ignored=[replacement]
	 * 		If an algorithm is ignored, its space in the output can
	 * 		be replaced by [replacement]. To ignore its space, just
	 * 		ignore this argument, ot to left it empty, let the
	 * 		[replacement] empty, as '--ignored='. To replace for '0',
	 * 		write '--ignored=0'.
	 * --average
	 * 		The time of each algorithm will be the average of the runs.
	 * --comment
	 * 		... TODO
	 * 
	 * Required arguments:
	 * 		The required argument can --file OR --gen, followed by its
	 * 		own arguments.
	 * --file
	 * 		The input will be files, especified by its following arguments.
	 * 		To pass the files 'file_1' and 'file_2' as input, for example,
	 * 		write '--file file_1 file_2'.
	 * --gen
	 * 		The input will be random generated data.
	 * 		The following arguments will be taken as the sizes or each run.
	 * 		For example, to run the algorithms on two arrays of sizes, 100
	 * 		and 500, write '--gen 100 500'. 
	 * 
	 * @param args the arguments.
	 */
	public static void main(String[] args)
	{
		int index = 0;
		
		// Selected algorithms
		int selected = ~0;
		IntSortingAlgorithmRunner[] algorithms = intAlgorithms;
		
		// The replacement for the ignored algorithm
		// Null value means space ignored 
		String ignored = null;
		
		// Indicates if the output should be the average
		boolean average = false;
		
		while (args[index].startsWith("--") && !args[index].equals("--file")
				&& !args[index].equals("--gen"))
		{
			if (args[index].startsWith("--ignore="))
			{
				List<IntSortingAlgorithmRunner> algorithmsList = new ArrayList<IntSortingAlgorithmRunner>();
				String algorithmsNumbers = args[index].substring(9);
				
				for (char number : algorithmsNumbers.toCharArray())
					selected &= ~(1 << (number - '0'));
				
				for (int i = 0; i < algorithms.length; i++) {
					if ((selected & (1 << i)) != 0)
					{
						comment("Include " + Util.algorithmsNames[i]);
						algorithmsList.add(algorithms[i]);
					}
					else comment("Ignore " + Util.algorithmsNames[i]);
				}

				algorithms = algorithmsList.toArray(new IntSortingAlgorithmRunner[0]);
			}
			
			else if (ARG_IGNORED.matcher(args[index]).matches())
			{
				ignored = args[index].substring(10);
				comment("Ignored replacement: " + ignored);
			}
			
			else if (args[index].equalsIgnoreCase("--average"))
			{
				average = true;
				comment("Output average");
			}
			
			else if (args[index].equalsIgnoreCase("--commented"))
			{
				commented = true;
				comment("Output commented");
			}
			
			else
			{
				System.out.println("Unknown argument " + args[index]);
				return;
			}
			
			index++;
		}
		
		try {
			List<Analysis[]> analysis = new ArrayList<Analysis[]>();
			
			if (args[index].equalsIgnoreCase("--gen"))
			{
				if (index + 1 >= args.length)
				{
					System.out.println("You need to provide at least one size.");
					return;
				}
				
				for (index++; index < args.length; index++)
				{
					int size = Integer.parseInt(args[index]);
					int[] data = Util.generateRandomIntegers(size);
					
					Analysis[] an = analyze(algorithms, data, false);
					comment("", false);
					if (commented) printAsCSVLine(an, selected, ignored);
					
					analysis.add(an);
				}
			}
			else if (args[index].equalsIgnoreCase("--file"))
			{
				if (index + 1 >= args.length)
				{
					System.out.println("You need to provide at least one file.");
					return;
				}
				
				for (index++; index < args.length; index++)
				{
					comment("Reading file " + args[index]);
					
					File file = new File(args[index]);
					Scanner scanner = new Scanner(file);
					
					int size = scanner.nextInt();
					int[] data = new int[size];
					
					while (--size >= 0)
						data[size] = scanner.nextInt();
					
					scanner.close();
					
					Analysis[] an = analyze(algorithms, data, false);
					comment("", false);
					if (commented) printAsCSVLine(an, selected, ignored);
					
					analysis.add(an);
				}
			}
			else
			{
				System.out.println("Must especify an input type, --gen or --file,"
						+ "\n\tfollowed by the arguments");
				System.out.println("--gen: For each size (argument), generates integer"
						+ "\n\tdata of the given size and runs on every algorithm.");
				System.out.println("--file: For each file (argument), reads the file"
						+ "\n\tof integers as the data and runs on every algorithm.");
				
				return;
			}
			
			if (average)
			{
				Analysis[] averageAnalysis = new Analysis[algorithms.length];
				
				for (int alg = 0; alg < algorithms.length; alg++)
				{
					long time = 0;
					
					for (int run = 0; run < analysis.size(); run++)
						time += analysis.get(run)[alg].time;
					
					averageAnalysis[alg] = new Analysis(time, analysis.size());
				}
				
				printAsCSVLine(averageAnalysis, selected, ignored);
			}
			else
			{
				for (Analysis[] ans : analysis)
					printAsCSVLine(ans, selected, ignored);
			}
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("Could not open file " + args[index]);
		}
		catch (AlgorithmResultException are) {
			System.out.println("Some algorithm contains error.");
		}
	}
	
	private static Analysis[] analyze(IntSortingAlgorithmRunner[] algorithms, int[] items, boolean check)
	{
		Analysis[] analyses = new Analysis[algorithms.length];
		
		int[] sortedData = null;
		if (check)
		{
			sortedData = Arrays.copyOf(items, items.length);
			Arrays.sort(sortedData);
		}
		
		for (int alg = 0; alg < algorithms.length; alg++)
		{
			int[] data = Arrays.copyOf(items, items.length);
			long time = System.currentTimeMillis();
			
			algorithms[alg].run(data);
			
			time = System.currentTimeMillis() - time;
			analyses[alg] = new Analysis(time, 1);
			
			if (check && !Arrays.equals(data, sortedData))
				throw new AlgorithmResultException();
		}
		
		return analyses;
	}
	
	public static void comment(String text, boolean breakLine) {
		if (commented) System.out.print(">> " + text + (breakLine ? "\n" : ""));
	}
	
	public static void comment(String text) {
		comment(text, true);
	}
	
	private static void printAsCSVLine(Analysis[] analysis, int selected, String ignored)
	{
		int analysisIndex = 0;
		for (int i = 0; i < intAlgorithms.length; i++)
		{
			if ((selected & (1 << i)) != 0)
			{
				if (i != 0) System.out.print(",");
				System.out.print((long) analysis[analysisIndex++].getTime());
			}
			else if (ignored != null)
			{
				if (i != 0) System.out.print(",");
				System.out.print(ignored);
			}
		}
		
		System.out.print("\n");
		System.out.flush();
	}
	
}
