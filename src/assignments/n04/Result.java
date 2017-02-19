package assignments.n04;

import algorithms.Algorithms;

public class Result
{
	public final Algorithms algorithm;
	public final long time;
	
	public Result(Algorithms algorithm, long time)
	{
		this.algorithm = algorithm;
		this.time = time;
	}
}
