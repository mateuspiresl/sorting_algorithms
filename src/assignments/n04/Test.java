package assignments.n04;

import algorithms.Algorithms;
import algorithms.Util;

public class Test {

	private static final int[] params = new int[] {  
			10, 100, 1000, 5000, 10000, 50000, 100000, 400000, 700000, 1000000, 4000000, 7000000, 10000000
	};
	
	public static void main(String[] args)
	{
		Heuristic.resultOnly = true;
		boolean choiceOnly = false;
		int runs = 1; 
		
		int index = 0;
		while (index < args.length)
		{
			if (args[index].equalsIgnoreCase("--choice"))
				choiceOnly = true;
			
			else if (args[index].startsWith("--runs="))
				runs = Integer.valueOf(args[index].substring(7));
			
			index++;
		}
		
		for (int length : params)
		{
			for (int range : params)
			{
				if (choiceOnly)
				{
					char result = Heuristic.chooseForInteger(length, range).name.charAt(0);
					System.out.print(range != params[0] ? "," + result : result);
				}
				else
				{
					long time = 0;
					
					for (int i = 0; i < runs; i++)
					{
						int[] data = Util.generateRandomIntegers(length, true, range);
						Algorithms algorithm = Heuristic.chooseForInteger(length, range);
						time += Heuristic.run(algorithm, data, null);
					}
					
					time /= runs;
					System.out.print(range != params[0] ? "," + time : time);
				}
			}
			
			System.out.println();
		}
	}
		
}
