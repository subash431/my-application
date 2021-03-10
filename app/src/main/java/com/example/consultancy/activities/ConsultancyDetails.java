package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultancy.R;
import com.example.consultancy.model.ConsultancyModel;
import com.example.consultancy.model.Country;
import com.example.consultancy.utils.TypeFaceUtil;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultancyDetails extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.carouselView)
    CarouselView carouselView;

    private ConsultancyModel consultancy;
    private int[] sampleImages = {R.drawable.coasta, R.drawable.operahouse, R.drawable.usa_pic, R.drawable.germany_pic};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultancy_details);
        ButterKnife.bind(this);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
*/
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

        consultancy = (ConsultancyModel) getIntent().getSerializableExtra("ConsultancyList");
        tvName.setText(consultancy.getName());
        tvTitle.setText(consultancy.getTitleDescription());
        tvDescription.setText(consultancy.getDescription());
        /*List<String> country = consultancy.getCountries();

        Log.d("Country", String.valueOf(country));*/

        btnApplyNow.setOnClickListener(this);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);

        }

        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnApplyNow) {//Toast.makeText(ConsultancyDetails.this, "Clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConsultancyDetails.this, UniversityActivity.class);

            startActivity(intent);
        }
    }
}
