# java -cp ./bin assignments.Assignment03 0 --std < assignments/assign_02.in.txt > assignments/assign_02.myout.txt

import subprocess
import sys
import shlex

_cmd = 'java -cp ./../bin assignments.Assignment03 ' + sys.argv[1] + ' --std'
subprocess.call(shlex.split(_cmd), shell=True)