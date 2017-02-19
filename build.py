from subprocess import call

try:
	call(["mkdir", "bin/"])
except:
	pass

call(["javac", "-d", "bin", "src/algorithms/analysis/*.java", "src/algorithms/*.java", "src/assignments/*.java", "src/assignments/n04/*.java"])

# javac -d bin src/algorithms/analysis/*.java src/algorithms/*.java src/assignments/*.java 