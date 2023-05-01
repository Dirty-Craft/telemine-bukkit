package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.regex.Pattern;

public class PlayerDeathHandler extends Handler {
    public static boolean isEnabled()
    {
        if (!Handler.isEnabled()) return false;
        return Configuration.FEATURE_PLAYER_DEATH_MESSAGES;
    }

    public static void handle(PlayerDeathEvent event)
    {
        LOGGER.info("Sending player death message");
        String playerName = event.getPlayer().getName();
        String deathMessageKey = event.deathMessage()
                .toString()
                .split("key=\"")[1]
                .split("\",")[0];
        String tempDeathMessage = translateText(deathMessageKey).replaceAll(Pattern.quote("%1$s "), "");
        if (deathMessageKey.endsWith(".player")) {
            try {
                tempDeathMessage = tempDeathMessage.replaceAll(Pattern.quote("%2$s"), event.getPlayer().getKiller().getName());
            } catch (Exception e) {
                //
            }
        }
        String deathMessage = tempDeathMessage;
        LOGGER.info(deathMessage);

        new Thread(() -> {
            String output = API.sendMessage(
                    Configuration.LANG_PLAYER_DEATH_MESSAGE.replaceAll("\\{death_message\\}", deathMessage)
                            .replaceAll("\\{player_name\\}", playerName),
                    Configuration.FEATURE_PLAYER_DEATH_MESSAGES_CHAT_ID
            );

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send player death message");
            }
        }).start();
    }
}
