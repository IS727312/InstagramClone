package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvPostDescription;
        private TextView tvPostUsername;
        private ImageView ivPostPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostDescription = itemView.findViewById(R.id.tvPostDescription);
            tvPostUsername = itemView.findViewById(R.id.tvPostUsername);
            ivPostPicture = itemView.findViewById(R.id.ivPostPicture);
            itemView.setOnClickListener(this);

        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvPostDescription.setText(post.getDescription());
            tvPostUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPostPicture);
            }
        }

        @Override
        public void onClick(View v) {
            Log.e(TAG, "Position detected");
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the post at the position, this won't work if the class is static
                Post post = posts.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, PostDetailActivity.class);
                // serialize the post using parceler, use its short name as a key
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                // show the activity
                context.startActivity(intent);
            }
        }
    }
}
