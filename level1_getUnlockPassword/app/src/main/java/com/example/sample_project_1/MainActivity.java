package com.example.sample_project_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("AppLockPrefs", MODE_PRIVATE);
        boolean isLocked = prefs.getBoolean("isLocked", false);

        if (isLocked) {
            startActivity(new Intent(MainActivity.this, LockActivity.class));
        }

        Button lockButton = findViewById(R.id.lockButton);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLocked", true);
                editor.apply();

                editor.putString("lock_password", "102030");
                editor.apply();

                startActivity(new Intent(MainActivity.this, LockActivity.class));
            }
        });
    }
}
