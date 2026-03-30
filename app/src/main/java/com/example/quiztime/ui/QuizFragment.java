package com.example.quiztime.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.quiztime.R;
import com.example.quiztime.databinding.FragmentQuizBinding;
import com.example.quiztime.model.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;

    private List<Question> questionList;
    private int currentIndex = 0;

    private int selectedAnswer = -1;
    private boolean isAnswered = false;
    private int score = 0;

    private Button[] optionButtons;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optionButtons = new Button[]{
                binding.btnOption1,
                binding.btnOption2,
                binding.btnOption3,
                binding.btnOption4
        };

        loadQuestions();
        loadQuestion();

        setupOptionClicks();
        setupSubmitNextButton();
    }

    // Loading Static Questions
    private void loadQuestions() {
        questionList = new ArrayList<>();

        questionList.add(new Question(
                "What is Android?",
                Arrays.asList("Language", "IDE", "Database", "OS"),
                3
        ));

        questionList.add(new Question(
                "Which language is used for Android?",
                Arrays.asList("Java", "Kotlin", "Both", "Python"),
                2
        ));

        questionList.add(new Question(
                "What is Activity?",
                Arrays.asList("UI Screen", "Database", "API", "Service"),
                0
        ));

        questionList.add(new Question(
                "Android is based on?",
                Arrays.asList("Windows", "Linux", "Mac", "Unix"),
                1
        ));

        questionList.add(new Question(
                "Which is Jetpack?",
                Arrays.asList("Library", "OS", "Device", "Language"),
                0
        ));
    }

    // Load Question
    private void loadQuestion() {
        Question q = questionList.get(currentIndex);

        binding.tvQuestionDesc.setText(q.getQuestion());

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(q.getOptions().get(i));
            optionButtons[i].setBackgroundResource(R.drawable.option_default_bg);
            optionButtons[i].setEnabled(true);
        }

        selectedAnswer = -1;
        isAnswered = false;

        binding.btnNext.setText(R.string.submit);

        updateProgress();
    }

    // Progress bar
    private void updateProgress() {

        int total = questionList.size();
        int progress = (currentIndex + 1) * 100 / total;

        binding.progressBar.setProgress(progress);
        binding.tvProgressText.setText((currentIndex + 1) + "/" + total);
    }

    // Option Clicks
    private void setupOptionClicks() {
        for (int i = 0; i < optionButtons.length; i++) {
            int index = i;
            optionButtons[i].setOnClickListener(v -> {
                if (!isAnswered) {
                    selectedAnswer = index;

                    // highlight selection
                    resetOptions();
                    optionButtons[index].setBackground(getResources().getDrawable(R.drawable.next_button_bg));
                }
            });
        }
    }

    // submit / next button
    private void setupSubmitNextButton() {

        binding.btnNext.setOnClickListener(v -> {

            if (!isAnswered) {
                checkAnswer();
            } else {
                goToNextQuestion();
            }
        });
    }

    private void checkAnswer() {

        if (selectedAnswer == -1) {
            Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        isAnswered = true;

        Question q = questionList.get(currentIndex);
        int correct = q.getCorrectAnswerIndex();

        // Disable buttons
        for (Button btn : optionButtons) {
            btn.setEnabled(false);
        }

        if (selectedAnswer == correct) {
            optionButtons[correct].setBackgroundResource(R.drawable.option_correct_bg);
            score++;
        } else {
            optionButtons[selectedAnswer].setBackgroundResource(R.drawable.option_wrong_bg);
            optionButtons[correct].setBackgroundResource(R.drawable.option_correct_bg);
        }

        binding.btnNext.setText(R.string.next);
    }

    // Next Question
    private void goToNextQuestion() {

        if (currentIndex < questionList.size() - 1) {
            currentIndex++;
            loadQuestion();
        } else {
            // Go to Result Screen
            navigateToResult();
        }
    }

    // Navigating to Result screen
    private void navigateToResult() {

        ResultFragment fragment = new ResultFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("SCORE", score);
        bundle.putInt("TOTAL", questionList.size());

        fragment.setArguments(bundle);

        ((MainActivity) requireActivity()).loadFragment(fragment);
    }


    private void resetOptions() {
        for (Button btn : optionButtons) {
            btn.setBackgroundResource(R.drawable.option_default_bg);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // avoids memory leak
    }

}
