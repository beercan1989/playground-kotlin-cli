package uk.co.baconi.playground.cli

import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("uk.co.baconi.playground.cli.Application")

/**
 * Entry point of the application
 */
fun main(args: Array<String>) {
  logger.info("Hello World: {}", args)
}
