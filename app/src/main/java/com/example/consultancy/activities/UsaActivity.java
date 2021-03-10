package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.consultancy.R;
import com.example.consultancy.model.University;
import com.example.consultancy.utils.TypeFaceUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsaActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerOptions<University> options;
    private FirebaseRecyclerAdapter<University, UniversityViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usa);
        ButterKnife.bind(this);

        initVars();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetch();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.startListening();
        }
    }

    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void fetch() {
        final ArrayList<University> universityArrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CollegeList")
                .child("Usa");

        options = new FirebaseRecyclerOptions.Builder<University>()
                .setQuery(databaseReference, University.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<University, UniversityViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UniversityViewHolder holder, final int position, @NonNull University model) {
                holder.setTvUniversityName(model.getName());

                universityArrayList.add(model);
                final University university = universityArrayList.get(position);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(UsaActivity.this, UniversityDetails.class);
                        intent.putExtra("UniversityList", university);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.university_list, viewGroup, false);

                return new UniversityViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public class UniversityViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUniversityName;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUniversityName = itemView.findViewById(R.id.tvUniversityName);
        }

        public void setTvUniversityName(String universityName) {
            TypeFaceUtil.nunito_bold(UsaActivity.this, tvUniversityName);
            tvUniversityName.setText(universityName);
        }
    }
}
