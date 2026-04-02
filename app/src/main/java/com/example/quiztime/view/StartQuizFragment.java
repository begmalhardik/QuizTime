package com.example.quiztime.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quiztime.databinding.FragmentStartQuizBinding;
import com.example.quiztime.viewmodel.QuizViewModel;

public class StartQuizFragment extends Fragment {

    private FragmentStartQuizBinding binding;

    private QuizViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        binding = FragmentStartQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.isEmpty()) {
                binding.etName.setText(name);
            }
        });

        binding.etName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(!(s.toString().isEmpty()) || !(s.toString().isBlank())) {
                    binding.tvNameError.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        binding.btnStart.setOnClickListener(c -> {

            String name = binding.etName.getText().toString().trim();

            if (name.isEmpty()) {
                // binding.etName.setError("PLease enter your name");
                binding.tvNameError.setVisibility(View.VISIBLE);
                return;
            }

            // Pass name to next fragment
            viewModel.setUserName(name);
            QuizFragment quizFragment = new QuizFragment();

            ((MainActivity) requireActivity()).loadFragment(quizFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // avoids memory leak
    }

}
