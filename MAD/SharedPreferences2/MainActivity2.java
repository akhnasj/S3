package com.example.sharedpreferences2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    TextView message;
    Button logout;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        message = (TextView) findViewById(R.id.textView);
        logout = (Button) findViewById(R.id.button2);

        preferences = getSharedPreferences("credentials", MODE_PRIVATE);

        String username = preferences.getString("username", "");
        if(username.isEmpty()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        message.setText("Hello " +username+ " !!");

        logout.setOnClickListener(e -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", "");
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }
}