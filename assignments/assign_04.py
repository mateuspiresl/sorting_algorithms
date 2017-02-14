# java -cp ./bin assignments.Assignment03 0 --std < assignments/assign_02.in.txt > assignments/assign_02.myout.txt

import subprocess
import sys
import shlex

params = []
params.append('10')
params.append('100')

params.append('1000')
params.append('5000')

params.append('10000')
params.append('50000')

params.append('100000')
params.append('400000')
params.append('700000')

params.append('1000000')
params.append('4000000')
params.append('7000000')

params.append('10000000')

for _range in params:
	# print '> Range: ' + str(_range)

	for _length in params:
		sys.stdout.flush()
		
		cmd = 'java -cp ../bin assignments.Assignment04 --include=12345 --runs=3 --csv --test ' + str(_length) + ' ' + str(_range)
		subprocess.call(shlex.split(cmd), shell=True)

	print ''