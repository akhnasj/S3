package com.example.intentimplicit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText urlInput, telInput, email, subject, content, textInput;
    Button web, dial, mail, text;
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

        urlInput = (EditText) findViewById(R.id.editText);
        telInput = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        subject = (EditText) findViewById(R.id.editText4);
        content = (EditText) findViewById(R.id.editText5);
        textInput = (EditText) findViewById(R.id.editText6);

        web = (Button) findViewById(R.id.button);
        dial = (Button) findViewById(R.id.button2);
        mail = (Button) findViewById(R.id.button3);
        text = (Button) findViewById(R.id.button4);

        web.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(urlInput.getText().toString())
            );
            startActivity(intent);
        });

        dial.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + telInput.getText())
            );
            startActivity(intent);
        });

        mail.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("mailto:" + email.getText())
            );
            intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());
            startActivity(intent);
        });

        text.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, textInput.getText().toString());
            startActivity(Intent.createChooser(intent,"Share with"));
        });
    }
}