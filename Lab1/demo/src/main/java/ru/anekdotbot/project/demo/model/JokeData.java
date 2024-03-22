package ru.anekdotbot.project.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JokeData {
    private int id;
    private String body;
}
