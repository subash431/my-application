package com.example.consultancy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.consultancy.R;
import com.example.consultancy.model.FormData;
import com.example.consultancy.model.University;
import com.example.consultancy.model.Upload;
import com.example.consultancy.utils.Constants;
import com.example.consultancy.utils.UIUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyFormActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etFirstName)
    MaterialEditText etFirstName;
    @BindView(R.id.til1)
    TextInputLayout til1;
    @BindView(R.id.etLastName)
    MaterialEditText etLastName;
    @BindView(R.id.til2)
    TextInputLayout til2;
    @BindView(R.id.til3)
    TextInputLayout til3;
    @BindView(R.id.txtEmail)
    MaterialEditText txtEmail;
    @BindView(R.id.til4)
    TextInputLayout til4;
    @BindView(R.id.txtNationality)
    MaterialEditText txtNationality;
    @BindView(R.id.til5)
    TextInputLayout til5;
    @BindView(R.id.spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.btnApply)
    Button btnApply;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.etDOB)
    MaterialEditText etDOB;
    @BindView(R.id.radioMale)
    RadioButton radioMale;
    @BindView(R.id.radioFemale)
    RadioButton radioFemale;
    @BindView(R.id.radio)
    RadioGroup radio;
    @BindView(R.id.editTextFileName)
    EditText editTextFileName;
    @BindView(R.id.buttonUploadFile)
    Button buttonUploadFile;

    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 1;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.textViewStatus)
    TextView textViewStatus;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String gender;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    private MaterialDialog dialog;
    private StorageReference mStorageReference;
    private DatabaseReference mmDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_form);
        ButterKnife.bind(this);

        initVars();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mmDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        buttonUploadFile.setOnClickListener(this);
        btnApply.setOnClickListener(this);

    }

    private void getPDF() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        //creating an intent for file chooser
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadFile(data.getData());
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data) {
        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Upload upload = new Upload(editTextFileName.getText().toString(), taskSnapshot.getUploadSessionUri().toString());
                        mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });

    }


    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);

        }


        University university = (University) getIntent().getSerializableExtra("University");

        String universityName = university.getName();

        // Spinner Drop down elements
        List<String> universities = new ArrayList<String>();
        universities.add(universityName);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, universities);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        dialog = UIUtils.runProgressDialog(this, "Applying", "Please wait...");
       switch (id){
           case R.id.btnApply:

            int selectedId = radio.getCheckedRadioButtonId();

            radioButton = findViewById(selectedId);

            String university = spinner.getSelectedItem().toString();

            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String dOB = etDOB.getText().toString();
            String gender = radioButton.getText().toString();
            String email = txtEmail.getText().toString();
            String nationality = txtNationality.getText().toString();

            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(dOB) || TextUtils.isEmpty(gender)
                    || TextUtils.isEmpty(email) || TextUtils.isEmpty(nationality)) {
                Toast.makeText(ApplyFormActivity.this, "Please fill the required fields", Toast.LENGTH_LONG).show();
            } else {
                FormData formData = new FormData(
                        firstName,
                        lastName,
                        dOB,
                        gender,
                        email,
                        nationality,
                        university
                );

                String userId = mDatabaseReference.push().getKey();

                mDatabaseReference = mDatabase.getReference().child("AppliedList").child(userId);
                mDatabaseReference.setValue(formData);

                Snackbar snackbar = Snackbar
                        .make(linear, "Thank you for choosing us", Snackbar.LENGTH_LONG);
                snackbar.show();


                txtEmail.setText("");
                txtNationality.setText("");
                etDOB.setText("");
                etFirstName.setText("");
                etLastName.setText("");
            }
            break;
           case R.id.buttonUploadFile:
               getPDF();
               break;
        }
        dialog.dismiss();
    }
}
