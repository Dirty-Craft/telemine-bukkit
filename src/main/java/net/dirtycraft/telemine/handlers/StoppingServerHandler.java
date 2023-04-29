package net.dirtycraft.telemine.handlers;

import net.dirtycraft.telemine.Configuration;

public class StoppingServerHandler extends Handler {
    public static boolean ENABLED = Configuration.FEATURE_SERVER_SHUTDOWN_MESSAGE;

    public static void handle()
    {
        LOGGER.info("Sending shutting server down message");

        new Thread(() -> {
            String output = API.sendMessage(Configuration.LANG_SERVER_SHUTDOWN_MESSAGE, Configuration.FEATURE_SERVER_SHUTDOWN_MESSAGE_CHAT_ID);

            if (output.equals("ERROR")) {
                LOGGER.error("Failed to send shutting server down message");
            }
        }).start();
    }
}
