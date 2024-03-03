package com.example.loginproject_sharman6;

public class QuestionBank {
    //List of questions. SCQ is single answer question. MCQ are multi-choice question
    public static String[][] questions ={
            {"Which of these major city exists in California?", "SCQ"},
            {"Which one is the tallest mountain in the world?", "SCQ"},
            {"What are the two components of a Laptop?", "MCQ"},
            {"What are the two major mobile OS", "MCQ"},
            {"Which one of the option is a retail store", "SCQ"},
            {"Who is the manufacturer of F150 Truck", "SCQ"}
    };
    //list of answers to choice from
    public static String[][] choices = {
            {"Huston","Newark","San Jose","Seatle"},
            {"Everest","K2","Broad Peak","Nanda Devi"},
            {"USB","mouse","pen drive","RAM"},
            {"Windows","iOS","Android","Blackberry"},
            {"Zara","Startbucks","Target","Walgreens"},
            {"Honda","Chevy","Nissan","Ford"}
    };
    //Correct answer for each question
    public static String[] correctAnswers = {
            "San Jose",
            "Everest",
            "USB RAM",
            "iOS Android",
            "Target",
            "Ford"
    };
}
