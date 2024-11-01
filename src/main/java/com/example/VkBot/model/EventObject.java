package com.example.VkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventObject {
    @JsonValue
    @JsonProperty("message")
    private EventMessage message;
}
