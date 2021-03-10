package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.consultancy.R;
import com.example.consultancy.app.App;
import com.example.consultancy.pref.Prefs;
import com.example.consultancy.pref.PrefsManager;
import com.example.consultancy.utils.OnBackPressedUtil;
import com.example.consultancy.utils.UIUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.etUsername)
    MaterialEditText etUsername;
    @BindView(R.id.til1)
    TextInputLayout til1;
    @BindView(R.id.etPassword)
    MaterialEditText etPassword;
    @BindView(R.id.til2)
    TextInputLayout til2;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.rememberMe)
    CheckBox rememberMe;

    private FirebaseAuth mAuth;

    private MaterialDialog dialog;

    int backPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        if (etUsername.getText().toString() != null && etPassword.getText().toString() != null){
            rememberMe.setChecked(false);
        }

        CheckSession();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                SingInWithEmailAndPassword();

                break;
        }
    }

    //Checking for the session
    public void CheckSession() {
        if (PrefsManager.getEmail() != null && PrefsManager.getLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            //Toast.makeText(this, App.prefs.getPrefs().getEmail() + " " + PrefsManager.getRememberMe() + " " + PrefsManager.getPassword(), Toast.LENGTH_SHORT).show();
            if (PrefsManager.getRememberMe()){
                etUsername.setText(PrefsManager.getEmail());
                etPassword.setText(PrefsManager.getPassword());
                rememberMe.setChecked(true);
            }

        }
    }

    //Signing with email and password
    public void SingInWithEmailAndPassword() {
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog = UIUtils.runProgressDialog(this, "Logging", "Please wait...");

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();

                            dialog.dismiss();

                            Toast.makeText(LoginActivity.this, "Login successful",
                                    Toast.LENGTH_LONG).show();

                            //saving into prefs
                            PrefsManager.setEmail(username);
                            PrefsManager.setPassword(password);
                            PrefsManager.setLoggedIn(true);
                            PrefsManager.setUserid(userId);
                            if (rememberMe.isChecked()){
                                PrefsManager.setRememberMe(true);
                            }else{
                                PrefsManager.setRememberMe(false);
                            }

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                            //saving the email to prefs
                            //for receiving notifications
                            // storeDeviceToken();
                        } else {

                            dialog.hide();
                            Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials !",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        backPressed++;
        OnBackPressedUtil onBackPressedUtil = new OnBackPressedUtil(this);
        onBackPressedUtil.onBackPress(backPressed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = 0;
            }
        }, 2000);
    }
}
