import sys
import random
import string

size = int(sys.argv[1])
length = int(sys.argv[2])

_set = string.digits + string.ascii_letters + string.punctuation

print size

for i in xrange(size):
	print ''.join(random.choice(_set) for _ in range(length))