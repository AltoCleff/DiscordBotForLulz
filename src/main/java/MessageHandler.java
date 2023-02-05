import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class MessageHandler extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            if(event.getAuthor().getId().equals("500360296729280532")) {
                event.getChannel().sendMessage("Держи в курсе, Пава!").timeout(5, TimeUnit.MINUTES).submit();
            }
        }
    }
}
