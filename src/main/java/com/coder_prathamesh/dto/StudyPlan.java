package com.coder_prathamesh.dto;

import dev.langchain4j.model.output.structured.Description;

import java.util.List;

public record StudyPlan(
        @Description("Title of the study plan")
        String planTitle,
        @Description("Number of days for the study plan")
        Integer numberOfDays,
        @Description("Number of hours per day for the study plan")
        Integer numberOfHoursPerDay,
        @Description("List of day-wise plans")
        List<DayPlan> dayPlans
) {
}
