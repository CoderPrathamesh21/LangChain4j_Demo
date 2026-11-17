package com.coder_prathamesh.tools;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalTime;

public class TimeTool {

    @Tool(name = "Get Current Date and Time")
    public static String getCurrentDateTime() {
        return LocalTime.now().toString();
    }

}
