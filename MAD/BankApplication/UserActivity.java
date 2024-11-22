package com.example.bankapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {

    EditText amount;
    TextView message;
    Button withdraw, deposit, logout;
    SharedPreferences sp;
    Integer balance, cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        amount = (EditText) findViewById(R.id.editText7);
        message = (TextView) findViewById(R.id.textView2);
        withdraw = (Button) findViewById(R.id.button3);
        deposit = (Button) findViewById(R.id.button4);
        logout = (Button) findViewById(R.id.button5);

        sp = getSharedPreferences("credentials", MODE_PRIVATE);
        balance = Integer.parseInt(sp.getString("balance", ""));
        message.setText("Balance : " +balance);

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash = Integer.parseInt(amount.getText().toString());
                if (balance - cash < 1000){
                    Toast.makeText(UserActivity.this, "Cannot Withdraw! \nMinimum Balance should be 1000", Toast.LENGTH_SHORT).show();
                    return;
                }

                balance -= cash;

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("balance", balance.toString());
                editor.apply();

                message.setText("Balance : " +balance);
                Toast.makeText(UserActivity.this, "Successfully Withdrawn!", Toast.LENGTH_SHORT).show();
            }
        });

        deposit.setOnClickListener(v -> {
            cash = Integer.parseInt(amount.getText().toString());
            balance += cash;

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("balance", balance.toString());
            editor.apply();

            message.setText("Balance : " +balance);
            Toast.makeText(this, "Successfully Deposited!", Toast.LENGTH_SHORT).show();
        });

        logout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", "");
            editor.putString("password", "");
            editor.putString("balance", "");
            editor.apply();

            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}