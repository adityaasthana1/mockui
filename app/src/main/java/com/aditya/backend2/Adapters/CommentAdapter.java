package com.aditya.backend2.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.backend2.R;
import com.aditya.backend2.models.Comment.CommentData;
import com.bumptech.glide.Glide;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    List<CommentData> commentData;

    public CommentAdapter(Context context, List<CommentData> commentData){
        this.context = context;
        this.commentData = commentData;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_comment, parent, false);
        return new CommentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Log.d("POST_COMMENT", commentData.get(position).getMessage());
        Glide.with(context).load(commentData.get(position).getOwner().getPicture()).into(holder.imageView);
        String fullname = commentData.get(position).getOwner().getFirstName() + " " + commentData.get(position).getOwner().getLastName();
        holder.UserName.setText(fullname);
        holder.CommentText.setText(commentData.get(position).getMessage());
        holder.CommentDate.setText(commentData.get(position).getPublishDate().toLocaleString());

    }

    @Override
    public int getItemCount() {
        return commentData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView UserName, CommentText,CommentDate;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.comment_element_image);
            UserName = itemView.findViewById(R.id.comment_username);
            CommentText = itemView.findViewById(R.id.comment_text);
            CommentDate = itemView.findViewById(R.id.comment_time);


        }
    }
}
