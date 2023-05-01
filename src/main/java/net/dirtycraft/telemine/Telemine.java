package net.dirtycraft.telemine;

import io.papermc.lib.PaperLib;
import net.dirtycraft.telemine.handlers.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Telemine extends JavaPlugin {
  public static final String MOD_ID = "Telemine";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final Api API = new Api();

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);
    saveDefaultConfig();

    LOGGER.info("Loading configuration");
    Configuration.load();

    if (API.isValid()) {
      String output = "ERROR";
      try {
        output = API.get("getme");
      } catch (Exception e) {
        output = "ERROR";
      }

      if (output.equals("ERROR")) {
        LOGGER.error("Failed to connect to the bot");
      } else {
        LOGGER.info("Connected to the bot successfully: " + output);
      }
    }

    this.getCommand("telemine-reload-configuration").setExecutor(new ReloadConfigurationCommand());
    this.getCommand("tg").setExecutor(new TgCommandHandler());
    Handler.loadTranslations(getClass().getResourceAsStream("/translations.json"));
    getServer().getPluginManager().registerEvents(new EventListener(), this);
    if (StartingServerHandler.isEnabled()) StartingServerHandler.handle();
  }

  @Override
  public void onDisable() {
    if (StoppingServerHandler.isEnabled()) StoppingServerHandler.handle();
  }
}
