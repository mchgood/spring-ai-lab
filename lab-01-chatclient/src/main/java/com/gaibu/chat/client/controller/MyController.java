package com.gaibu.chat.client.controller;

import cn.hutool.core.util.RandomUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class MyController {


    @Autowired
    private ChatClient qwenPlusChatClient;
    @Autowired
    private ChatClient qwenMaxChatClient;

    @GetMapping("/ai")
    public String generation(String userInput) {

        return RandomUtil.randomEle(List.of(qwenPlusChatClient, qwenMaxChatClient)).prompt()
                .user(userInput)
                .call()
                .content();
    }

    @GetMapping(
            value = "/ai/stream"
    )
    public Flux<String> generationStream(String userInput) {

        return RandomUtil.randomEle(List.of(qwenPlusChatClient, qwenMaxChatClient)).prompt()
                .user(userInput)
                .stream()
                .content();
    }

    @GetMapping("/ai/returnEntity")
    public ActorFilms returnEntity() {
        return qwenMaxChatClient.prompt()
                .user("Generate the filmography for a random actor.")
                .call()
                .entity(ActorFilms.class);
    }

    @GetMapping("/ai/returnEntityList")
    public List<ActorFilms> returnEntityList() {
        return qwenMaxChatClient.prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {
                });
    }

    public record ActorFilms(String actor, List<String> movies) {
    }
}
