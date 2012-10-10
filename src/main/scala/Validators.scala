package org.w3.validator

import _root_.nu.validator.servletfilter.{ InboundGzipFilter, InboundSizeLimitFilter }
import _root_.nu.validator.servlet.{ MultipartFormDataFilter, VerifierServlet }
import org.apache.log4j.PropertyConfigurator
import org.eclipse.jetty.server.{ Server, Handler, Connector }
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.servlet.{ ServletContextHandler, ServletHolder, FilterHolder }
import org.eclipse.jetty.servlets.GzipFilter
import org.eclipse.jetty.util.thread.QueuedThreadPool
import javax.servlet.DispatcherType
import java.util.EnumSet

trait Validator {

  def handler: Handler

}

class Validators(port: Int, validators: Validator*) {

  val server: Server = {
    val pool: QueuedThreadPool = {
      val pool = new QueuedThreadPool
      pool.setMaxThreads(100)
      pool
    }
    val connector: Connector = {
      val connector = new SelectChannelConnector
      connector.setPort(port)
      connector.setMaxIdleTime(30000)
      // connector.setHost("127.0.0.1")
      connector.setThreadPool(pool)
      connector
    }
    val handlers: ContextHandlerCollection = {
      val array: Array[Handler] = validators.map(_.handler).toArray[Handler]
      val handlers = new ContextHandlerCollection
      handlers.setHandlers(array)
      handlers
    }
    val server = {
      val server = new Server
      server.setGracefulShutdown(500)
      server.setSendServerVersion(false)
      server.setSendDateHeader(true)
      server.setStopAtShutdown(true)
      server.setThreadPool(pool)
      server.addConnector(connector)
      server.setHandler(handlers)
      server
    }
    server
  }

  def start(): Unit = {
    server.start()
  }

  def stop(): Unit = {
    server.stop()
    server.join()
  }

}

object Validators {

  def main(args: Array[String]): Unit = {

    val port = args.toList.headOption.map(_.toInt) getOrElse 8080

    val validatorNu = new nu.ValidatorNu
    val cssValidator = new css.CSSValidator

    val validators = new Validators(port, validatorNu, cssValidator)
    
    validators.start()

    println(">> press Enter to stop")

    scala.Console.readLine()

    validators.stop()
  }

}
