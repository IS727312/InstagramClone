package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private Button btnLogOut;
    private Button btnCaptureImage;
    private Button btnSubmit;
    private EditText etDescription;
    private ImageView ivPostImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogOut);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        etDescription = findViewById(R.id.etDescription);
        ivPostImage = findViewById(R.id.ivPostImage);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(currentUser == null){
                    Log.i(TAG, "Successfully Logged Out");
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
        
        queryPosts();
    }

    private void queryPosts() {
        //Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        //Specify the ObjectID
         query.findInBackground(new FindCallback<Post>() {
             @Override
             public void done(List<Post> posts, ParseException e) {
                 if(e != null){
                     Log.e(TAG, "Issues with getting posts: ", e);
                     return;
                 }
                  for(Post post: posts){
                      Log.i(TAG,  "Post: " +  post.getDescription() + ", Username: " + post.getUser().getUsername());
                  }
             }
         });

    }
}