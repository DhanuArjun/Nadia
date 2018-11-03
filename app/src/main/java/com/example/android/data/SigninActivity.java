package com.example.android.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*Login screen to log in to the app */
public class SigninActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";

    //references to textView and EditText to email and password
    private TextView mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        mEmailView = (TextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSighInButtion = (Button) findViewById(R.id.sign_in_button);

        mEmailSighInButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }

    private void attemptLogin(){

        //for error handles
        mEmailView.setError(null);
        mPasswordView.setError(null);

        //store the email and password in the local variable
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //check password has not empty and at least 4 characters
        if(!TextUtils.isEmpty(password) && !isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        //check email is valid
        if (!isEmailValid(email)){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(email)) {
                mEmailView.setError(getString(R.string.error_invalid_email));
                focusView = mEmailView;
                cancel = true;
        }
        if(cancel){
            focusView.requestFocus();
        }else {
            getIntent().putExtra(EMAIL_KEY, email);
            setResult(RESULT_OK, getIntent());
            finish();
        }



    }
    //this function check email validation
    private  boolean isEmailValid(String email){
        return email.contains("@");
    }

    //check password has 4 characters
    private boolean isPasswordValid(String password){
        return password.length() > 4;
    }

}
