package assignments.n04;

import algorithms.Algorithms;
import algorithms.Util;

public class ConcurrentTest
{
	private static final int[] params = new int[] {  
			10, 100, 1000, 5000, 10000, 50000, 100000, 400000, 700000, 1000000, 4000000, 7000000, 10000000
	};
	
	private static boolean choiceOnly = false;
	private static int runs = 1;
	private static int finished = 0;
	private static long[][] result;
	
	public static void main(String[] args)
	{
		Heuristic.resultOnly = true;
		
		int index = 0;
		while (index < args.length)
		{
			if (args[index].equalsIgnoreCase("--choice"))
				choiceOnly = true;
			
			else if (args[index].startsWith("--runs="))
				runs = Integer.valueOf(args[index].substring(7));
			
			index++;
		}
		
		result = new long[params.length][];
		for (int i = 0; i < params.length; i++)
			result[i] = new long[params.length];
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ConcurrentTest.run('.', 0, 10);
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ConcurrentTest.run('-', 11, 11);
			}
		}).start();
		
		run('|', 12, 12);
	}
	
	private static void run(char id, int begin, int end)
	{
		for (int sizeIndex = begin; sizeIndex <= end; sizeIndex++)
		{
			int size = params[sizeIndex];
			
			for (int rangeIndex = 0; rangeIndex < params.length; rangeIndex++)
			{
				int range = params[rangeIndex];
				
				if (choiceOnly)
				{
					char algorithm = Heuristic.chooseForInteger(size, range).name.charAt(0);
					System.out.print(range != params[0] ? "," + algorithm : algorithm);
				}
				else
				{
					long time = 0;
					
					for (int i = 0; i < runs; i++)
					{
						int[] data = Util.generateRandomIntegers(size, true, range);
						Algorithms algorithm = Heuristic.chooseForInteger(size, range);
						time += Heuristic.run(algorithm, data, null);
					}
					
					result[sizeIndex][rangeIndex] = time / runs;
					System.out.print(id);
				}
			}
		}
		
		System.out.print(id + "x");
		
		if (!choiceOnly) synchronized (result)
		{
			finished++;
			
			if (finished == 3)
			{
				System.out.println();
				
				for (int size = 0; size < params.length; size++) {
					for (int range = 0; range < params.length; range++)
					{
						if (range != 0) System.out.print(",");
						System.out.print(result[size][range]);
					}
					
					System.out.println();
				}
			}
		}
	}
}
