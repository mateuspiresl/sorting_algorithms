import subprocess
import sys
import shlex

def task(args, output):
	_index = 0
	_input = ''
	_program = 'Assignment02'

	while args[_index].startswith('--'):
		if args[_index] == '--integer':
			_program = 'Assignment02Integer'
		else:
			_input += ' ' + args[_index]
		
		_index += 1

	_input += ' --file'

	for _i in xrange(_index, len(args)):
		_input += ' ../' + args[_i]

	_cmd = 'cd bin/ && java ' + _program + _input
	print _cmd
	sys.stdout.flush()

	if output == False:
		return subprocess.Popen(shlex.split(_cmd), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	else:
		return subprocess.call(shlex.split(_cmd), shell=True, stdout=output)


if __name__ == "__main__":
	task(sys.argv[1:], sys.stdout)