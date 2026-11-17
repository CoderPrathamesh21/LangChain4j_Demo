package com.coder_prathamesh.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface ChatBotAssistant {

    @SystemMessage(fromResource = "ai-system-prompt.txt")
    public TokenStream chatWithBot(@MemoryId Integer memoryId, @UserMessage String userInput);
}
