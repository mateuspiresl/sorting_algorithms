# python assignments/gen.py 10 10 | java -cp ./bin assignments.Assignment04 --commented --include=12345 --string --std

import subprocess
import sys
import shlex

str_lengths = [10, 100, 400, 700, 1000]
sizes = [10, 100, 1000, 5000, 10000, 50000, 100000]

for str_length in str_lengths:
	print '> Length: ' + str(str_length)

	for size in sizes:
		sys.stdout.flush()
		
		cmd = 'java -cp ../bin assignments.Assignment04 --csv --include=12345 --string --file gen/size' + str(size) + '-len' + str(str_length) + '.in'
		# print cmd

		subprocess.call(shlex.split(cmd), shell=True)

	print ''
	print ''