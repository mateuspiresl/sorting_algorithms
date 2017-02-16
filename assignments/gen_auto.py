import sys
import random
import string

str_lengths = [10, 100, 400, 700, 1000]
sizes = [10, 100, 1000, 5000, 10000, 50000, 100000]
_set = string.digits + string.ascii_letters + string.punctuation

for size_i in xrange(len(sizes)):
	size = sizes[size_i]

	for str_length_i in xrange(len(str_lengths)):
		str_length = str_lengths[str_length_i]

		print 'Generating size ' + str(size) + ', length ' + str(str_length)

		out = open('gen/size' + str(size) + '-len' + str(str_length) + '.in', 'w')
		out.write(str(size) + '\n')

		if size_i > 0:
			inp = open('gen/size' + str(sizes[size_i - 1]) + '-len' + str(str_length) + '.in')
			jump = int(inp.readline())

			for j in xrange(jump):
				in_line = inp.readline()
				out.write(in_line)
		else:
			jump = 0

		print 'Jumping ' + str(jump)
		gen_size = size - jump

		for i in xrange(gen_size):
			out.write(''.join(random.choice(_set) for _ in range(str_length)) + '\n')

		out.close()

		print 'Generated size ' + str(size) + ', length ' + str(str_length)