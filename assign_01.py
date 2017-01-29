import subprocess
import sys
import shlex

_algorithm = sys.argv[1]
_input = sys.argv[2]

_cmd = 'cd bin/ && java Assignment01 ' + _algorithm + ' < ../' + _input

if len(sys.argv) > 3:
	_output = open(sys.argv[3], 'w')
	subprocess.call(shlex.split(_cmd), shell=True, stdout=_output)
	_output.flush()
	_output.close()
else:
	subprocess.call(shlex.split(_cmd), shell=True)