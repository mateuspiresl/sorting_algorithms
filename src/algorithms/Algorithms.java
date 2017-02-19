package algorithms;

public enum Algorithms {
	InsertionSort("Insertion sort"),
	SelectionSort("Selection sort"),
	QuickSort("Quick sort"),
	MergeSort("Merge sort"),
	HeapSort("Heap sort"),
	ShellSort("Shell sort"),
	CountingSort("Counting"),
	BucketSort("Bucket sort"),
	RadixSort("Radix sort");
	
	public final String name;
	
	private Algorithms(String name) {
		this.name = name;
	}
}
