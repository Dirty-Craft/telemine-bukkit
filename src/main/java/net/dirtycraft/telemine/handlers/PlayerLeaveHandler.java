package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveHandler extends Handler {
    public static boolean ENABLED = Configuration.FEATURE_PLAYER_LEAVE_MESSAGES;

    public static void handle(PlayerQuitEvent event)
    {
        LOGGER.info("Sending player leave message");

        String playerName = event.getPlayer().getName();
        String message;

        if (Configuration.FEATURE_PLAYER_LEAVE_MESSAGE_SHOW_ONLINE_PLAYERS_LIST) {
            message = Configuration.LANG_PLAYER_LEFT_MESSAGE.replaceAll("\\{name\\}", playerName) + "\n\n" + getOnlinePlayersList(true, playerName);
        } else {
            message = Configuration.LANG_PLAYER_LEFT_MESSAGE.replaceAll("\\{name\\}", playerName);
        }

        new Thread(() -> {
            String output = API.sendMessage(message, Configuration.FEATURE_PLAYER_LEAVE_MESSAGES_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send player leave message");
            }
        }).start();
    }
}
