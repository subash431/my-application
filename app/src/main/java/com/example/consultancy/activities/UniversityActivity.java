package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultancy.R;
import com.example.consultancy.model.Country;
import com.example.consultancy.utils.TypeFaceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.usaLayout)
    LinearLayout usaLayout;
    @BindView(R.id.europeLayout)
    LinearLayout europeLayout;
    @BindView(R.id.tvUsa)
    TextView tvUsa;
    @BindView(R.id.tvEurope)
    TextView tvEurope;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvAus)
    TextView tvAus;
    @BindView(R.id.ausLayout)
    LinearLayout ausLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        ButterKnife.bind(this);

        TypeFaceUtil.nunito_bold(this, tvAus);
        TypeFaceUtil.nunito_bold(this, tvUsa);
        TypeFaceUtil.nunito_bold(this, tvEurope);


        initVars();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* Country country = (Country) getIntent().getSerializableExtra("Country");
        Toast.makeText(UniversityActivity.this, country.getName(), Toast.LENGTH_LONG).show();*/
    }

    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);
        }



        ausLayout.setOnClickListener(this);
        usaLayout.setOnClickListener(this);
        europeLayout.setOnClickListener(this);
        /*recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ausLayout:
                Intent intent = new Intent(UniversityActivity.this, AustraliaActivity.class);
                startActivity(intent);
                break;
            case R.id.usaLayout:
                Intent usaIntent = new Intent(UniversityActivity.this, UsaActivity.class);
                startActivity(usaIntent);
                break;
            case R.id.europeLayout:
                Intent europeIntent = new Intent(UniversityActivity.this, NewZealandActivity.class);
                startActivity(europeIntent);
        }
    }

}
