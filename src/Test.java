import java.util.Scanner;

public class Test {
	
	/**
	 * Makes analysis of random data on every algorithm.
	 * The size of the data must be provided in the command
	 * line arguments, as the first argument, or in the
	 * standard input.
	 * 
	 * @param args the size of the data to generate.
	 */
	public static void main(String[] args)
	{
		try {
			int size;
			
			// If no argument is provided, get the size from the
			// standard input
			if (args.length == 0)
			{
				Scanner scanner = new Scanner(System.in);
				size = scanner.nextInt();
				scanner.close();
			}
			else if (args.length == 1)
			{
				size = Integer.parseInt(args[0]);
			}
			else
			{
				System.out.println("Invalid arguments");
				return;
			}
			
			Example[] data = Example.generateData(size);
			Analysis[] analyses = new Analyzer(Util.algorithms).analyze(data);
			
			Util.printAnalysis(analyses);
		}
		catch (AlgorithmResultException are) {
			are.printStackTrace();
			System.out.println("Some algorithm contains error.");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Faltal error!");
		}
	}
	
}
