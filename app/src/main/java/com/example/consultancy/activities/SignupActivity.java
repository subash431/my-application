package com.example.consultancy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.consultancy.R;
import com.example.consultancy.utils.UIUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.btnSignup)
    Button btnSignup;
    @BindView(R.id.btnLogin)
    TextView btnLogin;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    public void CreateUserWithEmail() {
        final String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

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

        dialog = UIUtils.runProgressDialog(this, "Creating your account", "Please wait...");

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("opeth", "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);

                            Toast.makeText(SignupActivity.this, "SignUp Successful with " + username, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(SignupActivity.this, "Error: " + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(SignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignup:
                CreateUserWithEmail();

                break;
            case R.id.btnLogin:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
        }
    }
}
