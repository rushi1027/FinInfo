package com.devaconsultancy.fin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devaconsultancy.fin.R;
import com.devaconsultancy.fin.pojo.DataModal;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<DataModal> userDetailList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<DataModal> userDetailList) {
        this.userDetailList = userDetailList;
        this.mContext = context;
    }

    @NonNull
    @Override


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModal modal = userDetailList.get(position);
        holder.tvId.setText("" + modal.getId());
        holder.tvName.setText(modal.getName());


        if (modal.getAge() == null) {
            holder.lyAge.setVisibility(View.GONE);
        } else {
            holder.lyAge.setVisibility(View.VISIBLE);
            holder.tvAge.setText(modal.getAge());
        }

        if (modal.getCity() == null) {
            holder.lyCity.setVisibility(View.GONE);
        } else {
            holder.lyCity.setVisibility(View.VISIBLE);
            holder.tvCity.setText(modal.getCity());
        }

    }

    @Override
    public int getItemCount() {
        return userDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvId;
        protected TextView tvAge;
        protected TextView tvName;
        protected TextView tvCity;
        protected LinearLayout lyAge;
        protected LinearLayout lyCity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvId = (TextView) itemView.findViewById(R.id.tv_id);
            this.tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvCity = (TextView) itemView.findViewById(R.id.tv_city);
            this.lyAge = (LinearLayout) itemView.findViewById(R.id.ly_age);
            this.lyCity = (LinearLayout) itemView.findViewById(R.id.ly_city);

            itemView.setOnClickListener(this);

        }

        public void onClick(View view) {

        }
    }
}
