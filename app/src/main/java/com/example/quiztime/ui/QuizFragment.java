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
import androidx.lifecycle.ViewModelProvider;

import com.example.quiztime.R;
import com.example.quiztime.databinding.FragmentQuizBinding;
import com.example.quiztime.model.Question;
import com.example.quiztime.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;
    private QuizViewModel viewModel;
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

        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        optionButtons = new Button[]{
                binding.btnOption1,
                binding.btnOption2,
                binding.btnOption3,
                binding.btnOption4
        };
        
        observeViewModel();
        setupClicks();
    }

    private void observeViewModel() {

        viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), q -> {

            binding.tvQuestionDesc.setText(q.getQuestion());

            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(q.getOptions().get(i));
                optionButtons[i].setBackgroundResource(R.drawable.option_default_bg);
                optionButtons[i].setEnabled(true);
            }

            binding.btnNext.setText(R.string.submit);

            updateProgress();
        });

        viewModel.getIsAnswered().observe(getViewLifecycleOwner(), answered -> {

            if (answered) {

                int correct = viewModel.getCorrectAnswer();
                int selected = viewModel.getSelectedAnswer();

                for (Button btn : optionButtons) {
                    btn.setEnabled(false);
                }

                optionButtons[correct].setBackgroundResource(R.drawable.option_correct_bg);

                if (selected != correct) {
                    optionButtons[selected].setBackgroundResource(R.drawable.option_wrong_bg);
                }

                binding.btnNext.setText(R.string.next);
            }
        });

        viewModel.isQuizFinished().observe(getViewLifecycleOwner(), finished -> {
            if (finished) navigateToResult();
        });
    }

    private void setupClicks() {
        for (int i = 0; i < optionButtons.length; i++) {
            int index = i;

            optionButtons[i].setOnClickListener(v -> {
                viewModel.selectAnswer(index);

                resetOptions();
                optionButtons[index].setBackgroundResource(R.drawable.next_button_bg);
            });
        }

        binding.btnNext.setOnClickListener(v -> {

            if (!Boolean.TRUE.equals(viewModel.getIsAnswered().getValue())) {
                viewModel.submitAnswer();
            } else {
                viewModel.nextQuestion();
            }
        });
    }

    // Progress bar
    private void updateProgress() {

        int index = viewModel.getQuestionIndex().getValue();
        int total = viewModel.getTotalQuestions();
        int progress = (index + 1) * 100 / total;

        binding.progressBar.setProgress(progress);
        binding.tvProgressText.setText((index + 1) + "/" + total);
    }


    // Navigating to Result screen
    private void navigateToResult() {

        ResultFragment fragment = new ResultFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("SCORE", viewModel.getScore().getValue());
        bundle.putInt("TOTAL", viewModel.getTotalQuestions());

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
