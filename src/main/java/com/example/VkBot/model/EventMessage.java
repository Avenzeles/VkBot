package com.example.VkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventMessage {
    @JsonProperty("date")
    private int date;

    @JsonProperty("from_id")
    private int fromId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("version")
    private int version;

    @JsonProperty("out")
    private int out;

    @JsonProperty("text")
    private String text;
}
