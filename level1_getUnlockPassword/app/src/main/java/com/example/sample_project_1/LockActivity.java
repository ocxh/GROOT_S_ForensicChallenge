package com.example.sample_project_1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        final EditText pinEditText = findViewById(R.id.pinEditText);
        Button unlockButton = findViewById(R.id.unlockButton);

        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredPin = pinEditText.getText().toString();
                SharedPreferences prefs = getSharedPreferences("AppLockPrefs", MODE_PRIVATE);
                String password = prefs.getString("lock_password", "null");
                if (password.equals(enteredPin)) {  // 임시로 설정한 PIN
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isLocked", false);
                    editor.apply();

                    finish();
                } else {
                    Toast.makeText(LockActivity.this, "잘못된 PIN", Toast.LENGTH_SHORT).show();
                    pinEditText.setText("");
                }
            }
        });
    }
}

