package com.example.bankapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText6);
        login = (Button) findViewById(R.id.button2);

        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        String user = sp.getString("username", "");
        String pass = sp.getString("password", "");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString();
                String p = password.getText().toString();

                if (u.isEmpty() || p.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter input fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!u.equals(user) || !p.equals(pass)) {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LoginActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}