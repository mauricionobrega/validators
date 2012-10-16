PROGRAM = validator-nu-standalone

all: $(PROGRAM)

$(PROGRAM): jar

clean-validator.nu:
	make -C checker clean
	-rm -rf lib/*.jar

dist-clean-cssvalidator: clean-cssvalidator
	rm -Rf 2002

dist-clean-validator.nu: clean-validator.nu

clean-cssvalidator:
	rm -Rf src/main/resource/css-validator*.war lib/css-validator.jar src/main/resources/org src/main/resources/*.vm

clean: clean-validator.nu clean-cssvalidator
	make -C checker dist-clean
	-rm -rf lib

dist-clean: clean dist-clean-cssvalidator dist-clean-validator.nu

validator.nu:
	-mkdir lib
	make -C checker all

css-validator:
	./build-css-validator-war.sh

jar: validator.nu css-validator
	./sbt assembly