package com.devaconsultancy.fin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.devaconsultancy.fin.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin;


    private TextInputEditText etPassword;
    private TextInputEditText etUsername;

    private TextInputLayout tpUsernameError;
    private TextInputLayout tpUserPasswordError;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    ".{7,}" +               //at least 7 characters
                    "$");

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^" + ".{10,}" +               //at least 10 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //To set all the UI components
        setupUI();
        //To set all the onClick events
        setonClickEvents();
    }


    /**
     * This method initialise the UI components of the fragment
     */
    public void setupUI() {
        btnLogin = (MaterialButton) findViewById(R.id.btn_login);


        etUsername = (TextInputEditText) findViewById(R.id.et_username);
        etPassword = (TextInputEditText) findViewById(R.id.et_enter_password);

        tpUsernameError = (TextInputLayout) findViewById(R.id.tp_username_error);
        tpUserPasswordError = (TextInputLayout) findViewById(R.id.tp_user_password_error);

    }


    /**
     * This method sets all the onClick events
     */
    public void setonClickEvents() {
        //This is the text change listener event of etUsername
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String username = etUsername.getText().toString();
                //To validate username
                validateUsername(username);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //This is the text change listener event of etPassword
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = etPassword.getText().toString();
                //To validate password
                validatePassword(password);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //This is the onClick event of btnLogin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To authenticate user
                authenticateUser();
            }
        });
    }

    /**
     * This method validates the username
     *
     * @param username
     */
    public void validateUsername(String username) {
        // if username field is empty
        if (TextUtils.isEmpty(username)) {
            //sets error
            tpUsernameError.setError("Username can't be empty");
        } // if username pattern doesn't matches
        else if (username.length() < 10) {
            //sets error
            tpUsernameError.setError("Username must be 10 characters");
        } else {
            //sets error as null
            tpUsernameError.setError(null);
        }
    }

    /**
     * This method validates the password
     *
     * @param password
     */
    public void validatePassword(String password) {

        // if password field is empty
        if (TextUtils.isEmpty(password)) {
            //sets error
            tpUserPasswordError.setError("Password can't be empty");
        } // if password pattern doesn't matches
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            //sets error
            tpUserPasswordError.setError("Password must be 7 characters with 1 uppercase," +
                    "1 special character and 1 digit");
        } else {
            //sets error as null
            tpUserPasswordError.setError(null);
        }
    }

    /**
     * This method authenticate user enter details
     */
    public void authenticateUser() {

        if (etUsername.getText().toString().equals("Fininfocom") && etPassword.getText().toString().equals("Fin@123")) {
            //To move to user details activity
            moveToUserDetailsActivity();
        } else {
            //sets error
            tpUserPasswordError.setError("Please enter valid username and password");
        }
    }

    /**
     * This method moves user to user details activity
     */
    public void moveToUserDetailsActivity() {
        Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
        startActivity(intent);
        finish();
    }
}
