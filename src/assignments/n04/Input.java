package assignments.n04;

public class Input
{
	public final Object data;
	public final boolean ordered;
	public final int range;
	
	public Input(Object data, boolean ordered, int range)
	{
		this.data = data;
		this.ordered = ordered;
		this.range = range;
	}
	
	public Input(Object data, boolean ordered) {
		this(data, ordered, 0);
	}
}
