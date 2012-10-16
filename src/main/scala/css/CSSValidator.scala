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

  // there is a little bit of annoying ceremony to get around how the
  // css validator loads its resources
  import org.apache.velocity.app.Velocity
  org.w3c.css.util.Util.onDebug = false
  Velocity.setProperty("resource.loader", "class")
  Velocity.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader")

  def handler: ServletContextHandler = {
    val context = new ServletContextHandler
    context.setContextPath(prefix)
    context.setInitParameter("vendorExtensionsAsWarnings", "true")
    context.addServlet(new ServletHolder(new org.w3c.css.servlet.CssValidator), "/*")
    context
  }
}
