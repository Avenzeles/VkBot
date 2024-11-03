package com.example.VkBot.services;

import com.example.VkBot.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MessageService {
    private final String CALLBACK_API_CONFIRMATION_TOKEN = "your confirmation token";  // Строка, которую должен вернуть сервер
    private final String VK_API_ACCESS_TOKEN = "your access token";  // Ключ доступа сообщества
    private final String VK_API_SECRET_KEY = "your secret key";   // Секретный ключ
    private final String VK_API_VERSION = "5.199"; // Используемая версия API

    public String ApiCall(@org.jetbrains.annotations.NotNull Event event) throws URISyntaxException, IOException {
        if (!event.getSecret().equals(VK_API_SECRET_KEY)) {
            return "error";
        }

        if (event.getType().equals("confirmation")) {
            return CALLBACK_API_CONFIRMATION_TOKEN;
        }

        if (event.getType().equals("message_new")) {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("message", "Вы написали: " + event.getObject().getMessage().getText());
            requestParams.put("peer_id", String.valueOf(event.getObject().getMessage().getFromId()));
            requestParams.put("access_token", VK_API_ACCESS_TOKEN);
            requestParams.put("v", VK_API_VERSION);
            requestParams.put("random_id", "0");

            StringBuilder getParams = new StringBuilder();
            for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                if (getParams.length() != 0) {
                    getParams.append("&");
                }
                getParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                getParams.append("=");
                getParams.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            }

            String urlString = "https://api.vk.com/method/messages.send?" + getParams.toString();

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
        }

        return "ok";
    }

}
