package me.altocleff.discordbot;

import me.altocleff.discordbot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady (ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        if(event.getMessage().getContentRaw().startsWith(Config.get("prefix"))) {
            manager.handle(event);
        }
    }
}
