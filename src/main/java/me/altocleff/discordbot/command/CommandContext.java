package me.altocleff.discordbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public final class CommandContext {
    private final MessageReceivedEvent event;
    private final List<String> args;

    public CommandContext(MessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
    }

    public MessageReceivedEvent event() {
        return event;
    }

    public List<String> args() {
        return args;
    }
}
