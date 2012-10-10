#!/bin/sh

# if you change this, don't forget to change the corresponding date in the Scala code!
date="2012-09-20"

## this script probably runs only on a Linux-based machine
## you need CVS, Java and ant to be already installed

rm -Rf 2002 src/main/resource/css-validator*.war

# check out the source
export CVSROOT=":pserver:anonymous@dev.w3.org:/sources/public"
echo
echo "IMPORTANT: enter anonymous as the password for cvs"
echo
cvs login
cvs get -D "$date" 2002/css-validator

# build the WAR file
cd 2002/css-validator
ant
ant war

# stages the WAR so that it's part of the SBT project
cd ../..
echo
echo "cp 2002/css-validator/css-validator.war src/main/resources/css-validator-$date.war"
cp 2002/css-validator/css-validator.war src/main/resources/css-validator-$date.war