package net.dirtycraft.telemine;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configuration {
    public static FileConfiguration configs;
    public static File file;

    public static boolean ENABLED = false;


    public static String BOT_TOKEN = "";
    public static String CHAT_ID = "";


    public static String PROXY_HOST = "";
    public static String PROXY_PORT = "";


    public static boolean FEATURE_STARTING_SERVER_MESSAGE = true;
    public static String FEATURE_STARTING_SERVER_MESSAGE_CHAT_ID = "";
    public static String LANG_STARTING_SERVER_MESSAGE = "Starting the server...";


    public static boolean FEATURE_SERVER_STARTED_AND_READY_MESSAGE = true;
    public static String FEATURE_SERVER_STARTED_AND_READY_MESSAGE_CHAT_ID = "";
    public static String LANG_SERVER_STARTED_AND_READY_MESSAGE = "Server is up!";


    public static boolean FEATURE_SERVER_SHUTDOWN_MESSAGE = true;
    public static String FEATURE_SERVER_SHUTDOWN_MESSAGE_CHAT_ID = "";
    public static String LANG_SERVER_SHUTDOWN_MESSAGE = "Shutting down the server...";


    public static boolean FEATURE_PLAYER_JOIN_MESSAGES = true;
    public static String FEATURE_PLAYER_JOIN_MESSAGES_CHAT_ID = "";
    public static String LANG_PLAYER_JOIN_MESSAGE = "{name} joined the server";


    public static boolean FEATURE_PLAYER_JOIN_MESSAGE_SHOW_ONLINE_PLAYERS_LIST = true;
    public static boolean FEATURE_PLAYER_LEAVE_MESSAGE_SHOW_ONLINE_PLAYERS_LIST = true;
    public static String LANG_ONLINE_PLAYERS_LIST_MESSAGE = "There are {count} of a max of {max_count} players online: {list}";


    public static boolean FEATURE_PLAYER_LEAVE_MESSAGES = true;
    public static String FEATURE_PLAYER_LEAVE_MESSAGES_CHAT_ID = "";
    public static String LANG_PLAYER_LEFT_MESSAGE = "{name} left the server";


    public static boolean FEATURE_PLAYER_DEATH_MESSAGES = true;
    public static String FEATURE_PLAYER_DEATH_MESSAGES_CHAT_ID = "";
    public static String LANG_PLAYER_DEATH_MESSAGE = "{player_name} {death_message}";


    public static boolean FEATURE_ADVANCEMENT_MADE_MESSAGES = true;
    public static String FEATURE_ADVANCEMENT_MADE_MESSAGES_CHAT_ID = "";
    public static String LANG_ADVANCEMENT_MADE_MESSAGE = "{player_name} has made the advancement [{advancement_name}]";


    public static boolean FEATURE_MONITOR_COMMAND_EXECUTIONS = false;
    public static String FEATURE_MONITOR_COMMAND_EXECUTIONS_CHAT_ID = "";
    public static String LANG_COMMAND_REPORT_MESSAGE = "{player_name} entered a command: {command}";


    public static boolean FEATURE_TG_SEND_MESSAGE_COMMAND = true;
    public static String FEATURE_TG_SEND_MESSAGE_COMMAND_CHAT_ID = "";
    public static String LANG_TG_COMMAND_SEND_MESSAGE_FORMAT = "{player_name}: {message}";


    public static boolean FEATURE_VILLAGER_DEATH_MESSAGE = false;
    public static String FEATURE_VILLAGER_DEATH_MESSAGE_CHAT_ID = "";
    public static String LANG_VILLAGER_DEATH_MESSAGE = "{death_message} at {location}";


    public static String LANG_GENERAL_MESSAGE_HEADER = "";
    public static String LANG_GENERAL_MESSAGE_FOOTER = "";

    public static void load()
    {
        load(false);
    }

    public static void load(boolean isReload)
    {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(Telemine.MOD_ID).getDataFolder(), "config.yml");
        configs = YamlConfiguration.loadConfiguration(file);
        assignConfigs(isReload);
    }


    private static void assignConfigs(boolean isReload) {
        ENABLED = configs.getBoolean("enabled", ENABLED);

        BOT_TOKEN = configs.getString("telegram.bot_token", BOT_TOKEN);
        CHAT_ID = configs.getString("telegram.chat_id", CHAT_ID);

        PROXY_HOST = configs.getString("proxy.host", PROXY_HOST);
        PROXY_PORT = configs.getString("proxy.port", PROXY_PORT);

        FEATURE_STARTING_SERVER_MESSAGE = configs.getBoolean("feature.starting_server_message", FEATURE_STARTING_SERVER_MESSAGE);
        FEATURE_STARTING_SERVER_MESSAGE_CHAT_ID = configs.getString("feature.starting_server_message_chat_id", CHAT_ID);
        LANG_STARTING_SERVER_MESSAGE = configs.getString("lang.starting_server_message", LANG_STARTING_SERVER_MESSAGE);

        FEATURE_SERVER_STARTED_AND_READY_MESSAGE = configs.getBoolean("feature.server_started_and_ready_message", FEATURE_SERVER_STARTED_AND_READY_MESSAGE);
        FEATURE_SERVER_STARTED_AND_READY_MESSAGE_CHAT_ID = configs.getString("feature.server_started_and_ready_message_chat_id", CHAT_ID);
        LANG_SERVER_STARTED_AND_READY_MESSAGE = configs.getString("lang.server_started_and_ready_message", LANG_SERVER_STARTED_AND_READY_MESSAGE);

        FEATURE_SERVER_SHUTDOWN_MESSAGE = configs.getBoolean("feature.server_shutdown_message", FEATURE_SERVER_SHUTDOWN_MESSAGE);
        FEATURE_SERVER_SHUTDOWN_MESSAGE_CHAT_ID = configs.getString("feature.server_shutdown_message_chat_id", CHAT_ID);
        LANG_SERVER_SHUTDOWN_MESSAGE = configs.getString("lang.server_shutdown_message", LANG_SERVER_SHUTDOWN_MESSAGE);

        FEATURE_PLAYER_JOIN_MESSAGES = configs.getBoolean("feature.player_join_messages", FEATURE_PLAYER_JOIN_MESSAGES);
        FEATURE_PLAYER_JOIN_MESSAGES_CHAT_ID = configs.getString("feature.player_join_messages_chat_id", CHAT_ID);
        LANG_PLAYER_JOIN_MESSAGE = configs.getString("lang.player_join_message", LANG_PLAYER_JOIN_MESSAGE);

        FEATURE_PLAYER_JOIN_MESSAGE_SHOW_ONLINE_PLAYERS_LIST = configs.getBoolean("feature.player_join_message_show_online_players_list", FEATURE_PLAYER_JOIN_MESSAGE_SHOW_ONLINE_PLAYERS_LIST);
        FEATURE_PLAYER_LEAVE_MESSAGE_SHOW_ONLINE_PLAYERS_LIST = configs.getBoolean("feature.player_leave_message_show_online_players_list", FEATURE_PLAYER_LEAVE_MESSAGE_SHOW_ONLINE_PLAYERS_LIST);
        LANG_ONLINE_PLAYERS_LIST_MESSAGE = configs.getString("lang.online_players_list_message", LANG_ONLINE_PLAYERS_LIST_MESSAGE);

        FEATURE_PLAYER_LEAVE_MESSAGES = configs.getBoolean("feature.player_leave_messages", FEATURE_PLAYER_LEAVE_MESSAGES);
        FEATURE_PLAYER_LEAVE_MESSAGES_CHAT_ID = configs.getString("feature.player_leave_messages_chat_id", CHAT_ID);
        LANG_PLAYER_LEFT_MESSAGE = configs.getString("lang.player_left_message", LANG_PLAYER_LEFT_MESSAGE);

        FEATURE_PLAYER_DEATH_MESSAGES = configs.getBoolean("feature.player_death_messages", FEATURE_PLAYER_DEATH_MESSAGES);
        FEATURE_PLAYER_DEATH_MESSAGES_CHAT_ID = configs.getString("feature.player_death_messages_chat_id", CHAT_ID);
        LANG_PLAYER_DEATH_MESSAGE = configs.getString("lang.player_death_message", LANG_PLAYER_DEATH_MESSAGE);

        FEATURE_ADVANCEMENT_MADE_MESSAGES = configs.getBoolean("feature.advancement_made_messages", FEATURE_ADVANCEMENT_MADE_MESSAGES);
        FEATURE_ADVANCEMENT_MADE_MESSAGES_CHAT_ID = configs.getString("feature.advancement_made_messages_chat_id", CHAT_ID);
        LANG_ADVANCEMENT_MADE_MESSAGE = configs.getString("lang.advancement_made_message", LANG_ADVANCEMENT_MADE_MESSAGE);

        FEATURE_MONITOR_COMMAND_EXECUTIONS = configs.getBoolean("feature.monitor_command_executions", FEATURE_MONITOR_COMMAND_EXECUTIONS);
        FEATURE_MONITOR_COMMAND_EXECUTIONS_CHAT_ID = configs.getString("feature.monitor_command_executions_chat_id", FEATURE_MONITOR_COMMAND_EXECUTIONS_CHAT_ID);
        LANG_COMMAND_REPORT_MESSAGE = configs.getString("lang.command_report_message", LANG_COMMAND_REPORT_MESSAGE);

        FEATURE_TG_SEND_MESSAGE_COMMAND = configs.getBoolean("feature.tg_send_message_command", FEATURE_TG_SEND_MESSAGE_COMMAND);
        FEATURE_TG_SEND_MESSAGE_COMMAND_CHAT_ID = configs.getString("feature.tg_send_message_command_chat_id", CHAT_ID);
        LANG_TG_COMMAND_SEND_MESSAGE_FORMAT = configs.getString("lang.tg_command_send_message_format", LANG_TG_COMMAND_SEND_MESSAGE_FORMAT);

        FEATURE_VILLAGER_DEATH_MESSAGE = configs.getBoolean("feature.villager_death_message", FEATURE_VILLAGER_DEATH_MESSAGE);
        FEATURE_VILLAGER_DEATH_MESSAGE_CHAT_ID = configs.getString("feature.villager_death_message_chat_id", CHAT_ID);
        LANG_VILLAGER_DEATH_MESSAGE = configs.getString("lang.villager_death_message", LANG_VILLAGER_DEATH_MESSAGE);

        LANG_GENERAL_MESSAGE_HEADER = configs.getString("lang.general_message_header", LANG_GENERAL_MESSAGE_HEADER);
        LANG_GENERAL_MESSAGE_FOOTER = configs.getString("lang.general_message_footer", LANG_GENERAL_MESSAGE_FOOTER);

        if (!ENABLED) {
            Telemine.LOGGER.warn("The mod is disabled");
        } else if (BOT_TOKEN == "") {
            Telemine.LOGGER.warn("The Telegram bot token is not set in the configs");
        } else if (CHAT_ID == "") {
            Telemine.LOGGER.warn("The Telegram chat ID is not set in the configs");
        } else {
            Telemine.API.enabled = ENABLED;
            Telemine.API.botToken = BOT_TOKEN;
            Telemine.API.groupID = CHAT_ID;
            Telemine.API.proxyHost = PROXY_HOST;
            Telemine.API.proxyPort = PROXY_PORT;
        }

        if (isReload) {
            Telemine.LOGGER.info("Configs have been re-loaded successfully");
        } else {
            Telemine.LOGGER.info("Configs have been loaded successfully");
        }
    }
}
