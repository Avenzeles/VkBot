package com.example.VkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Event {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("event_id")
    private String eventId;

    @JsonValue
    @JsonProperty("object")
    private EventObject object;

    @JsonProperty("secret")
    private String secret;

}
