package me.altocleff.discordbot;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URISyntaxException;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public Config () {
        try {
            String path = Application.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();

            Dotenv.configure()
                    .directory(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }
}
