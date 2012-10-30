Validator.nu+CSSValidator Standalone
====================================

The Validator.nu HTML Validator and CSS Validator bundled in one single and standalone service.

With this, you can build, assemble and run the service locally within minutes.

How to build the validators
---------------------------

You need Python, Java >1.7, Mercurial, SVN and ant.

```bash
JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64 make jar
```

You need to make sure that `JAVA_HOME` points to your local Java
home. It may even be already set.

Here is what happens:
* the script takes the code from the official repositories [there](http://validator.nu/) and [there](http://dev.w3.org/cvsweb/2002/css-validator/)
* the jars and wars are made available to sbt

How to generate the standalone jar
----------------------------------

You can now do the following:

```bash
./sbt assembly
```

How to start the service
------------------------

Using the jar you previously built:

```bash
java -jar target/validators.jar 8888
```

Then go to [http://localhost:8888/nu](http://localhost:8888/nu) or [http://localhost:8888/css/](http://localhost:8888/css).

Licence
-------

This source code is made available under the [W3C Licence](http://opensource.org/licenses/W3C).
