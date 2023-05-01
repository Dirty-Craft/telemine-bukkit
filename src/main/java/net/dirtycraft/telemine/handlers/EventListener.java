package net.dirtycraft.telemine.handlers;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void playerJoinHandler(PlayerJoinEvent event)
    {
        if (PlayerJoinHandler.isEnabled()) {
            PlayerJoinHandler.handle(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLeaveHandler(PlayerQuitEvent event)
    {
        if (PlayerLeaveHandler.isEnabled()) {
            PlayerLeaveHandler.handle(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerDeathHandler(PlayerDeathEvent event)
    {
        if (PlayerDeathHandler.isEnabled()) {
            PlayerDeathHandler.handle(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void serverCommandHandler(ServerCommandEvent event)
    {
        if (CommandExecuteHandler.isEnabled()) {
            CommandExecuteHandler.handle(event.getSender().getName(), event.getCommand());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerCommandHandler(PlayerCommandPreprocessEvent event)
    {
        if (CommandExecuteHandler.isEnabled()) {
            if (event.getMessage().startsWith("/")) {
                CommandExecuteHandler.handle(event.getPlayer().getName(), event.getMessage());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void villagerDeathHandler(EntityDeathEvent event)
    {
        if (VillagerDeathHandler.isEnabled()) {
            if (event.getEntityType().equals(EntityType.VILLAGER)) {
                VillagerDeathHandler.handle(event);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void advancementMadeHandler(PlayerAdvancementDoneEvent event)
    {
        if (AdvancementMadeHandler.isEnabled()) {
            AdvancementMadeHandler.handle(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void serverStartedHandler(ServerLoadEvent event)
    {
        if (ServerStartedHandler.isEnabled()) {
            ServerStartedHandler.handle();
        }
    }
}
