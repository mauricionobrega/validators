PROGRAM			=	validator-nu-standalone

all:			$(PROGRAM)

$(PROGRAM):		validator
				
validator:		
				-mkdir lib
				make -C checker all
				./sbt compile
					
clean:			
				make -C checker clean
				-rm -rf lib/*.jar
				
dist-clean:		clean
				make -C checker dist-clean
				-rm -rf lib
				
run:			validator
				./sbt run
				
jar:			validator
				./sbt assembly