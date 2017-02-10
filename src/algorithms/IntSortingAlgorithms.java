package algorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides sorting methods.
 * The algorithms includes Selection sort, Insertion sort, QuickSort,
 * MergeSort and HeapSort.
 * 
 * @author Mateus Pires Lustosa
 */
public class IntSortingAlgorithms {
	
	private static void swap(int[] items, int i, int j)
	{
		int value = items[i];
		items[i] = items[j];
		items[j] = value;
	}

	public static void selection(int[] items)
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

	public static void insertion(int[] items)
	{
		for (int i = 1; i < items.length; i++)
		{
			int value = items[i];
			int j = i;
			
			while (j > 0 && value < items[j - 1])
			{
				items[j] = items[j - 1];
				j--;
			}
			
			if (i != j) items[j] = value;
		}
	}
	
	public static void merge(int[] list) {
		merge_sort(list, 0, list.length - 1);
	}
	
	private static void merge_sort(int[] list, int begin, int end)
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
	
	private static void merge_merge(int[] list, int begin, int end, int half)
	{
		int[] left = Arrays.copyOfRange(list, begin, half + 1);
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
	
	public static void heap(int[] list)
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
	
	private static void heap_lower(int[] list, int index, int limit)
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
	public static void quick(int[] items) {
        quick_sort(items, 0, items.length - 1);
    }
    
	/**
	 * Sort an array using the Quicksort algorithm.
	 * @param items the array to sort.
	 * @param begin the array's first element.
	 * @param end the array's last element. 
	 */
    private static void quick_sort(int[] items, int begin, int end)
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
    private static int quick_partition(int[] items, int begin, int end)
    {
    	// Takes the last element as the pivot
    	int pivot = items[end];
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
     * Sort an array of int using Radix sort algorithm.
     * @param list the array to sort.
     */
    public static void counting(int[] list)
    {
    	int min = list[0];
    	int max = list[0];
    	
    	for (int i = 1; i < list.length; i++)
    	{
    		if (list[i] < min) min = list[i];
    		else if (list[i] > max) max = list[i];
    	}
    	
    	counting(Arrays.copyOf(list, list.length), list, min, max); 
    }
    
    /**
     * Sort an array of int using Radix sort algorithm.
     * @param list the array to sort.
     * @param sorted the result array. Must be of the same size or larger.
     * @param max the maximum value found in the elements of the array.
     */
    public static void counting(int[] list, int[] sorted, int max) {
    	counting(list, sorted, 0, max);
    }
    
    /**
     * Sort an array of int using Radix sort algorithm.
     * @param list the array to sort.
     * @param sorted the result array. Must be of the same size or larger.
     * @param min the minimum value found in the array.
     * @param max the maximum value found in the array.
     */
    public static void counting(int[] list, int[] sorted, int min, int max)
    {
    	int[] order = new int[max - min + 1];
    	
    	for (int i = 0; i < list.length; i++)
    		order[list[i] - min]++;
    	
    	for (int i = 1; i < order.length; i++)
    		order[i] += order[i - 1];
    	
    	for (int i = list.length - 1; i >= 0; i--)
    	{
    		int index = --order[list[i] - min];
    		sorted[index] = list[i];
    	}
    }
	
    /**
     * Sort an array of int using the Bucket sort algorithm.
     * @param list the array to sort.
     */
    public static void bucket(int[] list)
    {
    	int max = list[0];
    	
    	for (int i = 1; i < list.length; i++)
    		if (list[i] > max) max = list[i];
    	
    	bucket(list, max);
    }
    
    /**
     * 
     * Sort an array of int using the Bucket sort algorithm.
     * @param list the array to sort.
     * @param max the maximum value found in the array.
     */
    public static void bucket(int[] list, int max)
    {
    	int numBuckets = Math.min(max / 5, list.length / 10);
    	int bestCapacity = list.length / numBuckets;
    	
    	@SuppressWarnings("unchecked")
		List<Integer>[] buckets = new ArrayList[numBuckets];
    	
    	for (int i = 0; i < buckets.length; i++)
    		buckets[i] = new ArrayList<Integer>(bestCapacity);
    	
    	for (int i = 0; i < list.length; i++)
    	{
    		float perc = list[i] / (float) (max + 1);
    		buckets[perc >= 1f ? numBuckets - 1 : (int) (perc * numBuckets)].add(list[i]);
    	}
    	
    	for (int i = 0; i < buckets.length; i++)
    		SortingAlgorithms.quick(buckets[i]);
    	
    	int i = 0;
    	
		for (List<Integer> bucket : buckets)
			for (int value : bucket)
				list[i++] = value;
    }
    
    /**
     * Sort an array of int using the Radix sort algorithm.
     * @param list the array to sort.
     */
    public static void radix(int[] list)
    {
    	int[] unordered = list;
    	int[] ordered = new int[list.length];
    	
    	for (int digit = 0;; digit++)
    	{
    		int[] numbers = new int[10];
    		int power = (int) Math.pow(10, digit);
    		
    		for (int i = 0; i < unordered.length; i++)
    		{
    			int index = unordered[i] % (power * 10) / power;
    			numbers[index]++;
    		}
    		
    		for (int i = 1; i < 10; i++)
    			numbers[i] += numbers[i - 1];
    		
    		if (numbers[0] == numbers[9])
    			break;
    		
    		for (int i = unordered.length - 1; i >= 0; i--)
    		{
    			int index = unordered[i] % (power * 10) / power;
    			ordered[--numbers[index]] = unordered[i];
    		}
    		
    		int[] temp = ordered;
    		ordered = unordered;
    		unordered = temp;
    	}
    	
    	if (unordered != list)
    		for (int i = 0; i < list.length; i++)
    			list[i] = unordered[i];
    }
}
