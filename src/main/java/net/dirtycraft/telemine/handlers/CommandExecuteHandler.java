package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import net.dirtycraft.telemine.Telemine;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandExecuteHandler extends Handler {
    public static boolean isEnabled()
    {
        if (!Handler.isEnabled()) return false;
        return Configuration.FEATURE_MONITOR_COMMAND_EXECUTIONS;
    }

    public static void handle(String playerName, String command)
    {
        String messageToSend = Configuration.LANG_COMMAND_REPORT_MESSAGE.replaceAll("\\{player_name\\}", playerName).replaceAll("\\{command\\}", command);
        Telemine.LOGGER.info("Sending command executed message");

        new Thread(() -> {
            String output;
            if (!Configuration.FEATURE_MONITOR_COMMAND_EXECUTIONS_CHAT_ID.isEmpty()) {
                output = Telemine.API.sendMessage(messageToSend, Configuration.FEATURE_MONITOR_COMMAND_EXECUTIONS_CHAT_ID);
            } else {
                output = Telemine.API.sendMessage(messageToSend);
            }

            if (output.equals("ERROR")) {
                Telemine.LOGGER.error("Failed to send command executed message");
            }
        }).start();
    }
}
