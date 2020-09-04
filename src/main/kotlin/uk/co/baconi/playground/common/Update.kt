package uk.co.baconi.playground.common

import com.github.ajalt.clikt.core.CliktCommand

class Update : CliktCommand(printHelpOnEmptyArgs = true) {
    override fun run() {
        echo("I would update something")
    }
}
