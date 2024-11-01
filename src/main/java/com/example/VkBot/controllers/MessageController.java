package com.example.VkBot.controllers;

import com.example.VkBot.model.Event;
import com.example.VkBot.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping
    public String ApiCall(@RequestBody Event event) throws URISyntaxException, IOException {
        return service.ApiCall(event);
    }

}
