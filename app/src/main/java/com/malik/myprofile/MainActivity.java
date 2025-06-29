package com.malik.myprofile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String PREF_DARK_MODE = "dark_mode";

    private ImageView themeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(PREF_DARK_MODE, false);
        setAppTheme(isDarkMode);

        setContentView(R.layout.activity_main);

        Button showMeButton = findViewById(R.id.show_me_button);
        ImageView backgroundImage = findViewById(R.id.background_image);
        themeIcon = findViewById(R.id.theme_icon);

        updateThemeIcon(isDarkMode);


        themeIcon.setOnClickListener(v -> {
            boolean newDarkModeState = !prefs.getBoolean(PREF_DARK_MODE, false);
            prefs.edit().putBoolean(PREF_DARK_MODE, newDarkModeState).apply();
            setAppTheme(newDarkModeState);

            animateThemeIconChange(newDarkModeState);
            recreate();
        });

        showMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileSelectionBottomSheet bottomSheet = new ProfileSelectionBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
            }
        });
    }

    private void setAppTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void updateThemeIcon(boolean isDarkMode) {
        if (themeIcon != null) {
            if (isDarkMode) {
                themeIcon.setImageResource(R.drawable.ic_sun);
            } else {
                themeIcon.setImageResource(R.drawable.ic_moon);
            }
            // Hapus setColorFilter agar warnanya mengikuti tint dari XML
            themeIcon.clearColorFilter();
        }
    }

    private void animateThemeIconChange(boolean newDarkModeState) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(themeIcon, "alpha", 1f, 0f);
        fadeOut.setDuration(150);

        fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                updateThemeIcon(newDarkModeState);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(themeIcon, "alpha", 0f, 1f);
                fadeIn.setDuration(150);
                fadeIn.start();
            }
        });
        fadeOut.start();
    }
}