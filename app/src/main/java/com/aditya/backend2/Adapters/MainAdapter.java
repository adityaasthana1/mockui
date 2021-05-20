package com.aditya.backend2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.backend2.R;
import com.aditya.backend2.models.User.UserData;
import com.bumptech.glide.Glide;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {

    private Context context;
    private List<UserData> dataList;

    public MainAdapter(Context context, List<UserData> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MainAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_users, parent, false);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapterViewHolder holder, int position) {
        String FullName = dataList.get(position).getFirstName()+ " " +dataList.get(position).getLastName();
        holder.UserName.setText(FullName);
        holder.UserEmail.setText(dataList.get(position).getEmail());
        Glide.with(context).load(dataList.get(position).getPicture())
                .into(holder.UserImage);

        holder.ContainerLayout.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView UserName, UserEmail;
        ImageView UserImage;
        LinearLayout ContainerLayout;
        public MainAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            UserEmail = itemView.findViewById(R.id.user_email);
            UserName = itemView.findViewById(R.id.user_name);
            UserImage = itemView.findViewById(R.id.user_image);
            ContainerLayout = itemView.findViewById(R.id.element_layout);
        }
    }
}
