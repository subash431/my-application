package com.example.consultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.consultancy.R;
import com.example.consultancy.app.App;
import com.example.consultancy.db.AppDatabase;
import com.example.consultancy.model.Consultancy;
import com.example.consultancy.model.ConsultancyModel;
import com.example.consultancy.pref.PrefsManager;
import com.example.consultancy.utils.TypeFaceUtil;
import com.example.consultancy.utils.UIUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tvUserEmail)
    TextView tvUserEmail;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.top)
    LinearLayout top;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private MaterialDialog dialog;
    private AppDatabase db;
    private FirebaseRecyclerAdapter<ConsultancyModel, ConsultancyViewHolder> adapter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initVars();

        ArrayList<Consultancy> consultancyArrayList = (ArrayList<Consultancy>) db.consultancyDao().getAllConsultancy();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        nav.setNavigationItemSelectedListener(this);
        //adapter = new ConsultancyAdapter(this, consultancyArrayList);
        //recyclerView.setAdapter(adapter);

        //Toast.makeText(this, App.prefs.getPrefs().getEmail() + " " + PrefsManager.getRememberMe() + " " + PrefsManager.getPassword(), Toast.LENGTH_SHORT).show();
    }

    private void initVars() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        db = AppDatabase.getInstance(this);

        tvUserEmail.setText(App.prefs.getPrefs().getEmail());

        fetch();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btn_logout) {
            PrefsManager.clearPrefs();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.startListening();
        }
    }


    private void fetch() {
        dialog = UIUtils.runProgressDialog(this, "Loading", "Please wait...");
        dialog.show();

        final ArrayList<ConsultancyModel> consultancyModelArrayList = new ArrayList<>();

        //private ConsultancyAdapter adapter;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ConsultancyList");

        FirebaseRecyclerOptions<ConsultancyModel> options = new FirebaseRecyclerOptions.Builder<ConsultancyModel>()
                .setQuery(databaseReference, ConsultancyModel.class).build();

        adapter = new FirebaseRecyclerAdapter<ConsultancyModel, ConsultancyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ConsultancyViewHolder holder, int position, @NonNull ConsultancyModel model) {
                dialog.dismiss();
                holder.setTvAddress(model.getAddress());
                holder.setTvPhoneNumber(model.getPhoneNumber());
                holder.setTvConsultancyName(model.getName());

                consultancyModelArrayList.add(model);

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        ConsultancyModel consultancyModel = consultancyModelArrayList.get(pos);
                        Intent intent = new Intent(MainActivity.this, ConsultancyDetails.class);
                        intent.putExtra("ConsultancyList", consultancyModel);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ConsultancyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.consultancy_list, viewGroup, false);

                return new ConsultancyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.about:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.document:
                Intent intent2 = new Intent(MainActivity.this, DocumentDetailActivity.class);
                startActivity(intent2);
                break;
        }

        drawerLayout.closeDrawer(Gravity.START);
        return false;
    }

    public class ConsultancyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvConsultancyName, tvAddress, tvPhoneNumber;
        View mView;

        ConsultancyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

        }

        void setTvConsultancyName(String consultancyName) {
            tvConsultancyName = mView.findViewById(R.id.tvConsultancyName);
            tvConsultancyName.setText(consultancyName);
            TypeFaceUtil.nunito_bold(MainActivity.this, tvConsultancyName);
        }

        void setTvAddress(String address) {
            tvAddress = mView.findViewById(R.id.tvAddress);
            tvAddress.setText(address);
        }

        void setTvPhoneNumber(String phoneNumber) {
            tvPhoneNumber = mView.findViewById(R.id.tvPhoneNumber);
            tvPhoneNumber.setText(phoneNumber);
        }


    }
}
