public class Test {
	
	/**
	 * Makes analysis of random data on every algorithm.
	 * The size of the data must be provided in the command
	 * line arguments, as the first argument. 
	 * @param args the size of the data to generate.
	 */
	public static void main(String[] args)
	{
		try {
			if (args.length == 1)
			{
				int size = Integer.parseInt(args[0]);
				Example[] data = Example.generateData(size);
				Analysis[] analyses = new Analyzer(Util.algorithms).analyze(data, true);
				
				Util.printAnalysis(analyses);
				System.out.println("Success.");
			}
			else {
				System.out.println("Invalid arguments");
			}
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
