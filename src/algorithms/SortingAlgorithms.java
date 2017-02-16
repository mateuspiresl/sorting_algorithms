package algorithms;
import java.util.Arrays;
import java.util.List;

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
	
	private static <T> void swap(List<T> items, int i, int j)
	{
		T value = items.get(i);
		items.set(i, items.get(j));
		items.set(j,  value);
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
	
	public static <T extends Comparable<T>> void selection(List<T> items)
	{
		for (int i = 0; i < items.size(); i++)
		{
			int smaller = i;
			
			for (int j = i + 1; j < items.size(); j++)
				if (items.get(j).compareTo(items.get(smaller)) < 0)
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
	
	public static <T extends Comparable<T>> void insertion(List<T> items)
	{
		for (int i = 1; i < items.size(); i++)
		{
			T value = items.get(i);
			int j = i;
			
			while (j > 0 && value.compareTo(items.get(j - 1)) < 0)
			{
				items.set(j, items.get(j - 1));
				j--;
			}
			
			if (i != j) items.set(j, value);
		}
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

	public static <T extends Comparable<T>> void merge(List<T> list)
	{
		merge_sort(list, 0, list.size() - 1);
	}
	
	public static <T extends Comparable<T>> void merge_sort(List<T> list, int begin, int end)
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
	
	private static <T extends Comparable<T>> void merge_merge(List<T> list, int begin, int end, int half)
	{
		List<T> left = list.subList(begin, half + 1);
		int leftIndex = 0, rightIndex = half + 1;
		
		for (int i = begin; i <= end; i++)
		{
			if (leftIndex < left.size() && rightIndex <= end)
			{
				if (left.get(leftIndex).compareTo(list.get(rightIndex)) <= 0)
					list.set(i, left.get(leftIndex++));
				else
					list.set(i, list.get(rightIndex++));
			}
			
			else if (leftIndex < left.size())
				list.set(i, left.get(leftIndex++));
			
			else
				list.set(i, list.get(rightIndex++));
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

	public static <T extends Comparable<T>> void heap(List<T> list)
	{
		for (int i = list.size() / 2 - 1; i >= 0; i--)
			heap_lower(list, i, list.size());
		
        int limit = list.size();

        for (int i = list.size() - 1; i > 0; i--)
        {
        	swap(list, i, 0);
        	heap_lower(list, 0, --limit);
        }
	}
	
	private static <T extends Comparable<T>> void heap_lower(List<T> list, int index, int limit)
	{
		int child = 2 * index + 1;
		
        while (child < limit)
        {
            if (child + 1 < limit && list.get(child).compareTo(list.get(child + 1)) < 0)
                child++;
            
            if (list.get(child).compareTo(list.get(index)) > 0)
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
	public static <T extends Comparable<T>> void quick(T[] items) {
        quick_sort(items, 0, items.length - 1);
    }
    
	/**
	 * Sort an array using the Quicksort algorithm.
	 * @param items the array to sort.
	 * @param begin the array's first element.
	 * @param end the array's last element. 
	 */
    private static <T extends Comparable<T>> void quick_sort(T[] items, int begin, int end)
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
    private static <T extends Comparable<T>> int quick_partition(T[] items, int begin, int end)
    {
    	// Takes the last element as the pivot
        T pivot = items[end];
        int marker = begin;
        
        // Puts all elements smaller than the pivot, before the marker
        for (int i = begin; i < end; i++) {
            if (items[i].compareTo(pivot) <= 0)
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
	 * Sort an list using the Quicksort algorithm.
	 * @param items the list to sort.
	 */
	public static <T extends Comparable<T>> void quick(List<T> items) {
        quick_sort(items, 0, items.size() - 1);
    }
    
	/**
	 * Sort an list using the Quicksort algorithm.
	 * @param items the list to sort.
	 * @param begin the list's first element.
	 * @param end the list's last element. 
	 */
    private static <T extends Comparable<T>> void quick_sort(List<T> items, int begin, int end)
    {
        if (begin < end)
        {
            int pivot = quick_partition(items, begin, end);
            
            quick_sort(items, begin, pivot - 1);
            quick_sort(items, pivot + 1, end);
        }
    }
 
    /**
     * Divide a sublist in two groups, one smaller than the pivot,
     * and one larger. The pivot is the last element.
     * @param items the list being sorted.
     * @param begin the first element index of the sublist.
     * @param end the last element index of the sublist.
     * @return the pivot index.
     */
    private static <T extends Comparable<T>> int quick_partition(List<T> items, int begin, int end)
    {
    	// Takes the last element as the pivot
        T pivot = items.get(end);
        int marker = begin;
        
        // Puts all elements smaller than the pivot, before the marker
        for (int i = begin; i < end; i++) {
            if (items.get(i).compareTo(pivot) <= 0)
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
     * Sort an array of int using the Radix sort algorithm.
     * @param list the array to sort.
     */
    public static void radix(String[] list)
    {
    	String[] unordered = list;
    	String[] ordered = new String[list.length];
    	
    	int max = 0;
    	for (int i = 0; i < unordered.length; i++)
    		if (unordered[i].length() > max)
    			max = unordered[i].length();
    	
    	for (int position = max - 1; position >= 0; position--)
    	{
    		int[] characters = new int[256];
    		
    		for (int i = 0; i < unordered.length; i++)
    		{
    			int index = position >= unordered[i].length() ? 0 : unordered[i].charAt(position);
    			characters[index]++;
    		}
    		
    		for (int i = 1; i < 256; i++)
    			characters[i] += characters[i - 1];
    		
    		if (characters[0] == characters[255])
    			break;
    		
    		for (int i = unordered.length - 1; i >= 0; i--)
    		{
    			int index = position >= unordered[i].length() ? 0 : unordered[i].charAt(position);
    			ordered[--characters[index]] = unordered[i];
    		}
    		
    		String[] temp = ordered;
    		ordered = unordered;
    		unordered = temp;
    	}
    	
    	if (unordered != list)
    		for (int i = 0; i < list.length; i++)
    			list[i] = unordered[i];
    }
    
    public static <T extends Comparable<T>> void shell(T list[])
	{
		int h = 1;
		
		while (h < list.length / 3)
			h = h * 3 + 1;
		
		while (h > 0) {
			for(int i = h; i < list.length; i++)
			{
				T aux = list[i];
				
				int j;
				for (j = i; j >= h && list[j - h].compareTo(aux) > 0; j-= h)
					list[j] = list[j - h];
				
				list[j] = aux;
			}
			
			h /= 3;
		}
	}
}
