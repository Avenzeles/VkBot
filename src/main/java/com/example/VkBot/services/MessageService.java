package com.example.VkBot.services;

import com.example.VkBot.model.Event;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


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

    public String ApiCall(Event event) throws URISyntaxException {
        if (!event.getSecret().equals(VK_API_SECRET_KEY)) {
            return "error";
        }

        if (event.getType().equals("confirmation")) {
            return "ba08915b";
        }

        if (event.getType().equals("message_new")) {
            List<NameValuePair> Response = new ArrayList<>();
            Response.add(new BasicNameValuePair("message", "Вы написали: " + event.getObject().getBody()));
            Response.add(new BasicNameValuePair("peer_id", String.valueOf(event.getObject().getUserId())));
            Response.add(new BasicNameValuePair("access_token", VK_API_ACCESS_TOKEN));
            Response.add(new BasicNameValuePair("v", VK_API_VERSION));
            Response.add(new BasicNameValuePair("random_id", String.valueOf(new SecureRandom().nextInt())));

            HttpGet httpGet = new HttpGet(VK_API_ENDPOINT + "messange.send");
            httpGet.setURI(new URIBuilder(httpGet.getURI()).addParameters(Response).build());
        }

        return "ok";
    }

}
