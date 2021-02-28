//package com.example.smartparking;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
//    @BindView(R.id.createUserButton)
//    Button mCreateUserButton;
//    @BindView(R.id.nameEditText)
//    EditText mNameEditText;
//    @BindView(R.id.emailEditText)
//    EditText mEmailEditText;
//    @BindView(R.id.passwordEditText)
//    EditText mPasswordEditText;
//    @BindView(R.id.confirmPasswordEditText)
//    EditText mConfirmPasswordEditText;
//    @BindView(R.id.loginTextView)
//    TextView mLoginTextView;
//    public static final String TAG = CreateAccountActivity.class.getSimpleName();
//    private ProgressDialog mAuthProgressDialog;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_account);
//        ButterKnife.bind(this);
////        createAuthProgressDialog();
//        mLoginTextView.setOnClickListener(this);
//        mCreateUserButton.setOnClickListener(this);
//    }
//
////    private void createAuthProgressDialog() {
////        mAuthProgressDialog = new ProgressDialog(this);
////        mAuthProgressDialog.setTitle("Loading...");
////        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
////        mAuthProgressDialog.setCancelable(false);
////    }
//
//    private void createNewUser() {
//        final String name = mNameEditText.getText().toString().trim();
//        final String email = mEmailEditText.getText().toString().trim();
//        String password = mPasswordEditText.getText().toString().trim();
//        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
//        boolean validEmail = isValidEmail(email);
//        boolean validName = isValidName(name);
//        boolean validPassword = isValidPassword(password, confirmPassword);
//        if (!validEmail || !validName || !validPassword) return;
//
//
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        if (view == mCreateUserButton) {
//            createNewUser();
//        }
//
//    }
//
//    private boolean isValidEmail(String email) {
//        boolean isGoodEmail =
//                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
//        if (!isGoodEmail) {
//            mEmailEditText.setError("Please enter a valid email address");
//            return false;
//        }
//        return isGoodEmail;
//    }
//
//    private boolean isValidName(String name) {
//        if (name.equals("")) {
//            mNameEditText.setError("Please enter your name");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isValidPassword(String password, String confirmPassword) {
//        if (password.length() < 6) {
//            mPasswordEditText.setError("Please create a password containing at least 6 characters");
//            return false;
//        } else if (!password.equals(confirmPassword)) {
//            mPasswordEditText.setError("Passwords do not match");
//            return false;
//        }
//        return true;
//    }
//}