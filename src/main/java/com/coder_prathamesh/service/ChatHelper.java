package com.coder_prathamesh.service;

import com.coder_prathamesh.dto.StudyPlan;
import com.coder_prathamesh.tools.TimeTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;

public class ChatHelper {

    static StreamingChatModel chatModel = OllamaStreamingChatModel.builder()
            .baseUrl("http://localhost:11434")
            .modelName("llama3.1:latest")
            .temperature(0.7)
            .build();

    static ChatBotAssistant chatBotAssistant = AiServices.builder(ChatBotAssistant.class)
            .streamingChatModel(chatModel)
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
            .tools(
                    new TimeTool()
            )
            .build();

    public static TokenStream getResponse(Integer conversationId, String input) {

        return chatBotAssistant.chatWithBot(conversationId, input);
    }

    public static StudyPlan getStudyPlan(String topic) {

        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.1:latest")
                .temperature(0.7)
                .build();

        StudyPlannerAssistant studyPlannerAssistant = AiServices.create(StudyPlannerAssistant.class, chatModel);
        return studyPlannerAssistant.getStudyPlan(topic);
    }

}
