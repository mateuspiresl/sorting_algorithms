import subprocess
import sys
import shlex

_size = sys.argv[1]
_cmd = 'cd bin/ && java Test ' + _size

if len(sys.argv) > 2:
	_output = open(sys.argv[2], 'w')
	subprocess.call(shlex.split(_cmd), shell=True, stdout=_output)
	_output.flush()
	_output.close()
else:
	subprocess.call(shlex.split(_cmd), shell=True)