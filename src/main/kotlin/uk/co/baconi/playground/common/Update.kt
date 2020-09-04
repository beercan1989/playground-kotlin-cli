package uk.co.baconi.playground.common

import com.github.ajalt.clikt.core.CliktCommand

class Update : CliktCommand() {
    override fun run() {
        echo("I would update something")
    }
}
