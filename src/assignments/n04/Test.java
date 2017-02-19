package assignments.n04;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import algorithms.Util;

public class Test {

	private static final int[] params = new int[] {  
			10, 100, 1000, 5000, 10000, 50000, 100000, 400000, 700000, 1000000, 4000000, 7000000, 10000000
	};
	
	public static void main(String[] args)
	{
		Heuristic.resultOnly = true;
		
		if (args.length > 0 && args[0].equalsIgnoreCase("--choice"))
			Heuristic.choiceOnly = true;
		
		for (int length : params)
		{
			for (int range : params)
			{
				if (Heuristic.choiceOnly)
				{
					char result = Heuristic.chooseForInteger(length, range).charAt(0);
					System.out.print(range != params[0] ? "," + result : result);
				}
				else
				{
					int[] data = Util.generateRandomIntegers(length, false, range);
					
					StringBuilder inputString = new StringBuilder();
					inputString.append(length + "\n");
					
					for (int element : data)
						inputString.append(element + "\n");
					
					byte[] inputBytes = inputString.toString().getBytes(StandardCharsets.UTF_8);
					InputStream input = new ByteArrayInputStream(inputBytes);
					
					long time = Heuristic.heuristic(input, false).time;
					System.out.print(range != params[0] ? "," + time : time);
				}
			}
			
			System.out.println();
		}
	}
		
}
