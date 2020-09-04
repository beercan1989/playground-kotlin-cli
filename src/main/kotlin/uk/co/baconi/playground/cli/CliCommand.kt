package uk.co.baconi.playground.cli

import com.github.ajalt.clikt.core.Abort
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.eagerOption
import uk.co.baconi.playground.common.Create
import uk.co.baconi.playground.common.Help
import uk.co.baconi.playground.common.Update
import uk.co.baconi.playground.slack.BotServer

class CliCommand : CliktCommand(name = "tum", printHelpOnEmptyArgs = true) {

    init {
        eagerOption("--server") {
            BotServer().start()
            throw Abort(error = false)
        }
        subcommands(
            Create(),
            Update(),
            Help(this)
        )
    }

    override fun run() {}
}


fun main(args: Array<String>) {
    CliCommand().main(args)
}