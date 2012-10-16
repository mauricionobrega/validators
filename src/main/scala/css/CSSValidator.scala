package org.w3.validator.css

import org.w3.validator._
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler, ServletHolder }
import org.eclipse.jetty.util.component.LifeCycle
import java.util.concurrent.CountDownLatch
import java.io._
import java.nio.file._

/**
 * http://download.eclipse.org/jetty/stable-8/apidocs/
 * http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
 */
class CSSValidator(prefix: String = "/css") extends Validator {

  // shut off the "logs"

  def handler: ServletContextHandler = {

    // the path to the war file
    // first, tries to see if this is running from sbt
    // if it's not, we assume we are in the jar and that the war is available there
    val warPath: String = {
      val date = "2012-09-20"
      val warName = "css-validator-" + date + ".war"
      val sbtPath = "src/main/resources/" + warName
      if (Paths.get(sbtPath).toFile.exists) {
        sbtPath
      } else {
        val tmpPath = Paths.get(java.lang.System.getProperty("java.io.tmpdir")).resolve(warName)
        // this extracts the war file and make it available in the tmp directory
        if (! tmpPath.toFile.exists) {
          val is: InputStream = getClass().getResourceAsStream("/" + warName)
          Files.copy(is, tmpPath)
        }
        tmpPath.toString
      }
    }

    val webapp = {
      val webapp = new WebAppContext
      webapp.setContextPath(prefix)
      webapp.setWar(warPath)
      webapp
    }

    webapp

  }
}
