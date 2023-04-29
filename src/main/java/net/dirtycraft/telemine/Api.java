package net.dirtycraft.telemine;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Api {
    public boolean enabled = false;
    public String botToken = null;
    public String groupID = null;

    public String proxyHost = null;
    public String proxyPort = null;

    public boolean stringIsEmpty(String value)
    {
        return value == null || value.trim().isEmpty();
    }

    public boolean isValid()
    {
        if (!enabled) return false;
        if (stringIsEmpty(botToken)) return false;
        if (stringIsEmpty(groupID)) return false;

        return true;
    }

    public String getBaseURL()
    {
        return "https://api.telegram.org/bot" + botToken + "/";
    }

    public String get(String uri)
    {
        return call(uri, "GET", "");
    }

    public String post(String uri, String payload)
    {
        return call(uri, "POST", payload);
    }

    public String sendMessage(String text)
    {
        return sendMessage(text, true, "");
    }
    public String sendMessage(String text, String chatID)
    {
        return sendMessage(text, true, chatID);
    }

    public String sendMessage(String text, boolean includeHeaderAndFooter, String customChatID)
    {
        if (includeHeaderAndFooter) {
            text = Configuration.LANG_GENERAL_MESSAGE_HEADER + "\n" + text;
            text += "\n" + Configuration.LANG_GENERAL_MESSAGE_FOOTER;
        }

        text = text.replaceAll("\\\\n", "\n");
        text = text.replaceAll("%n", "\n");
        text = text.strip();

        String groupIdToUse = groupID;
        if (!customChatID.isEmpty()) {
            groupIdToUse = customChatID;
        }

        Telemine.LOGGER.info("Sending a message to the Telegram group: " + text);
        return post("sendMessage", "chat_id=" + groupIdToUse + "&text=" + text);
    }

    public String call(String uri, String method, String payload)
    {
        if (!isValid()) return "ERROR";

        HttpURLConnection connection = null;
        try {
            boolean proxyEnabled = false;
            Proxy proxy = null;
            int intProxyPort = 0;

            if (stringIsEmpty(proxyPort)) {
                intProxyPort = 0;
            } else {
                intProxyPort = Integer.parseInt(proxyPort);
            }

            if (!stringIsEmpty(proxyHost)) {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, intProxyPort));
            }

            URL url = new URL(getBaseURL() + uri);

            if (proxy == null) {
                connection = (HttpURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection(proxy);
            }

            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setConnectTimeout(3000);

            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            if (method.equals("POST")) {
                byte[] input = payload.getBytes("utf-8");
                wr.write(input, 0, input.length);
            }
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
