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
import com.aditya.backend2.models.Post.PostData;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<PostData> data;
    private int ITEM_VIEW_LOADING = 0, ITEM_VIEW_ROW = 1;

    public PostAdapter(Context context, List<PostData> data){
        this.context = context;
        this.data = data;
    }

    

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        try {

            Glide.with(context).load(data.get(position).getImage()).into(holder.PostImage);
            Glide.with(context).load(data.get(position).getOwner().getPicture()).centerCrop().into(holder.UserImage);
            String fullname = data.get(position).getOwner().getFirstName() + " " + data.get(position).getOwner().getLastName();
            holder.UserName.setText(fullname);
            holder.UserEmail.setText(data.get(position).getOwner().getEmail());
            String likes = data.get(position).getLikes() + " Likes";
            holder.PostLikes.setText(likes);
            String date = data.get(position).getPublishDate().toLocaleString();
            holder.PostDate.setText(date);
            if (data.get(position).getLink() == null)
                holder.PostLink.setHeight(0);
            else holder.PostLink.setText(data.get(position).getLink());
            holder.PostDesription.setText(data.get(position).getText());
        }catch (Exception e){
            Log.d("BindViewHolderError", e.getMessage());
        }


    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) == null? ITEM_VIEW_LOADING : ITEM_VIEW_ROW;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView UserImage, LikeButton, PostOptions;
        PhotoView PostImage;
        TextView UserName, UserEmail, PostDesription, PostLikes, PostDate, PostLink;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            PostImage = itemView.findViewById(R.id.post_image);
            UserImage = itemView.findViewById(R.id.post_user_image);
            LikeButton = itemView.findViewById(R.id.post_like_icon);
            PostOptions = itemView.findViewById(R.id.post_user_option);
            UserName = itemView.findViewById(R.id.post_user_name);
            UserEmail = itemView.findViewById(R.id.post_user_email);
            PostDesription = itemView.findViewById(R.id.post_description);
            PostLikes = itemView.findViewById(R.id.post_likes);
            PostDate = itemView.findViewById(R.id.post_date);
            PostLink = itemView.findViewById(R.id.post_link);
        }
    }
}
