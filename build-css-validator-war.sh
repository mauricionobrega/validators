#!/bin/sh

# if you change this, don't forget to change the corresponding date in the Scala code!
date="2012-09-20"

## this script probably runs only on a Linux-based machine
## you need CVS, Java and ant to be already installed

# check out the source
if [ ! -d 2002 ]; then
    export CVSROOT=":pserver:anonymous@dev.w3.org:/sources/public"
    echo
    echo "IMPORTANT: enter anonymous as the password for cvs"
    echo
    cvs login
    cvs get -D "$date" 2002/css-validator
fi;

# build the jar file
cd 2002/css-validator
ant
ant jar

# stages the jar and templates so that it's part of the SBT project
cd ../..
echo copying the jar and other files
cp 2002/css-validator/css-validator.jar lib
mkdir -p src/main/resources/org/w3c/css/index src/main/resources/org/w3c/css/css
cp 2002/css-validator/*html* src/main/resources/org/w3c/css/index
cp 2002/css-validator/org/w3c/css/index/validator.vm src/main/resources
