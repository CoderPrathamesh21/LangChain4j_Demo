package com.coder_prathamesh;

import com.coder_prathamesh.dto.DayPlan;
import com.coder_prathamesh.dto.StudyPlan;
import com.coder_prathamesh.service.ChatHelper;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.TokenStream;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class App
{
    public static void main( String[] args )
    {
        IO.println("--------Welcome to ChatBot---------");
        IO.println("Start conversation with ChatBot...");
        IO.println("Press 1 to create a study plan for a specific topic");
        IO.println("""
                Press 'quit' to exit the conversation""");

        int conversationId = new Random().nextInt(1000, 9999);
        IO.println("Conversation ID is: " + conversationId);

        IO.println("");

        while (true) {
            String readln = IO.readln("You:");

            if (readln.trim().equalsIgnoreCase("quit")) {
                IO.println("Exiting the ChatBot...");
                IO.println("ChatBot: Bye! Have a nice day.");
                break;
            }

            if ((readln.trim()).equals("1")) {
                IO.println("ChatBot: Please enter the topic for which you want to create a study plan.");
                String topic = IO.readln();
                StudyPlan studyPlan = ChatHelper.getStudyPlan(topic);
                IO.println("ChatBot: Here is your study plan for the topic '" + studyPlan.planTitle() + "':");
                IO.println("number of days: " + studyPlan.numberOfDays());
                IO.println("number of hours per day: " + studyPlan.numberOfHoursPerDay());

                IO.println("Day-wise plan:");
                for (DayPlan dayPlan : studyPlan.dayPlans()) {
                    IO.println("Day " + dayPlan.dayNumber() + ": " + dayPlan.dayTitle());
                    IO.println("Content: " + dayPlan.dayContent());
                    IO.println("Practice Questions: " + dayPlan.practiceQuestions());
                    IO.println("++++++++++++++++++++++++++++++++++");
                }

                IO.println("---------------------------------");
                continue;
            }
            TokenStream tokenStream = ChatHelper.getResponse(conversationId, readln);
            CompletableFuture<ChatResponse> futureResponse = new CompletableFuture<>();

            tokenStream.onCompleteResponse(response -> {
                IO.println();
                futureResponse.complete(response);
            })
                    .onPartialResponse(
                            IO::print
                    )
                    .onError(
                            error -> {
                                IO.println("Error occurred while processing the response: " + error.getMessage());
                                futureResponse.completeExceptionally(error);
                            }
                    )
            .start();
            futureResponse.join();
        }
    }
}
