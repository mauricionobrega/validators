import sbt._
import sbt.Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

/**
 * build configuration for Validators
 */
object ValidatorsBuild extends Build {

  val jettyVersion = "8.1.7.v20120910"
  
  lazy val validators = Project(
    id = "validator",
    base = file("."),

    settings = Defaults.defaultSettings ++ assemblySettings ++ Seq(
      organization := "org.w3",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.10.0-M7",
//      javacOptions ++= Seq("-Xlint:unchecked", "-Xmx256m", "-XX:ThreadStackSize=2048"),
      mainClass in assembly := Some("org.w3.validator.Validators"),
      jarName in assembly := "validators.jar",

      mergeStrategy in assembly <<= (mergeStrategy in assembly) {
        val fs = System.getProperty("file.separator")
        (old) =>
          {
            case "META-INF/MANIFEST.MF" => MergeStrategy.rename
            case r if r.endsWith("about.html") => MergeStrategy.discard
            case r if r.startsWith("org" + fs + "jaxen" + fs + "expr" + fs + "DefaultVariableReferenceExpr.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "expr" + fs + "PredicateSet.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "util" + fs + "AncestorOrSelfAxisIterator.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "function" + fs + "StringFunction.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "JaxenHandler.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "saxpath" + fs + "base" + fs + "TokenTypes.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "saxpath" + fs + "base" + fs + "XPathReader.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "saxpath" + fs + "base" + fs + "XPathLexer.class") => MergeStrategy.concat
            case r if r.startsWith("org" + fs + "jaxen" + fs + "SimpleNamespaceContext.class") => MergeStrategy.concat
            case x => old(x)
          }
      },
      
      test in assembly := {},
      licenses := Seq("W3C License" -> url("http://opensource.org/licenses/W3C")),
      homepage := Some(url("https://github.com/w3c/validators")),
      publishTo <<= version { (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
      },
      publishArtifact in Test := false,
      pomIncludeRepository := { _ => false },
      pomExtra := (
        <scm>
          <url>git@github.com:w3c/validator.nu-standalone.git</url>
          <connection>scm:git:git@github.com:w3c/validator.nu-standalone.git</connection>
        </scm>
        <developers>
          <developer>
            <id>betehess</id>
            <name>Alexandre Bertails</name>
            <url>http://bertails.org</url>
          </developer>
          <developer>
            <id>nunnun</id>
            <name>Hirotaka Nakajima</name>
            <url>http://hirotaka.org</url>
          </developer>
        </developers>),

      resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
      resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "apache-repo-releases" at "http://repository.apache.org/content/repositories/releases/",

      libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-util" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-server" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-io" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-http" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-security" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-continuation" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-xml" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-servlet" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "compile" intransitive(),
      libraryDependencies += "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile" artifacts (Artifact("javax.servlet", "jar", "jar")) intransitive(),
      ivyXML := 
        <dependency org="org.eclipse.jetty.orbit" name="javax.servlet" rev="3.0.0.v201112011016">
          <artifact name="javax.servlet" type="orbit" ext="jar"/>
        </dependency>,

      // validator.nu
      libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.6.4" % "compile",
      libraryDependencies += "commons-httpclient" % "commons-httpclient" % "3.1" intransitive(),
      libraryDependencies += "commons-logging" % "commons-logging" % "1.1.1" intransitive(),
      libraryDependencies += "com.hp.hpl.jena" % "iri" % "0.5" intransitive(),
      libraryDependencies += "commons-fileupload" % "commons-fileupload" % "1.2.1" intransitive(),
      libraryDependencies += "rhino" % "js" % "1.7R1" intransitive(),
      libraryDependencies += "com.ibm.icu" % "icu4j" % "4.4.2" from "http://download.icu-project.org/files/icu4j/4.4.2/icu4j-4_4_2.jar" intransitive(),
      libraryDependencies += "com.ibm.icu" % "icu4j-charsets" % "4.4.2" from "http://download.icu-project.org/files/icu4j/4.4.2/icu4j-charsets-4_4_2.jar" intransitive(),
      libraryDependencies += "commons-codec" % "commons-codec" % "1.4" intransitive(),
      libraryDependencies += "xom" % "xom" % "1.1"))

//      // css validator
//      libraryDependencies += "tagsoup" % "tagsoup" % "1.2" from "http://home.ccil.org/~cowan/XML/tagsoup/tagsoup-1.2.jar",
//      libraryDependencies += "commons-collections" % "commons-collections" % "3.2.1",
//      libraryDependencies += "commons-lang" % "commons-lang" % "2.6",
//      libraryDependencies += "velocity" % "velocity" % "1.7" from "http://www.apache.org/dist/velocity/engine/1.7/velocity-1.7.jar"





}


