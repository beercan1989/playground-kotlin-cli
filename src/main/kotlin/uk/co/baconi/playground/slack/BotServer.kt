package uk.co.baconi.playground.slack

import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory

class BotServer {

    private val session = SlackSessionFactory.createWebSocketSlackSession(System.getenv("SLACK_BOT_AUTH_TOKEN"))

    fun start() {
        session.connect()

        session.addMessagePostedListener(BotMessageListener())

        while (true) {
            // Force us to wait for input and a sigterm, but really we want a sigterm
            System.`in`.read()
        }
    }
}