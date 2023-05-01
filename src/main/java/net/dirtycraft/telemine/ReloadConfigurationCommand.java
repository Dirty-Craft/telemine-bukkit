package net.dirtycraft.telemine;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigurationCommand implements CommandExecutor {
    public static boolean ENABLED = Configuration.FEATURE_TG_SEND_MESSAGE_COMMAND;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!sender.hasPermission("telemine.reload-configuration")) {
            sender.sendMessage("You do not have permission to do this!");
            return false;
        }

        Configuration.load(true);
        sender.sendMessage("Configuration reloaded successfully");

        return true;
    }
}
