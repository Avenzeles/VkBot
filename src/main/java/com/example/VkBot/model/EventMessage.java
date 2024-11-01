package com.example.VkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventMessage {
    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    private int date;

    @JsonProperty("out")
    private int out;

    @JsonProperty("from_id")
    private int peerId;

    @JsonProperty("read_state")
    private int readState;

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    private String text;
}
