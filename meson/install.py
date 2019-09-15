#!/usr/bin/env python3

import sys, os
import glob
import shutil
import zipfile
import tempfile


dependencies = glob.glob('../lib/jar/*/*.jar')

temporary = tempfile.mkdtemp()


## Extract JAR dependencies

for dependency in dependencies:

	print('Extracting ' + dependency[11:])

	shutil.unpack_archive(dependency, temporary, 'zip')

	sys.stdout.write('\033[F\033[K')


## Remove unusual files and add resource directory

shutil.rmtree(temporary + '/META-INF')
shutil.copytree('../res', temporary + '/res')


## Merge dependencies in the executable JAR

jar = zipfile.ZipFile('../build/src/Orbit.jar', 'a')

namelist = jar.namelist()

print('Merging...')


for directory, subdirs, files in os.walk(temporary):

	# name of the current directory, without the /tmp/.../ prefix
	dirname = directory[len(temporary):]


	if dirname == '':
		continue

	# prevent for duplication warning
	if dirname[1:] + os.sep in namelist:
		continue


	## Create a new empty directory in the executable JAR
	jar.write(directory, dirname)


	for file in files:

		# name of the current file
		file = os.path.join(directory, file)

		# name of the current file, without the /tmp/.../ prefix
		filename = file[len(temporary):]


		## Append a Java Class file to the executable JAR
		jar.write(file, filename)


jar.close()

sys.stdout.write('\033[F\033[K')
print('Success')
