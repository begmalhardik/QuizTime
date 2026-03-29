package com.example.quiztime.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.quiztime.databinding.FragmentStartQuizBinding;

public class StartQuizFragment extends Fragment {

    private FragmentStartQuizBinding binding;

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

        binding.btnStart.setOnClickListener(c -> {

            String name = binding.etName.getText().toString().trim();

            if (name.isEmpty()) {
                // binding.etName.setError("PLease enter your name");
                binding.tvNameError.setVisibility(View.VISIBLE);
                return;
            } else {
                binding.tvNameError.setVisibility(View.GONE);
            }

            // Pass name to next fragment
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", name);

            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setArguments(bundle);

            ((MainActivity) requireActivity()).loadFragment(quizFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // avoids memory leak
    }

}
