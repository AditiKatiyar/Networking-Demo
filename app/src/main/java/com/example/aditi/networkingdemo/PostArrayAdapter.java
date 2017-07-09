package com.example.aditi.networkingdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Aditi on 7/8/2017.
 */

public class PostArrayAdapter extends ArrayAdapter<Post> {

    ArrayList<Post> postList;
    Context context;
    public PostArrayAdapter(@NonNull Context context, ArrayList<Post> postList) {
        super(context, 0, postList);
        this.postList = postList;
        this.context = context;
    }

    static class PostViewHolder {
        TextView userIdTV;
        TextView titleTV;
        TextView bodyTV;

        public PostViewHolder(TextView userIdTV, TextView titleTV, TextView bodyTV) {
            this.userIdTV = userIdTV;
            this.titleTV = titleTV;
            this.bodyTV = bodyTV;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            TextView titleTV = (TextView) convertView.findViewById(R.id.titleTV);
            TextView userIdTV = (TextView) convertView.findViewById(R.id.userIdTV);
            TextView bodyTV = (TextView) convertView.findViewById(R.id.bodyTV);
            PostViewHolder postViewHolder = new PostViewHolder(userIdTV, titleTV, bodyTV);
            convertView.setTag(postViewHolder);
        }

        Post post = postList.get(position);
        PostViewHolder postViewHolder = (PostViewHolder) convertView.getTag();
        postViewHolder.titleTV.setText(post.title);
        postViewHolder.userIdTV.setText(""+post.userId);
        postViewHolder.bodyTV.setText(post.body);

        return convertView;
    }
}
