PROGRAM			=	validator-nu

all:			$(PROGRAM)

$(PROGRAM):		copy
				
				
build:			
				`which hg` clone https://bitbucket.org/validator/build build
				-`which python` build/build.py all
				-`which python` build/build.py checkout dldeps dltests build test
				
copy:			build
				cp jars/*.jar ../lib/
				cp jing-trang/build/jing.jar ../lib/jing.jar
							
clean:			
				rm -rf build jars

dist-clean:		clean
				rm -rf dependencies extras htmlparser jing-trang local-entities logs nu-validator-site syntax util validator xmlparser