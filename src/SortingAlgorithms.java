import java.util.Arrays;

/**
 * Provides sorting methods.
 * The algorithms includes Selection sort, Insertion sort, QuickSort,
 * MergeSort and HeapSort.
 * 
 * @author Mateus Pires Lustosa
 */
public class SortingAlgorithms {
	
	private static <T> void swap(T[] items, int i, int j)
	{
		T value = items[i];
		items[i] = items[j];
		items[j] = value;
	}

	public static <T extends Comparable<T>> void selection(T[] items)
	{
		for (int i = 0; i < items.length; i++)
		{
			int smaller = i;
			
			for (int j = i + 1; j < items.length; j++)
				if (items[j].compareTo(items[smaller]) < 0)
					smaller = j;
			
			if (i != smaller) swap(items, i, smaller);
		}
	}

	public static <T extends Comparable<T>> void insertion(T[] items)
	{
		for (int i = 1; i < items.length; i++)
		{
			T value = items[i];
			int j = i;
			
			while (j > 0 && value.compareTo(items[j - 1]) < 0)
			{
				items[j] = items[j - 1];
				j--;
			}
			
			if (i != j) items[j] = value;
		}
	}
	
	public interface PivotFinder<T extends Comparable<T>> {
		public int find(T[] items, int left, int right);
	}
	
	public static <T extends Comparable<T>> void quick(T[] items, PivotFinder<T> pivot)
	{
		quick_sort(items, 0, items.length - 1, pivot);
	}
	
	private static <T extends Comparable<T>> void quick_sort(T[] items, int left, int right, PivotFinder<T> pivot)
	{
		int pivIndex = pivot.find(items, left, right);
		
		for (int i = left; i <= right; i++)
		{
			int j = i;
			int diff = items[j].compareTo(items[pivIndex]); 
			
			if (diff < 0)
			{
				if (j > pivIndex)
				{
					T value = items[j];
					
					do items[j] = items[j - 1];
					while (--j > pivIndex);
					
					items[j] = value;
					pivIndex++;
				}
			}
			else if (diff > 0)
			{
				if (j < pivIndex)
				{
					T value = items[j];
					
					do items[j] = items[j + 1];
					while (++j < pivIndex);
					
					items[j] = value;
					pivIndex--;
					i--;
				}
			}
		}
		
		if (pivIndex - 1 > left)
			quick_sort(items, left, pivIndex - 1, pivot);

		if (pivIndex + 1 < right)
			quick_sort(items, pivIndex + 1, right, pivot);
	}
	
	public static <T extends Comparable<T>> void merge(T[] list)
	{
		merge_sort(list, 0, list.length - 1);
	}
	
	public static <T extends Comparable<T>> void merge_sort(T[] list, int begin, int end)
	{
		int half = (begin + end) / 2;
		
		if (end != half)
		{
			if (begin != half)
			{
				merge_sort(list, begin, half);
				merge_sort(list, half + 1, end);
			}
			
			merge_merge(list, begin, end, half);
		}
	}
	
	private static <T extends Comparable<T>> void merge_merge(T[] list, int begin, int end, int half)
	{
		T[] left = Arrays.copyOfRange(list, begin, half + 1);
		int leftIndex = 0, rightIndex = half + 1;
		
		for (int i = begin; i <= end; i++)
		{
			if (leftIndex < left.length && rightIndex <= end)
			{
				if (left[leftIndex].compareTo(list[rightIndex]) <= 0)
					list[i] = left[leftIndex++];
				else
					list[i] = list[rightIndex++];
			}
			
			else if (leftIndex < left.length)
				list[i] = left[leftIndex++];
			
			else
				list[i] = list[rightIndex++];
		}
	}
	
	public static <T extends Comparable<T>> void heap(T[] list)
	{
		for (int i = list.length / 2 - 1; i >= 0; i--)
			heap_lower(list, i, list.length);
		
        int limit = list.length;

        for (int i = list.length - 1; i > 0; i--)
        {
        	swap(list, i, 0);
        	heap_lower(list, 0, --limit);
        }
	}
	
	private static <T extends Comparable<T>> void heap_lower(T[] list, int index, int limit)
	{
		int child = 2 * index + 1;
		
        while (child < limit)
        {
            if (child + 1 < limit && list[child].compareTo(list[child + 1]) < 0)
                child++;
            
            if (list[child].compareTo(list[index]) > 0)
            {
                swap(list, child, index);
                index = child;
                child = 2 * child + 1;
            }
            else break;
        }
	}
	
}
