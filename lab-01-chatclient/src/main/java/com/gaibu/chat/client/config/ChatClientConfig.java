package com.gaibu.chat.client.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient qwenPlusChatClient(OpenAiChatModel chatModel) {

        return ChatClient.create(chatModel
                .mutate()
                .defaultOptions(OpenAiChatOptions
                        .builder()
                        .model("qwen-plus")
                        .build())
                .build()
        );
    }

    @Bean
    public ChatClient qwenMaxChatClient(OpenAiChatModel chatModel) {

        return ChatClient.create(chatModel
                .mutate()
                .defaultOptions(OpenAiChatOptions
                        .builder()
                        .model("qwen-max")
                        .build())
                .build()
        );
    }

}
