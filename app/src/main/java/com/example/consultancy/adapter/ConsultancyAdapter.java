package com.example.consultancy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.consultancy.R;
import com.example.consultancy.activities.ConsultancyDetails;
import com.example.consultancy.db.AppDatabase;
import com.example.consultancy.model.Consultancy;

import java.util.ArrayList;

public class ConsultancyAdapter extends RecyclerView.Adapter<ConsultancyAdapter.ConsultancyViewHolder> {

    Context context;
    ArrayList<Consultancy> consultancyArrayList;
    LayoutInflater inflater;
    private Consultancy consultancy;
    private AppDatabase db;

    public ConsultancyAdapter(Context context, ArrayList<Consultancy> arrayList){
        this.context = context;
        this.consultancyArrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ConsultancyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = inflater.inflate(R.layout.consultancy_list, viewGroup, false);

       ConsultancyViewHolder holder = new ConsultancyViewHolder(view);

       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultancyViewHolder holder, int position) {

        consultancy = consultancyArrayList.get(position);
        holder.tvConsultancyName.setText(consultancy.getName());
        holder.tvAddress.setText(consultancy.getAddress());
        holder.tvPhoneNumber.setText(consultancy.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return consultancyArrayList.size();
    }

    public  class ConsultancyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvConsultancyName, tvAddress, tvPhoneNumber;
        private Consultancy consultancyArrayLists;

        public ConsultancyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvConsultancyName = itemView.findViewById(R.id.tvConsultancyName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);

            db = AppDatabase.getInstance(context);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id  = getAdapterPosition();

                    Consultancy consultancy = consultancyArrayList.get(id);

                    consultancyArrayLists = db.consultancyDao().getConsultancyById(consultancy.getId());

                    Intent intent = new Intent(context, ConsultancyDetails.class);
                    intent.putExtra("Consultancy", consultancyArrayLists);

                    context.startActivity(intent);
                }
            });
        }
    }
}
