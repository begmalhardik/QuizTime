package com.example.quiztime.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.quiztime.databinding.ActivityMainBinding;
import com.example.quiztime.util.ThemeManager;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyTheme(this); // MUST BE FIRST
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupThemeToggle();

        if(savedInstanceState == null) {
            loadFragment(new StartQuizFragment());
        }
    }

    private void setupThemeToggle() {

        boolean isDark = ThemeManager.isDarkMode(this);
        binding.switchTheme.setChecked(isDark);

        binding.switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ThemeManager.saveTheme(this, isChecked);
        });
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}