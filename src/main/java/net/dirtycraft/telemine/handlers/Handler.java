package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Api;
import net.dirtycraft.telemine.Configuration;
import net.dirtycraft.telemine.Telemine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Handler {
    public static final Logger LOGGER = Telemine.LOGGER;
    public static final Api API = Telemine.API;
    public static JSONObject translationData = new JSONObject();

    public static String getOnlinePlayersList()
    {
        return getOnlinePlayersList(false, "");
    }

    public static void loadTranslations(InputStream inputStream)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String content = "";
        String currentLine = "";
        try {
            while ((currentLine = reader.readLine()) != null) {
                content += currentLine + "\n";
            }
        } catch (Exception e) {
            content = "{}";
        }

        try {
            translationData = (JSONObject) (new JSONParser()).parse(content);
        } catch (Exception e) {
            //
        }
    }

    public static String getOnlinePlayersList(boolean doExclude, String playerToExclude)
    {
        var players = Bukkit.getServer().getOnlinePlayers().toArray();
        String[] playerNames = new String[players.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = ((Player) players[i]).getName();
        }

        if (doExclude) {
            String[] newArray = new String[playerNames.length - 1];
            int counter = 0;
            for (int i = 0; i < playerNames.length; i++) {
                if (!playerNames[i].equals(playerToExclude)) {
                    newArray[counter] = playerNames[i];
                    counter++;
                }
            }
            playerNames = newArray;
        }

        return Configuration.LANG_ONLINE_PLAYERS_LIST_MESSAGE.replaceAll("\\{list\\}", String.join(", ", playerNames))
                .replaceAll("\\{count\\}", Integer.toString(playerNames.length))
                .replaceAll("\\{max_count\\}", Integer.toString(Bukkit.getServer().getMaxPlayers()));
    }

    public static String translateText(String key)
    {
        try {
            return translationData.get(key).toString();
        } catch (Exception e) {
            return key;
        }
    }
}
