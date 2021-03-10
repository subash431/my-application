package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.consultancy.R;
import com.example.consultancy.model.University;
import com.example.consultancy.utils.TypeFaceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityDetails extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.btnApplyNow)
    Button btnApplyNow;
    private   University university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_details);
        ButterKnife.bind(this);

        TypeFaceUtil.nunito_bold(this, tvName);
        TypeFaceUtil.nunito_light(this, tvTitle);
        TypeFaceUtil.nunito_regular(this, tvDescription);

        initVars();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        university = (University) getIntent().getSerializableExtra("UniversityList");

        tvName.setText(university.getName());
        tvTitle.setText(university.getTitle());
        tvDescription.setText(university.getDescription());

        btnApplyNow.setOnClickListener(this);
    }

    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);

        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnApplyNow) {
            Intent intent = new Intent(UniversityDetails.this, ApplyFormActivity.class);
            intent.putExtra("University", university);
            startActivity(intent);
        }
    }
}
