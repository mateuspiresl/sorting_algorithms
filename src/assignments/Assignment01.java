import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * APA - Assignment 01 
 * 
 * @author Mateus Pires Lustosa - mateusplpl@gmail.com
 */
public class Assignment01 {

	private static final String ERROR_MESSAGE = "Use:\n\tn - algorithm number"; 
	
	public static void main(String[] args)
	{
		try {
			if (args.length == 1)
			{
				int algorithmNumber = Integer.parseInt(args[0]) - 1;
				Scanner scanner = new Scanner(System.in);

				// The first integer is the number of entries
				int n = scanner.nextInt();
				Integer[] data = new Integer[n];
				
				// Reads n entries
				while (n-- > 0) data[n] = scanner.nextInt();
				
				scanner.close();
				
				Util.algorithms[algorithmNumber].run(data);
				Util.printArray(data);
			}
			else System.out.println(ERROR_MESSAGE);
		}
		catch (InputMismatchException ime) {
			ime.printStackTrace();
			System.out.println(ERROR_MESSAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fatal error!");
		}
	}
	
}
