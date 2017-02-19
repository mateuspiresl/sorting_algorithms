package assignments.n04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StringTest {

	private static final int[] sizes = new int[] {  
			10, 100, 1000, 5000, 10000, 50000, 100000
	};
	
	private static final int[] lengths = new int[] {  
			10, 100, 400, 700, 1000
	};
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Heuristic.resultOnly = true;
		boolean choiceOnly = false;
		
		if (args.length > 0 && args[0].equalsIgnoreCase("--choice"))
			choiceOnly = true;
		
		for (int size : sizes) {
			for (int length : lengths)
			{
				if (choiceOnly)
				{
					char result = Heuristic.chooseForString(size).name.charAt(0);
					System.out.print(length != lengths[0] ? "," + result : result);
				}
				else
				{
					File file = new File("assignments/gen/size" + size + "-len" + length + ".in");
					FileInputStream fileStream = new FileInputStream(file);
					
					long time = Heuristic.heuristic(fileStream, false).time;
					System.out.print(length != lengths[0] ? "," + time : time);
				}
			}
			
			System.out.println();
		}
	}
		
}
