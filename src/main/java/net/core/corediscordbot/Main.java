package net.core.corediscordbot;

import net.core.corediscordbot.listeners.MessageListener;
import net.core.corediscordbot.listeners.WelcomeAndBye;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Main {

    public static final String PREFIX = "!";

    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault(System.getenv("TOKEN"))
                .setActivity(Activity.playing("Core"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(
                        new MessageListener(),
                        new WelcomeAndBye()
                )
                .build();
    }
}
