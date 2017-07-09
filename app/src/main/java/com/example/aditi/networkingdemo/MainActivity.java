 package com.example.aditi.networkingdemo;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity implements OnDownloadCompleteListener {

     ArrayList<Post> postList;
     ListView listView;
     ConstraintLayout mainLayout;
     PostArrayAdapter adapter;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         //listView = (ListView) findViewById(R.id.listView);

         postList = new ArrayList<>();
         adapter = new PostArrayAdapter(this, postList);
         /*listView.setAdapter(adapter);*/

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
         mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
         mainLayout.removeAllViews();
         listView = new ListView(this);
         listView.setAdapter(adapter);
         mainLayout.addView(listView);
         adapter.notifyDataSetChanged();

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(MainActivity.this, Comments.class);

             }
         });
     }
 }
