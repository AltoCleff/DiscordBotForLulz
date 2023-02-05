import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class JDACreator {
    private static final JDACreator JDA_CREATOR = new JDACreator();
    private JDA jda;

    private JDACreator () {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            jda = JDABuilder.createDefault(properties.getProperty("discord.token"))
                    .addEventListeners(new MessageHandler())
                    .build();
        } catch (FileNotFoundException  e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JDACreator getJDACreator() {
        return JDA_CREATOR;
    }

    public JDA getJda() {
        return jda;
    }
}
