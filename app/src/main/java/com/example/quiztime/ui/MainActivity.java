package com.example.quiztime.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.quiztime.R;
import com.example.quiztime.databinding.ActivityMainBinding;

/*
    GIT updated style of pushing code

    feat(security): add AES encryption
    feat(auth): implement biometric login
    refactor(data): clean repository layer
    fix(encryption): resolve key generation bug
    docs: update security architecture

    we need to work on result screen
    basic app testing
    refactoring code
*/

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null) {
            loadFragment(new StartQuizFragment());
        }
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .commit();
    }
}