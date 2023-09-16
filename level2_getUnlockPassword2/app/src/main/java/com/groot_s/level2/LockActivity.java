package com.groot_s.level2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                String sha256String = "";

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
                    StringBuilder hexString = new StringBuilder();

                    for (byte b : hash) {
                        String hex = Integer.toHexString(0xff & b);
                        if (hex.length() == 1) {
                            hexString.append('0');
                        }
                        hexString.append(hex);
                    }

                    sha256String = hexString.toString();

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                SharedPreferences prefs = getSharedPreferences("AppLockPrefs", MODE_PRIVATE);
                String password = prefs.getString("lock_password", "null");
                if (password.equals(sha256String)) {
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