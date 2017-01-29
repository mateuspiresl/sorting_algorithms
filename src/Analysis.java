/**
 * Class to hold and process analysis data.
 * 
 * @author Mateus Pires Lustosa - mateusplpl@gmail.com
 */
public class Analysis {

	public final long time;
	public final int runs;
	
	/**
	 * Constructor.
	 * @param time the time elapsed of runs.  
	 * @param runs the number of runs.
	 */
	public Analysis(long time, int runs)
	{
		this.time = time;
		this.runs = runs;
	}
	
	/**
	 * Returns the average time.
	 * @return the average time.
	 */
	public double getTime() { return time / (double) runs; }

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "{ time: " + time + ", runs: " + runs + " }";
	}
	
}
