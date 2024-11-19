package com.example.sharedpreferences1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView message;
    Button write, read;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        message = (TextView) findViewById(R.id.textView);
        write = (Button) findViewById(R.id.button);
        read = (Button) findViewById(R.id.button2);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", "test");
                editor.putString("email", "test123@gmail.com");
                editor.commit();
                message.setText("Write Operation Successful!");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("preferences", MODE_PRIVATE);
                String user = sp.getString("username", "");
                String email = sp.getString("email", "");
                message.setText("Username: " +user+ "\nEmail: " +email);
            }
        });

    }
}