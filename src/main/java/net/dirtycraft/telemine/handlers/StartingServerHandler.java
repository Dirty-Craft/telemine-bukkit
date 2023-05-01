package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;

public class StartingServerHandler extends Handler {
    public static boolean isEnabled()
    {
        if (!Handler.isEnabled()) return false;
        return Configuration.FEATURE_STARTING_SERVER_MESSAGE;
    }

    public static void handle()
    {
        LOGGER.info("Sending starting server message");

        new Thread(() -> {
            String output = API.sendMessage(Configuration.LANG_STARTING_SERVER_MESSAGE, Configuration.FEATURE_STARTING_SERVER_MESSAGE_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send starting server message");
            }
        }).start();
    }
}