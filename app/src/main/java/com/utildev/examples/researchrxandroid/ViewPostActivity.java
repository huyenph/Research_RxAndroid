package com.utildev.examples.researchrxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.utildev.examples.researchrxandroid.model.Post;

public class ViewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        if(getIntent().hasExtra("post")){
            Post post = (Post) getIntent().getSerializableExtra("post");
            Toast.makeText(this, post.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
