package com.example.euroinnovastudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.euroinnovastudy.customviews.LoginView;

public class LoginActivity extends AppCompatActivity {

    LoginView loginView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginView = findViewById(R.id.loginView);
        loginView.setOnLoginListener(new LoginView.OnLoginListener() {
            @Override
            public void onLogin(String user, String password) {
                Log.d("LoginActivity", "user name = " + user);
                Log.d("LoginActivity", "password = " + password);

                if(user.equals("hello") && password.equals("hello")){
                    loginView.loginMsg.setText("Correct");
                }
                else{
                    loginView.loginMsg.setText("Wrong");
                }
            }
        });
    }

    public void onNext(View view) {
        Intent intent = new Intent(this, NoughtAndCrossActivity.class);
        startActivity(intent);
    }
}