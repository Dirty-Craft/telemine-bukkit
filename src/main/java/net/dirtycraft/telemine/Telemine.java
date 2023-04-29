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

    if (!Configuration.ENABLED) {
      //
    } else if (Configuration.BOT_TOKEN == "") {
      LOGGER.warn("The Telegram bot token is not set in the configs");
    } else if (Configuration.CHAT_ID == "") {
      LOGGER.warn("The Telegram chat ID is not set in the configs");
    } else {
      API.enabled = true;
      API.botToken = Configuration.BOT_TOKEN;
      API.groupID = Configuration.CHAT_ID;
      API.proxyHost = Configuration.PROXY_HOST;
      API.proxyPort = Configuration.PROXY_PORT;
    }

    if (!API.isValid()) {
      LOGGER.warn("The mod is disabled");
    } else {
      String output = "ERROR";
      try {
        output = API.get("getme");
      } catch (Exception e) {
        output = "ERROR";
      }

      if (output.equals("ERROR")) {
        API.enabled = false;
        LOGGER.error("Failed to connect to the bot");
      } else {
        LOGGER.info("Connected to the bot successfully: " + output);
      }
    }

    if (API.isValid()) {
      Handler.loadTranslations(getClass().getResourceAsStream("/translations.json"));
      getServer().getPluginManager().registerEvents(new EventListener(), this);
      if (StartingServerHandler.ENABLED) StartingServerHandler.handle();
      if (TgCommandHandler.ENABLED) this.getCommand("tg").setExecutor(new TgCommandHandler());
    }
  }

  @Override
  public void onDisable() {
    if (API.isValid()) {
      if (StoppingServerHandler.ENABLED) StoppingServerHandler.handle();
    }
  }
}
