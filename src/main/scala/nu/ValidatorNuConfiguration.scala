package org.w3.validator.nu

object ValidatorNuConfiguration {

  val default: ValidatorNuConfiguration = {
    val config = Map[String, Any](
      ("nu.validator.servlet.read-local-log4j-properties" -> 1),
      ("nu.validator.servlet.log4j-properties" -> "validator/log4j.properties"),
      ("nu.validator.servlet.version" -> 3),
      ("nu.validator.servlet.service-name" -> "Validator.nu"),
      ("org.whattf.datatype.warn" -> true),
      ("nu.validator.servlet.about-page" -> "http://about.validator.nu/"),
      ("nu.validator.servlet.style-sheet" -> "style.css"),
      ("nu.validator.servlet.icon" -> "icon.png"),
      ("nu.validator.servlet.script" -> "script.js"),
      ("nu.validator.spec.html5-load" -> "http://www.whatwg.org/specs/web-apps/current-work/"),
      ("nu.validator.spec.html5-link" -> "http://www.whatwg.org/specs/web-apps/current-work/"),
      ("nu.validator.servlet.max-file-size" -> 7340032),
      ("nu.validator.servlet.connection-timeout" -> 5000),
      ("nu.validator.servlet.socket-timeout" -> 5000),
      ("nu.validator.servlet.w3cbranding" -> 0),
      ("nu.validator.servlet.statistics" -> 0),
      ("org.mortbay.http.HttpRequest.maxFormContentSize" -> 7340032),
      ("nu.validator.servlet.host.generic" -> ""),
      ("nu.validator.servlet.host.html5" -> ""),
      ("nu.validator.servlet.host.parsetree" -> ""),
      ("nu.validator.servlet.path.generic" -> "/"),
      ("nu.validator.servlet.path.html5" -> "/html5/"),
      ("nu.validator.servlet.path.parsetree" -> "/parsetree/"),
      ("nu.validator.servlet.path.about" -> "./validator/site/"))
    ValidatorNuConfiguration(config)
  }

}

case class ValidatorNuConfiguration(config: Map[String, Any]) {

  def readLocalLog4JProperties(b: Boolean): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.read-local-log4j-properties" -> (if (b) 1 else 0)))
  }

  def log4jProperties(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.log4j-properties" -> s))
  }

  def version(i: Int): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.version" -> i))
  }

  def serviceName(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.service-name" -> s))
  }

  def dataTypeWarn(b: Boolean): ValidatorNuConfiguration = {
    copy(config = config + ("org.whattf.datatype.warn" -> b))
  }

  def aboutPage(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.about-page" -> s))
  }

  def styleSheet(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.style-sheet" -> s))
  }

  def icon(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.icon" -> s))
  }

  def script(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.script" -> s))
  }

  def specHtml5Load(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.spec.html5-load" -> s))
  }

  def specHtml5Link(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.spec.html5-link" -> s))
  }

  def maxFileSize(i: Int): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.max-file-size" -> i))
  }

  def connectionTimeout(i: Int): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.connection-timeout" -> i))
  }

  def socketTimeout(i: Int): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.socket-timeout" -> i))
  }

  def w3cBranding(b: Boolean): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.w3cbranding" -> (if (b) 1 else 0)))
  }

  def statistics(b: Boolean): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.statistics" -> (if (b) 1 else 0)))
  }

  def httpRequestMaxFormContentSize(i: Int): ValidatorNuConfiguration = {
    copy(config = config + ("org.mortbay.http.HttpRequest.maxFormContentSize" -> i))
  }

  def hostGeneric(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.host.generic" -> s))
  }

  def hostHtml5(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.host.html5" -> s))
  }

  def hostParseTree(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.host.parsetree" -> s))
  }

  def pathGeneric(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.path.generic" -> s))
  }

  def pathHtml5(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.path.html5" -> s))
  }

  def pathParseTree(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.path.parsetree" -> s))
  }

  def pathAbout(s: String): ValidatorNuConfiguration = {
    copy(config = config + ("nu.validator.servlet.path.about" -> s))
  }

  def setSystemProperties(): Unit = {
    config foreach { case (k, v) =>
      System.setProperty(k, v.toString())
    }
  }

}
