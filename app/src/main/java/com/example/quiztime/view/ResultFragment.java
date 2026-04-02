package com.example.quiztime.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.quiztime.databinding.FragmentResultBinding;
import com.example.quiztime.viewmodel.QuizViewModel;

public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;

    private QuizViewModel viewModel;
    private int score;
    private int total;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        getDataFromBundle();
        setupUI();
        setupClicks();
    }

    private void getDataFromBundle() {
        if (getArguments() != null) {
            score = getArguments().getInt("SCORE", 0);
            total = getArguments().getInt("TOTAL", 0);
        }
    }

    private void setupUI() {

        binding.tvScoreValue.setText(String.format("%d / %d", score, total));
        binding.tvCongratulations.setText("Congratulations, " + viewModel.getUserName().getValue() + "!");

    }

    private void setupClicks() {

        // Restart Quiz
        binding.btnRestart.setOnClickListener(v -> {

            FragmentManager fm = requireActivity().getSupportFragmentManager();

            // Clear entire back stack
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            viewModel.resetQuiz();
            ((MainActivity) requireActivity()).loadFragment(new StartQuizFragment());
        });

        // Exit App (optional)
        binding.btnFinish.setOnClickListener(v -> {
            requireActivity().finish();
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // avoids memory leak
    }
}