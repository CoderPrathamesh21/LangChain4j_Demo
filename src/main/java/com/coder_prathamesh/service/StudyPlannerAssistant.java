package com.coder_prathamesh.service;


import com.coder_prathamesh.dto.StudyPlan;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface StudyPlannerAssistant {
    @UserMessage("""
            You are a helpful assistant. Your name is Yama.
            You will answer the user's questions to the best of your ability.
            If you don't know the answer, you will say 'I don't know'.
            You will provide a study plan for the given topic.
            The study plan should be in a table format with the following columns:
            1. Day
            2. Topic
            3. Sub-Topic
            4. Resources (Links to articles, videos, or books)
            5. Time Required (in hours)
            The study plan should be for a duration of 7 days.
            The study plan should be in a markdown format.
            {{topic}}
            """)
    StudyPlan getStudyPlan(@V("topic") String topic);
}
