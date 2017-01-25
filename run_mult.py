import subprocess
import sys
import shlex

_input = sys.argv[1]
_output_name = sys.argv[2]
_tokens = _output_name.split('.')

if len(_tokens) > 1:
	_output_name = _tokens[0] + '_'
	_output_name_end = '.' + _tokens[len(_tokens) - 1]
else:
	_output_name = _output_name + '_'
	_output_name_end = ''

def _call_algorithm(name, index):
	_output = open(_output_name + name + _output_name_end, 'w')

	subprocess.call(shlex.split('cd bin/ && java Program ' + str(index) + ' < ../' + _input), shell=True, stdout=_output)
	
 	_output.flush()
 	_output.close()

_call_algorithm('selection', 0)
_call_algorithm('insertion', 1)
_call_algorithm('quick', 2)
_call_algorithm('merge', 3)
_call_algorithm('heap', 4)