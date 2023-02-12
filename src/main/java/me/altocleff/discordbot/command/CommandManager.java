package me.altocleff.discordbot.command;

import me.altocleff.discordbot.Config;
import me.altocleff.discordbot.command.commands.RollCommand;
import me.altocleff.discordbot.command.commands.TestCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new TestCommand());
        addCommand(new RollCommand());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already exists");
        }

        commands.add(cmd);
    }

    @Nullable
    private ICommand getCommand(String name) {
        for(ICommand cmd : this.commands) {
            if (cmd.getName().equals(name.toLowerCase()) || cmd.getAliases().contains(name.toLowerCase())) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(MessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "")
                .split("\\s+");
        ICommand cmd = this.getCommand(split[0].toLowerCase());

        if (cmd != null) {
            cmd.handle(new CommandContext(event, Arrays.asList(split).subList(1, split.length)));
        }
    }
}
