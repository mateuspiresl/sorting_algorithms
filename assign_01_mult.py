import subprocess
import sys
import shlex

_input = sys.argv[1]
_tokens = sys.argv[2].split('.')

def _call_algorithm(name, index):
	_tokens[0] = tokens[0] + '_' + name
	_output = open('.'.join(tokens), 'w')
	_cmd = 'cd bin/ && java Assignment01 ' + str(index) + ' < ../' + _input

	subprocess.call(shlex.split(cmd), shell=True, stdout=_output)
	
 	_output.flush()
 	_output.close()

_call_algorithm('selection', 1)
_call_algorithm('insertion', 2)
_call_algorithm('quick', 3)
_call_algorithm('merge', 4)
_call_algorithm('heap', 5)