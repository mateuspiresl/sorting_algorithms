# java -cp ../bin assignments.n04.Test

import subprocess
import sys
import shlex

cmd = 'java -cp ../bin assignments.n04.Test'
subprocess.call(shlex.split(cmd), shell=True)