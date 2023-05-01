package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeathHandler extends Handler {
    public static boolean isEnabled()
    {
        if (!Handler.isEnabled()) return false;
        return Configuration.FEATURE_VILLAGER_DEATH_MESSAGE;
    }

    public static void handle(EntityDeathEvent event)
    {
        LOGGER.info("Sending villager death message");
        String deathMessage = translateText(event.toString());

        String position;
        try {
            position = Double.toString(event.getEntity().getLocation().x()) + ", "
                    + Double.toString(event.getEntity().getLocation().y()) + ", "
                    + Double.toString(event.getEntity().getLocation().z());
        } catch (Exception e) {
            position = "unknown";
        }

        String finalMessage = Configuration.LANG_VILLAGER_DEATH_MESSAGE.replaceAll("\\{death_message\\}", deathMessage)
                .replaceAll("\\{location\\}", position);

        new Thread(() -> {
            String output = API.sendMessage(finalMessage, Configuration.FEATURE_VILLAGER_DEATH_MESSAGE_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send villager death message");
            }
        }).start();
    }
}
