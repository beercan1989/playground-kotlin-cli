package uk.co.baconi.playground.slack

import com.github.ajalt.clikt.output.CliktConsole
import com.ullink.slack.simpleslackapi.SlackChannel
import com.ullink.slack.simpleslackapi.SlackSession

class BotConsole(private val session: SlackSession, private val channel: SlackChannel) : CliktConsole {

    override val lineSeparator: String = "\n"

    override fun print(text: String, error: Boolean) {
        session.sendMessage(channel, text)
    }

    override fun promptForLine(prompt: String, hideInput: Boolean): String? {
        // TODO - Look at handling this if we end up needing it.
        session.sendMessage(channel, "I don't know how to ask for [$prompt] and listen to your response yet.")
        return null
    }
}
