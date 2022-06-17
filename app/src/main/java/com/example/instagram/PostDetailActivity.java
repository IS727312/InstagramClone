package com.example.instagram;

import android.os.Bundle;
import android.os.Parcel;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailActivity extends AppCompatActivity {
    Post post;
    TextView tvDetailsPostsUser;
    TextView tvDetailsPostTimestamp;
    TextView tvDetailsPostCaption;
    ImageView ivDetailsPostPicture;
    Date createdAt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_details);

        tvDetailsPostCaption = findViewById(R.id.tvDetailsPostCaption);
        tvDetailsPostsUser = findViewById(R.id.tvDetailsPostUser);
        tvDetailsPostTimestamp = findViewById(R.id.tvDetailsPostTimestamp);
        ivDetailsPostPicture = findViewById(R.id.ivDetailsPostPicture);

        // unwrap the post passed in via intent, using its simple name as a key
        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvDetailsPostCaption.setText(post.getDescription());
        tvDetailsPostsUser.setText(post.getUser().getUsername());
        createdAt = post.getCreatedAt();
        tvDetailsPostTimestamp.setText(post.getRelativeTimeAgo(createdAt));
        Glide.with(this).load(post.getImage().getUrl()).into(ivDetailsPostPicture);
    }
}
