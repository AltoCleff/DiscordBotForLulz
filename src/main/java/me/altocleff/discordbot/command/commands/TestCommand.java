package me.altocleff.discordbot.command.commands;

import me.altocleff.discordbot.command.CommandContext;
import me.altocleff.discordbot.command.ICommand;

public class TestCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.event().getChannel().sendMessage("Иди нахуй, " + ctx.event().getAuthor().getName() + "!").queue();
    }

    @Override
    public String getName() {
        return "nahui";
    }
}
