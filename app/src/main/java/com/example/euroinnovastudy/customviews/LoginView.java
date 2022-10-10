package com.example.euroinnovastudy.customviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.euroinnovastudy.R;

/**
 * This is a customized view combining different components
 * that already exist in Android to form a composed view, here
 * a login view
 */
public class LoginView extends LinearLayout {
    public TextView loginMsg;
    private EditText userInput;
    private EditText passwordInput;
    private Button loginBtn;

    private OnLoginListener listener;

    public LoginView(Context context) {
        super(context);
        initialization();
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialization();
    }


    private void initialization(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_login, this, true);

        loginBtn = view.findViewById(R.id.loginBtn);
        loginMsg = findViewById(R.id.loginMsg);
        userInput = findViewById(R.id.userInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onLogin(userInput.getText().toString(), passwordInput.getText().toString());
                }
            }
        });
    }

    /**
     * Bind the implemented listener to this login view
     * @param listener
     */
    public void setOnLoginListener(OnLoginListener listener){
        this.listener = listener;
    }

    /**
     * We define a "OnLoginListener" interface here so whoever implements
     * this interface can deal with the specific business logic
     */
    public interface OnLoginListener{
        public void onLogin(String user, String password);
    }

}

