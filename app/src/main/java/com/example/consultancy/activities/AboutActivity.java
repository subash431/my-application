package com.example.consultancy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultancy.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.btnCallNumber)
    TextView btnCallNumber;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
    @BindView(R.id.imgThree)
    ImageView imgThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Picasso.with(this)
                .load("https://i.ibb.co/y07mKB1/img-two.jpg")
                .fit()
                .into(imgTwo);

        Picasso.with(this)
                .load("https://i.ibb.co/LpDxTzH/img-three.jpg")
                .fit()
                .into(imgThree);

    }
}
