package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler extends Handler {
    public static boolean isEnabled()
    {
        if (!Handler.isEnabled()) return false;
        return Configuration.FEATURE_PLAYER_JOIN_MESSAGES;
    }

    public static void handle(PlayerJoinEvent event)
    {
        LOGGER.info("Sending player join message");

        String playerName = event.getPlayer().getName();
        String message;

        if (Configuration.FEATURE_PLAYER_JOIN_MESSAGE_SHOW_ONLINE_PLAYERS_LIST) {
            message = Configuration.LANG_PLAYER_JOIN_MESSAGE.replaceAll("\\{name\\}", playerName) + "\n\n" + getOnlinePlayersList();
        } else {
            message = Configuration.LANG_PLAYER_JOIN_MESSAGE.replaceAll("\\{name\\}", playerName);
        }

        new Thread(() -> {
            String output = API.sendMessage(message, Configuration.FEATURE_PLAYER_JOIN_MESSAGES_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send player join message");
            }
        }).start();
    }
}
