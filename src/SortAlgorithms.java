/**
 * Implementação dos algoritmos: Selection Sort, Insertion Sort, ShellSort, QuickSort.
 * 
 * @author Mateus Pires Lustosa
 */

public class SortAlgorithms {
	
	private static final boolean EVERY_ATTR = true;
	
	private static <T> void swap(T[] items, int i, int j)
	{
		T value = items[i];
		items[i] = items[j];
		items[j] = value;
	}

	public static <T extends Comparable<T>> long selection(T[] items)
	{
		long swapCount = 0;
		
		for (int i = 0; i < items.length; i++)
		{
			int smaller = i;
			
			for (int j = i + 1; j < items.length; j++)
			{
				if (items[j].compareTo(items[smaller]) < 0)
				{
					smaller = j;
				}
			}
			
			if (i != smaller)
			{
				swap(items, i, smaller);
				swapCount++;
			}
		}
		
		return swapCount;
	}

	public static <T extends Comparable<T>> long insertion(T[] items)
	{
		long swapCount = 0;
		
		for (int i = 1; i < items.length; i++)
		{
			T value = items[i];
			int j = i;
			
			while (j > 0 && value.compareTo(items[j - 1]) < 0)
			{
				items[j] = items[j - 1];
				j--;
				if (EVERY_ATTR) swapCount++;
			}
			
			if (i != j)
			{
				items[j] = value;
				if (!EVERY_ATTR) swapCount++;
			}
		}
		
		return swapCount;
	}

	private static int getJump(int length)
	{
		int jump = 1;
		int next = 1;
		
		while (next < length)
		{
			jump = next;
			next = jump * 3 + 1;
		}
		
		return jump;
	}

	private static int previousJump(int jump) { return (jump - 1) / 3; }
	
	public static <T extends Comparable<T>> long shell(T[] items)
	{
		long swapCount = 0;
		int jump = getJump(items.length);
		
		while (jump > 0)
		{
			for (int i = jump; i < items.length; i++)
			{
				T value = items[i];
				int j = i;
				
				while (j >= jump && value.compareTo(items[j - jump]) < 0)
				{
					items[j] = items[j - jump];
					j -= jump;
					if (EVERY_ATTR) swapCount++;
				}
				
				if (i != j)
				{
					items[j] = value;
					if (!EVERY_ATTR) swapCount++;					
				}
			}
			
			jump = previousJump(jump);
		}
		
		return swapCount;
	}
	
	public interface PivotFinder {
		public <T extends Comparable<T>> int find(T[] items, int left, int right);
	}
	
	public <T extends Comparable<T>> int sort(T[] items, int left, int right, PivotFinder pivot)
	{
		int pivIndex = pivot.find(items, left, right);
		int compareCount = right - left;
		
		//System.out.println("piv[" + pivIndex + "]: " + items[pivIndex]);
		//System.out.println("< >: " + left + ", " + right);
		
		for (int i = left; i <= right; i++)
		{
			//System.out.println("i[" + i + "]: " + items[i]);
			
			int j = i;
			int diff = items[j].compareTo(items[pivIndex]); 
			
			//System.out.println("\t\tj: " + j + ", pi: " + pivIndex);
			if (diff < 0) {
				if (j > pivIndex)
				{
					T value = items[j];
					
					do
					{
						items[j] = items[j - 1];
					}
					while (--j > pivIndex);
					
					items[j] = value;
					pivIndex++;
				}
			}
			else if (diff > 0) {
				if (j < pivIndex)
				{
					T value = items[j];
					
					do
					{
						items[j] = items[j + 1];
					}
					while (++j < pivIndex);
					
					items[j] = value;
					pivIndex--;
					i--;
				}
			}
			
			/*for (T it : items) {
				System.out.print(it + ", ");
			} System.out.println();*/
		}
		
		if (pivIndex - 1 > left)
			compareCount += sort(items, left, pivIndex - 1, pivot);

		if (pivIndex + 1 < right)
			compareCount += sort(items, pivIndex + 1, right, pivot);
		
		return compareCount;
	}
	
}
