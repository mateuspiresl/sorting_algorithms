import os
import sys
import assign_02

commented = ['--commented']
ignored = ['--ignore=12', '--ignored=']
params = ['--average']

def task(this_params, size, output):
	if size != '100000':
		this_params = ignored + this_params

	this_params = commented + this_params

	print this_params
	sys.stdout.flush()

	process = assign_02.task(this_params, False)

	while process.poll() is None:
		out = process.stdout.readline()
		if out is '': continue
		
		if output != sys.stdout and not out.startswith('>>'):
			output.write(out)
			output.flush()

		sys.stdout.write(out)
		sys.stdout.flush()


in_folder = sys.argv[1]

if len(sys.argv) > 2:
	print 'Output to file ../' + sys.argv[2]
	output = open('../' + sys.argv[2], 'w')
else:
	output = sys.stdout

if not in_folder.endswith('/'):
	in_folder = in_folder + '/'

size = False
input_files = False

for file in os.listdir(in_folder):
	tokens = file.split('.')
	print tokens

	if tokens[1] != size or tokens[1] == False:
		if input_files != False:
			task(params + input_files, size, output)

		size = tokens[1]
		input_files = []
	
	input_files.append(in_folder + file)

task(params + input_files, size, output)

if output != sys.stdout:
	output.flush()
	output.close()