package uk.co.baconi.playground.slack

import com.github.ajalt.clikt.core.*
import uk.co.baconi.playground.common.Create
import uk.co.baconi.playground.common.Help
import uk.co.baconi.playground.common.Update

class BotCommand(botConsole: BotConsole) : CliktCommand(name = "@tum", printHelpOnEmptyArgs = true) {

    init {
        context {
            console = botConsole
        }
        subcommands(
            Create(),
            Update(),
            Help(this)
        )
    }

    // Clone of main without its app kill lines
    fun process(argv: List<String>) {
        try {
            parse(argv)
        } catch (e: ProgramResult) {
            // Everything was fine
        } catch (e: PrintHelpMessage) {
            echo(e.command.getFormattedHelp())
        } catch (e: PrintCompletionMessage) {
            val s = if (e.forceUnixLineEndings) "\n" else currentContext.console.lineSeparator
            echo(e.message, lineSeparator = s)
        } catch (e: PrintMessage) {
            echo(e.message)
        } catch (e: NoSuchSubcommand) {
            // Change no such subcommand to just print the help
            echo(getFormattedHelp())
        } catch (e: UsageError) {
            echo(e.helpMessage(), err = true)
        } catch (e: CliktError) {
            echo(e.message, err = true)
        } catch (e: Abort) {
            echo(currentContext.localization.aborted(), err = true)
        }
    }

    override fun run() {
        // Nothing to do at this level, look at the sub commands
    }
}