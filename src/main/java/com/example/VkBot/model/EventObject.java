package com.example.VkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventObject {

    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    private int date;

    @JsonProperty("out")
    private int out;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("read_state")
    private int readState;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;
}
