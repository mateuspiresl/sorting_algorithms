javac -d bin/ src/*.java

cd bin/

# NOT --commented
PARAMS="--average"
IGNORE=" --ignore=12 --ignored="

entropy=( "10" "50" "90" )
sizes=( "100000" "500000" "1000000" )

output=""

for e in "${entropy[@]}";
do
	for s in "${sizes[@]}";
	do
		files=""
		for i in `seq 1 10`;
		do
	    	files=$files"../instancias/"$e"."$s"."$i".in "
	    done

	    params=$PARAMS
	    if [ $s = "1000000" ]; then
			params=$params$IGNORE
		fi

	    java Assignment02 $params --file $files
	done
done