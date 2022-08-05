package net.core.corediscordbot.listeners;

import net.core.corediscordbot.Commands;
import net.core.corediscordbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        var args = event.getMessage().getContentRaw().split("\\s+");

        pingCommandAction(event, args);
        infoCommandAction(event, args);
        helpCommandAction(event, args);
    }

    public void pingCommandAction(@NotNull MessageReceivedEvent event, String[] args) {
        if (args[0].equalsIgnoreCase(Main.PREFIX + Commands.PING.getName()))
            event.getChannel().sendMessage("pong!").queue();
    }

    public void infoCommandAction(@NotNull MessageReceivedEvent event, String[] args) {
        if (args[0].equalsIgnoreCase(Main.PREFIX + Commands.INFO.getName())) {
            var messageEmbed = new EmbedBuilder()
                    .setTitle("Info")
                    .setColor(Color.decode("#55A649"))
                    .addField("Core", "Core is a community to build open source softwares.", false)
                    .build();
            event.getChannel().sendMessageEmbeds(messageEmbed).queue();
        }
    }

    public void helpCommandAction(@NotNull MessageReceivedEvent event, String[] args) {
        if (args[0].equalsIgnoreCase(Main.PREFIX + Commands.HELP.getName())) {
            var commandsDescriptions = new StringBuilder();
            for (Commands command : Commands.values())
                commandsDescriptions.append(command.getName()).append(": ").append(command.getDescription()).append("\n");

            var messageEmbed = new EmbedBuilder()
                    .setTitle("Help")
                    .setColor(Color.decode("#55A649"))
                    .addField("Prefix", Main.PREFIX, true)
                    .addField("Commands", commandsDescriptions.toString(), false)
                    .build();
            event.getChannel().sendMessageEmbeds(messageEmbed).queue();
        }
    }
}
