PROGRAM = validator-nu-standalone

all: $(PROGRAM)

$(PROGRAM): jar

clean-validator.nu:
	make -C checker clean
	-rm -rf lib/*.jar

clean-cssvalidator:
	rm -Rf 2002 src/main/resource/css-validator*.war

clean: clean-validator.nu clean-cssvalidator
	make -C checker dist-clean
	-rm -rf lib

validator.nu:
	-mkdir lib
	make -C checker all

css-validator:
	./build-css-validator-war.sh

jar: validator.nu css-validator
	./sbt assembly