import subprocess
import sys
import shlex

_algorithm = sys.argv[1]
_input = sys.argv[2]
_output_name = sys.argv[3]

_output = open(_output_name, 'w')
_cmd = 'cd bin/ && java Program ' + _algorithm + ' < ../' + _input

subprocess.call(shlex.split(_cmd), shell=True, stdout=_output)

_output.flush()
_output.close()