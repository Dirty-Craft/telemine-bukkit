package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;

public class ServerStartedHandler extends Handler {
    public static boolean ENABLED = Configuration.FEATURE_SERVER_STARTED_AND_READY_MESSAGE;

    public static void handle()
    {
        LOGGER.info("Sending server started message");

        new Thread(() -> {
            String output = API.sendMessage(Configuration.LANG_SERVER_STARTED_AND_READY_MESSAGE, Configuration.FEATURE_SERVER_STARTED_AND_READY_MESSAGE_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send server started message");
            }
        }).start();
    }
}
