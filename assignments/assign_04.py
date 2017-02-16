# java -cp ./bin assignments.Assignment03 0 --std < assignments/assign_02.in.txt > assignments/assign_02.myout.txt

import subprocess
import sys
import shlex

cmd = 'java -cp ../bin assignments.Assignment04Heuristic'
subprocess.call(shlex.split(cmd), shell=True)