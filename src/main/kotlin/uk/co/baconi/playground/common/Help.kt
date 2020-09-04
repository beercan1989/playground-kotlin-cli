package uk.co.baconi.playground.common

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage

class Help(private val parent: CliktCommand) : CliktCommand() {
    override fun run() {
        throw PrintHelpMessage(parent)
    }
}