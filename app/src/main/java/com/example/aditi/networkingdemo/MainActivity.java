 package com.example.aditi.networkingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity implements OnDownloadCompleteListener {

     ArrayList<Post> postList;
     ListView listView;
     PostArrayAdapter adapter;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         listView = (ListView) findViewById(R.id.listView);

         postList = new ArrayList<>();
         postList.add(new Post(1, 1, "hello", "world"));
         adapter = new PostArrayAdapter(this, postList);
         listView.setAdapter(adapter);

         fetchPosts();

     }

     private void fetchPosts() {

         String urlString = "https://jsonplaceholder.typicode.com/posts";
         PostsAsyncTask postsAsyncTask = new PostsAsyncTask();
         postsAsyncTask.execute(urlString);
         postsAsyncTask.setOnDownLoadCompleteListener(this);
     }


     @Override
     public void onDownloadComplete(ArrayList<Post> postList) {
         this.postList.clear();
         this.postList.addAll(postList);
         Log.i("onDownload", ""+this.postList.get(0).userId+" "+this.postList.get(0).title);
         adapter.notifyDataSetChanged();
     }
 }
