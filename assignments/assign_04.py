# java -cp ../bin assignments.n04.Heuristic

import subprocess
import sys
import shlex

cmd = 'java -cp ../bin assignments.n04.Heuristic'
subprocess.call(shlex.split(cmd), shell=True)