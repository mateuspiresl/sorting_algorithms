from subprocess import call

call(["mkdir", "bin/"])
call(["javac", "-d", "bin", "src/algorithms/analysis/*.java", "src/algorithms/*.java", "src/assignments/*.java"])

# javac -d bin src/algorithms/analysis/*.java src/algorithms/*.java src/assignments/*.java 