package net.core.corediscordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class WelcomeAndBye extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        var guild = event.getGuild();
        var textChannel = guild.getTextChannelsByName("welcome-n-bye", true).get(0);
        if (textChannel == null)
            return;

        var role = guild.createRole()
                .setName("member")
                .setHoisted(false)
                .setMentionable(false)
                .setColor(Color.decode("#2196F3"))
                .complete();

        var member = event.getMember();
        var welcomeMessageEmbed = new EmbedBuilder()
                .setTitle("New member joined")
                .addField("Hey " + member.getEffectiveName(), "Welcome to core community", true)
                .setColor(Color.decode("#55A649"))
                .setThumbnail(member.getEffectiveAvatarUrl())
                .setTimestamp(Instant.now())
                .build();

        guild.addRoleToMember(member, role).queue();
        textChannel.sendMessageEmbeds(welcomeMessageEmbed).queue();
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        var guild = event.getGuild();
        var textChannel = guild.getTextChannelsByName("welcome-n-bye", true).get(0);
        if (textChannel == null)
            return;

        var user = event.getUser();
        guild.retrieveAuditLogs().queueAfter(1, TimeUnit.SECONDS, auditLogEntries -> {
            for (AuditLogEntry logEntry : auditLogEntries) {
                if (logEntry.getTargetIdLong() == user.getIdLong()) {
                    switch (logEntry.getType()) {
                        case KICK -> textChannel.sendMessage(user.getName() + " kicked").queue();
                        case BAN -> textChannel.sendMessage(user.getName() + " banned").queue();

                        default -> textChannel.sendMessage(user.getName() + " left the server").queue();
                    }

                    break;
                }
            }
        });
    }
}
