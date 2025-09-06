package com.gaibu.chat.client.controller;

import cn.hutool.core.util.RandomUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {


    @Autowired
    private ChatClient qwenPlusChatClient;
    @Autowired
    private ChatClient qwenMaxChatClient;

    @GetMapping("/ai")
    String generation(String userInput) {

        return RandomUtil.randomEle(List.of(qwenPlusChatClient, qwenMaxChatClient)).prompt()
                .user(userInput)
                .call()
                .content();
    }
}
