# Sorting algorithms

## Build

```
    python build.py
```

## Run

### One algorithm

- 0: Selection sort
- 1: Insertion sort
- 2: QuickSort
- 3: MergeSort
- 4: HeapSort

```
    python run.py alg_number file_in file_out
```

##### Example
To run algorithm `0`, selection sort, with input from file `in.txt`

```
    python run.py 0 in.txt out.txt
```

### Multiple algorithms
Runs all algorithms.

```
    python run_mult.py file_in file_out
```

##### Example
With input from file `in.txt`

```
    python run_mult.py in.txt out.txt
```

As output:

`out_selection.txt`
`out_insertion.txt`
`out_quick.txt`
`out_merge.txt`
`out_heap.txt`