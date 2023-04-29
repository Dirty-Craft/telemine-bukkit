package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TgCommandHandler extends Handler implements CommandExecutor {
    public static boolean ENABLED = Configuration.FEATURE_TG_SEND_MESSAGE_COMMAND;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        new Thread(() -> {
            String playerName;
            String message = String.join(" ", args);
            try {
                playerName = sender.getName();
            } catch (Exception e) {
                playerName = "[Server]";
            }
            String output;
            String messageToBeSent = Configuration.LANG_TG_COMMAND_SEND_MESSAGE_FORMAT.replaceAll("\\{message\\}", message);
            try {
                output = API.sendMessage(messageToBeSent.replaceAll("\\{player_name\\}", playerName), Configuration.FEATURE_TG_SEND_MESSAGE_COMMAND_CHAT_ID);
            } catch (Exception e) {
                output = "ERROR";
            }

            if (output.equals("ERROR")) {
                sender.sendMessage("Failed to send the message!");
            } else {
                sender.sendMessage("Message sent to the Telegram group successfully");
            }
        }).start();

        return true;
    }
}
