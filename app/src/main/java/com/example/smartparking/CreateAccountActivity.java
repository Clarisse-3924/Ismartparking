package com.example.smartparking;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.smartparking.models.RegisterRequest;
import com.example.smartparking.models.RegisterResponse;
import com.example.smartparking.services.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.createUserButton)
    Button mCreateUserButton;
    @BindView(R.id.nameEditText)
    EditText mNameEditText;
    @BindView(R.id.emailEditText)
    EditText mEmailEditText;
    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText)
    EditText mConfirmPasswordEditText;
    @BindView(R.id.loginTextView)
    TextView mLoginTextView;
    @BindView(R.id.fname)
    EditText mfname;
    @BindView(R.id.lname)
    EditText mlname;

    private ProgressDialog mAuthProgressDialog;
    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        createAuthProgressDialog();
        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);


        //validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        // add validations
        awesomeValidation.addValidation(this,R.id.nameEditText, RegexTemplate.NOT_EMPTY,R.string.invalid_names);
        awesomeValidation.addValidation(this,R.id.emailEditText, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.fname, RegexTemplate.NOT_EMPTY,R.string.invalid_names);
        awesomeValidation.addValidation(this,R.id.lname, RegexTemplate.NOT_EMPTY,R.string.invalid_names);
        String regexPassword = ".{3,}";
        awesomeValidation.addValidation(this, R.id.passwordEditText, regexPassword, R.string.week_passwor);
        awesomeValidation.addValidation(this, R.id.confirmPasswordEditText, R.id.passwordEditText, R.string.invalid_confirm_password);





        mCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate() ) {
                    String username = mNameEditText.getText().toString();
                    String password =  mPasswordEditText.getText().toString();
                    String firstName=mfname.getText().toString();
                    String lastName=mlname.getText().toString();

                    RegisterRequest registerRequest = new RegisterRequest(username,password,firstName,lastName );

                    registerUser(registerRequest);
                }else{
                    String message = "Registration failed!";
                    Toast.makeText(CreateAccountActivity.this,message,Toast.LENGTH_SHORT).show();
                }

            }
        });
        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createNewUser() {
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;


    }

    @Override
    public void onClick(View view) {

        if (view == mCreateUserButton) {
            createNewUser();
        }

    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Please wait while we are checking......");
        mAuthProgressDialog.setCancelable(false);
    }
    public void registerUser(RegisterRequest registerRequest) {
        mAuthProgressDialog.show();
        Call<RegisterResponse> registerResponseCall= ApiClient.getService().RegisterUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    //RegisterResponse registerResponse = response.body();
                    String msg = "Success";
                    Toast.makeText(CreateAccountActivity.this,msg,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateAccountActivity.this,LoginActivity.class);
                    startActivity(intent);

                }else {
                    String msg = "failed";
                    Toast.makeText(CreateAccountActivity.this,msg,Toast.LENGTH_LONG).show();
                    mAuthProgressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(CreateAccountActivity.this,message,Toast.LENGTH_LONG).show();
                mAuthProgressDialog.dismiss();

            }
        });

    }



}