package me.altocleff.discordbot.command.commands;

import me.altocleff.discordbot.command.CommandContext;
import me.altocleff.discordbot.command.ICommand;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RollCommand implements ICommand {
    private final Map<String, String> dices = new HashMap<>();

    public RollCommand() {
        dices.put("r1", "<:red1:1074453949609955440>");
        dices.put("r2", "<:red2:1074455667403935844>");
        dices.put("r3", "<:red3:1074455668850958469>");
        dices.put("r4", "<:red4:1074453954068480191>");
        dices.put("r5", "<:red5:1074453956283093262>");
        dices.put("r6", "<:red6:1074455670176366622>");

        dices.put("g1", "<:green1:1074453939862376518>");
        dices.put("g2", "<:green2:1074453941015822458>");
        dices.put("g3", "<:green3:1074453942672556072>");
        dices.put("g4", "<:green4:1074453944509669407>");
        dices.put("g5", "<:green5:1074453946476810320>");
        dices.put("g6", "<:green6:1074455665122226216>");

        dices.put("b1", "<:blue1:1074453928000897125>");
        dices.put("b2", "<:blue2:1074453930597167165>");
        dices.put("b3", "<:blue3:1074453931947733142>");
        dices.put("b4", "<:blue4:1074453933252149309>");
        dices.put("b5", "<:blue5:1074453935856820365>");
        dices.put("b6", "<:blue6:1074453937236754565>");
    }

    @Override
    public void handle(CommandContext ctx) {
        MessageCreateBuilder mb = new MessageCreateBuilder();
        for (String s : ctx.args()) {
            String[] spl = s.split("(?<=\\d)(?=\\p{L})|(?<=\\p{L})(?=\\d)");
            if (spl.length != 2) {
                return;
            }
            List<String> diceList = diceGenerator(Integer.parseInt(spl[0]), spl[1]);
            diceList.forEach((x) -> {
                if (dices.get(x) != null) {
                    mb.addContent(dices.get(x) + " ");
                }
            });
        }
        if (mb.isEmpty()) {
            return;
        }
        ctx.event().getChannel().sendMessage(mb.build()).queue();

    }

    @Override
    public String getName() {
        return "roll";
    }

    private List<String> diceGenerator(int value, String colour) {
        List<String> diceList = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
            diceList.add(colour + randomNum);
        }
        return diceList;
    }

}
