package com.groot_s.level2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

                editor.putString("lock_password", "c0034605ea413370d5ad022b8d2f7fe33461bf6d7e5f4ac78f02c27b793673c9");
                editor.apply();

                startActivity(new Intent(MainActivity.this, LockActivity.class));
            }
        });
    }
}