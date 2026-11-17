package com.coder_prathamesh.dto;

public record DayPlan(
        String dayTitle,
        String dayContent,
        String practiceQuestions,
        Integer dayNumber
) {
}
