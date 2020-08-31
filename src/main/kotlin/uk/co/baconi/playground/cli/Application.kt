package uk.co.baconi.playground.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import kotlin.random.Random

class Application : CliktCommand(printHelpOnEmptyArgs = true) {
    override fun run() {}
}

class Create : CliktCommand() {

    companion object {
        private val charPool: List<Char> by lazy { ('a'..'z') + ('A'..'Z') + ('0'..'9') }

        private val randomPrefix: String by lazy { randomString(4) }

        private fun randomString(length: Int) = (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    private val username by option("-u", "--username", help = "Username to create account with")
        .default(randomString(8))
        .validate {
            require(it.length >= 8) { "must be 8 characters long" }
            require(it.contains("[a-zA-Z0-9]".toRegex())) { "must consist of only alpha numeric characters" }
        }

    private val pin by option("-p", "--pin", help = "6 digit pin to create account with")
        .int()
        .restrictTo(100000..999999)
        .default(121212)

    private val prefix by option("--prefix", help = "Prefix for generated fields")
        .default(randomPrefix)
        .validate {
            require(it.contains("[a-zA-Z0-9]".toRegex())) { "must consist of only alpha numeric characters" }
        }

    override fun run() {
        echo("Username: $username")
        echo("Pin: $pin")
        echo("Prefix: $prefix")
    }
}

class Update : CliktCommand() {
    override fun run() {
        echo("I would update something")
    }
}

fun main(args: Array<String>) {
    Application()
        .subcommands(
          Create(),
          Update()
        )
        .main(args)
}