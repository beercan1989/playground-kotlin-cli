package uk.co.baconi.playground.slack

import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory

class BotServer {

    private val session = SlackSessionFactory.createWebSocketSlackSession(System.getenv("SLACK_BOT_AUTH_TOKEN"))

    fun start() {
        session.connect()

        session.addMessagePostedListener { event, session ->

            val console = BotConsole(session, event.channel)
            val bot = BotCommand(console)

            // Don't reply to yourself its not healthy
            if (session.sessionPersona().id == event.sender.id) {
                return@addMessagePostedListener
            }

            // Only reply to messages directed at the bot
            if (!(event.channel.isDirect || event.messageContent.startsWith("<@${session.sessionPersona().id}> "))) {
                return@addMessagePostedListener
            }

            // Remove the @ mention of the bot
            val message = event.messageContent.replace("<@${session.sessionPersona().id}>", "").trim()

            // Process as "normal"
            bot.process(message.split(' '))
        }

        while (true) {
            // Force us to wait for input and a sigterm, but really we want a sigterm
            System.`in`.read()
        }
    }
}