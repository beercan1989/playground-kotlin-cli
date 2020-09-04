package uk.co.baconi.playground.cli

import com.github.ajalt.clikt.core.PrintHelpMessage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CliCommandSpec : StringSpec({
    "Should print help on no input" {
        shouldThrow<PrintHelpMessage> {
            CliCommand().parse(emptyArray())
        }
    }
})