package com.example.VkBot.services;

import com.example.VkBot.model.Event;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MessageService {
    private static final String CALLBACK_API_CONFIRMATION_TOKEN = "ba08915b";  // Строка, которую должен вернуть сервер
    private static final String VK_API_SECRET_KEY = "avenzeles2001";  // Секретный ключ
    private static final String VK_API_ACCESS_TOKEN = "vk1.a.p0_Nel1xHPILedmUXNBlZBMQuB5aKG0tX2wvs554cUYflViOFzJzPFn5L8Goit0Ur-DA8mgBDxvSu8GYZDVQTLSwdLKhCKKH7UUe2qQ_LtL6q5KcqQyqkNdswNwlhhXlYpk-waCiyzPrZ6snDnULYnMi-sJIiuKg0s3RKEt1NRpK9LRcz4bigcKPd29XA7pyk3QVw8wE4XxFzCQg8mjIVw";   // Ключ доступа сообщества
    private static final String CALLBACK_API_EVENT_CONFIRMATION = "confirmation"; // Тип события о подтверждении сервера
    private static final String CALLBACK_API_EVENT_MESSAGE_NEW = "message_new"; // Тип события о новом сообщении
    private static final String VK_API_ENDPOINT = "https://api.vk.com/method/";   // Адрес обращения к API
    private static final String VK_API_VERSION = "5.199"; // Используемая версия API

    public String ApiCall(@org.jetbrains.annotations.NotNull Event event) throws URISyntaxException, IOException {
        if (!event.getSecret().equals(VK_API_SECRET_KEY)) {
            return "error";
        }

        if (event.getType().equals("confirmation")) {
            return "ba08915b";
        }

        if (event.getType().equals("message_new")) {

            System.out.println(event.getType() + " " + event.getGroupId() + " " + event.getSecret());

            System.out.println("Мне написали " + event.getObject().getMessage().getText());

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("message", "Вы написали: " + event.getObject().getMessage().getText());
            requestParams.put("peer_id", String.valueOf(event.getObject().getMessage().getPeerId()));
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

            System.out.println(responseCode);

        }

        return "ok";
    }

}
