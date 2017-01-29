# Sorting algorithms

## Build

```
python build.py
```

## Test

```
python test.py <size>
```

Where *_size* is the amount of data to generate and run on every algorithm.

This prints the time that each algorithm took to sort the same generated data.
On case of errors, a message indicating will be displayed.

## Assisgnment 01

### The input

Example file:
```
n			// the entry size
entry_1		// entry 1
entry_2		// entry 2
...
entry_n		// entry n
```

### Run one algorithm

- 1: Selection sort
- 2: Insertion sort
- 3: QuickSort
- 4: MergeSort
- 5: HeapSort

```
python assign_01.py <alg_number> <file_in> <file_out>
```

##### Example

To run algorithm `0`, selection sort, with input from file `in.txt` to file `out.txt`:

```
python run.py 0 in.txt out.txt
```

### Run multiple algorithms

Runs all algorithms and generate output files for each.

```
python run_mult.py <file_in> <file_out>
```

The output files will be named as the file name (before the dot), appended with *_[algorithm_name]* and the file format, if exists.

##### Example

With input from file `in.txt` to file `out.txt`:

```
python run_mult.py in.txt out.txt
```

Will generate the files:

`out_selection.txt`
`out_insertion.txt`
`out_quick.txt`
`out_merge.txt`
`out_heap.txt`