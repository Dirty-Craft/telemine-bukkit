package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import net.dirtycraft.telemine.Telemine;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementMadeHandler extends Handler {
    public static boolean ENABLED = Configuration.FEATURE_ADVANCEMENT_MADE_MESSAGES;

    public static void handle(PlayerAdvancementDoneEvent event)
    {
        if (event.getAdvancement().getKey().toString().contains("minecraft:recipes")) {
            return;
        }

        String playerName = event.getPlayer().getName();
        String theKey = "advancements." + event.getAdvancement().getKey().getKey().replaceAll("/", ".") + ".title";
        String advancementName = translateText(theKey);
        if (advancementName == theKey) {
            return;
        }

        String messageToSend = Configuration.LANG_ADVANCEMENT_MADE_MESSAGE.replaceAll("\\{player_name\\}", playerName).replaceAll("\\{advancement_name\\}", advancementName);
        Telemine.LOGGER.info("Sending advancement made message");

        new Thread(() -> {
            String output;
            if (!Configuration.FEATURE_ADVANCEMENT_MADE_MESSAGES_CHAT_ID.isEmpty()) {
                output = Telemine.API.sendMessage(messageToSend, Configuration.FEATURE_ADVANCEMENT_MADE_MESSAGES_CHAT_ID);
            } else {
                output = Telemine.API.sendMessage(messageToSend);
            }

            if (output.equals("ERROR")) {
                Telemine.LOGGER.error("Failed to send advancement made message");
            }
        }).start();
    }
}
