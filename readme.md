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

### The input

Example file:
```
n           // the entry size
entry_1     // entry 1
entry_2     // entry 2
...
entry_n     // entry n
```

## Assisgnment 01

Provides Selection sort, Insertion sort, QuickSort, MergeSort and HeapSort algorithms.

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

## Assignment 02

Analyzes the speed of the Java's sort, Selection sort, Insertion sort, QuickSort, MergeSort and HeapSort algorithms and outputs in .csv format.

### Running

The program will read each file as especified in [input session](#the-input).

```
python assign_02.py <file_1> <file_2> <...> <file_n>
```

### Output

Each line represents the data generated in the same order as their arguments were given and, each column, the speed in milliseconds of the algorithms Java's sort, Selection sort, Insertion sort, QuickSort, MergeSort and HeapSort, respectively.

#### Example

```
0.006,0.099,0.021,0.003,0.005,0.004 // Of the file_1
0.001,0.083,0.013,0.001,0.001,0.001 // Of the file_2
...
0.001,0.032,0.011,0.001,0.001,0.001 // Of the file_n
```

Which means Selection sort took 0.099 seconds to sort file_1 and QuickSort took 0.001 seconds to sort file_2, for example.

### Flags

#### --commented

The output will contain comments, which starts with `>>`.

```
python assign_02.py --commented <file_1> <file_2> <...> <file_n>
```

#### --ignore

`--ignore=<algorithm_1><algorithm_2><...><algorithm_n>`

To ignore algorithms, list them on the value of the attribute `ignore`.

##### Example

```
python assign_02.py --ignore=12 <file_1> <file_2> <...> <file_n>
```

Will ignore algorithm 1 and 2, which means selection and insertion sort.

#### --ignored

The replacement for the ignored algorithm time.

- If not present, the output will not consider the ignored algorithm space.
- If empty (`--ignored=`), the output will let the algorithm time space in .csv empty.
    + Output example: `0.1,0.2,,,0.5,0.6`.
- If has a value, the time space will be filled.
    + Output example for `--ignored=-`: `0.1,0.2,-,-,0.5,0.6`.

#### --average

For multiple files, the output will be the average.

##### Example

```
python assign_02.py file_1 file_2
```

If this run could ouput the following:

```
0.006,0.099,0.021,0.003,0.005,0.004
0.001,0.083,0.013,0.001,0.001,0.001
```

The line...

```
python assign_02.py --average file_1 file_2
```

Will output:

```
0.035,0.085,0.015,0.02,0.03,0.025
```

### Auto

If the files follows the pattern `<sorting_percentage>.<number_of_elements>...`, the script `assign_02_auto.py` can be used. All the flags are valid on this script too.

It needs the first argument, which is the folder that contains the input files, and has an optional argument, the output file name.
If the script in ran with the flag `--commented`, the comment output will not be written in the output file.

```
python assign_02_auto.py <folder> [output_file]
```