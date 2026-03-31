package com.example.quiztime.viewmodel;

import com.example.quiztime.model.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizRepository {

    public List<Question> getQuestions() {

        List<Question> list = new ArrayList<>();

        list.add(new Question(
                "What is Android?",
                Arrays.asList("Language", "IDE", "Database", "OS"),
                3
        ));

        list.add(new Question(
                "Which language is used for Android?",
                Arrays.asList("Java", "Kotlin", "Both", "Python"),
                2
        ));

        list.add(new Question(
                "What is Activity?",
                Arrays.asList("UI Screen", "Database", "API", "Service"),
                0
        ));

        list.add(new Question(
                "Android is based on?",
                Arrays.asList("Windows", "Linux", "Mac", "Unix"),
                1
        ));

        list.add(new Question(
                "Which is Jetpack?",
                Arrays.asList("Library", "OS", "Device", "Language"),
                0
        ));

        return list;
    }
}
