package uk.co.baconi.playground.slack

import com.ullink.slack.simpleslackapi.SlackSession
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener

class BotMessageListener : SlackMessagePostedListener {

    override fun onEvent(event: SlackMessagePosted, session: SlackSession) {

        // Don't reply to yourself its not healthy
        if (session.sessionPersona().id == event.sender.id) {
            return
        }

        // Only reply to messages directed at yourself
        if (!(event.channel.isDirect || event.messageContent.startsWith("<@${session.sessionPersona().id}> "))) {
            return
        }

        // Remove the @ mention of the bot
        val message = event.messageContent.replace("<@${session.sessionPersona().id}>", "").trim()

        // Process as "normal"
        val bot = BotCommand(BotConsole(session, event.channel))
        bot.process(message.split(' '))
    }
}