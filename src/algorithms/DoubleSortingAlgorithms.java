package algorithms;
import java.util.Arrays;

/**
 * Provides sorting methods.
 * The algorithms includes Selection sort, Insertion sort, QuickSort,
 * MergeSort and HeapSort.
 * 
 * @author Mateus Pires Lustosa
 */
public class DoubleSortingAlgorithms {
	
	private static void swap(double[] items, int i, int j)
	{
		double value = items[i];
		items[i] = items[j];
		items[j] = value;
	}

	public static void selection(double[] items)
	{
		for (int i = 0; i < items.length; i++)
		{
			int smaller = i;
			
			for (int j = i + 1; j < items.length; j++)
				if (items[j] < items[smaller])
					smaller = j;
			
			if (i != smaller) swap(items, i, smaller);
		}
	}

	public static void insertion(double[] items)
	{
		for (int i = 1; i < items.length; i++)
		{
			double value = items[i];
			int j = i;
			
			while (j > 0 && value < items[j - 1])
			{
				items[j] = items[j - 1];
				j--;
			}
			
			if (i != j) items[j] = value;
		}
	}
	
	public static void merge(double[] list) {
		merge_sort(list, 0, list.length - 1);
	}
	
	private static void merge_sort(double[] list, int begin, int end)
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
	
	private static void merge_merge(double[] list, int begin, int end, int half)
	{
		double[] left = Arrays.copyOfRange(list, begin, half + 1);
		int leftIndex = 0, rightIndex = half + 1;
		
		for (int i = begin; i <= end; i++)
		{
			if (leftIndex < left.length && rightIndex <= end)
			{
				if (left[leftIndex] <= list[rightIndex])
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
	
	public static void heap(double[] list)
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
	
	private static void heap_lower(double[] list, int index, int limit)
	{
		int child = 2 * index + 1;
		
        while (child < limit)
        {
            if (child + 1 < limit && list[child] < list[child + 1])
                child++;
            
            if (list[child] > list[index])
            {
                swap(list, child, index);
                index = child;
                child = 2 * child + 1;
            }
            else break;
        }
	}
	
	/**
	 * Sort an array using the Quicksort algorithm.
	 * @param items the array to sort.
	 */
	public static void quick(double[] items) {
        quick_sort(items, 0, items.length - 1);
    }
    
	/**
	 * Sort an array using the Quicksort algorithm.
	 * @param items the array to sort.
	 * @param begin the array's first element.
	 * @param end the array's last element. 
	 */
    private static void quick_sort(double[] items, int begin, int end)
    {
        if (begin < end)
        {
            int pivot = quick_partition(items, begin, end);
            
            quick_sort(items, begin, pivot - 1);
            quick_sort(items, pivot + 1, end);
        }
    }
 
    /**
     * Divide a subarray in two groups, one smaller than the pivot,
     * and one larger. The pivot is the last element.
     * @param items the array being sorted.
     * @param begin the first element index of the subarray.
     * @param end the last element index of the subarray.
     * @return the pivot index.
     */
    private static int quick_partition(double[] items, int begin, int end)
    {
    	// Takes the last element as the pivot
    	double pivot = items[end];
        int marker = begin;
        
        // Puts all elements smaller than the pivot, before the marker
        for (int i = begin; i < end; i++) {
            if (items[i] <= pivot)
            {
                swap(items, marker, i);
                marker++;
            }
        }

        // The marker is the first element larger than the pivot
        // Makes the pivot be between the division 
        swap(items, marker, end);
        
        // After the movement, the marker points to the pivot
        return marker;
    }
    
    /**
     * @author João Lucas
     */
	public static void shell(double list[])
	{
		int h = 1;
		
		while (h < list.length / 3)
			h = h * 3 + 1;
		
		while (h > 0) {
			for(int i = h; i < list.length; i++)
			{
				double aux = list[i];
				
				int j;
				for (j = i; j >= h && list[j - h] > aux; j-= h)
					list[j] = list[j - h];
				
				list[j] = aux;
			}
			
			h /= 3;
		}
	}
}
