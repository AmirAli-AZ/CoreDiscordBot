package net.core.corediscordbot;

import net.core.corediscordbot.listeners.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static final String PREFIX = "!";

    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault(System.getenv("TOKEN"))
                .setActivity(Activity.playing("Core"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageListener())
                .build();
    }
}
