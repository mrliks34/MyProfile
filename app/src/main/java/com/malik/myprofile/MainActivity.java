package com.malik.myprofile;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showMeButton = findViewById(R.id.show_me_button);

        showMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan BottomSheetDialogFragment
                ProfileSelectionBottomSheet bottomSheet = new ProfileSelectionBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

                // overridePendingTransition tidak lagi diperlukan untuk ini
                // overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out_activity);
            }
        });
    }
}