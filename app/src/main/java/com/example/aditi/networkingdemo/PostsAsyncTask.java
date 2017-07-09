package com.example.aditi.networkingdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Aditi on 7/8/2017.
 */

class PostsAsyncTask extends AsyncTask<String, Void, ArrayList<Post>> {

    OnDownloadCompleteListener mListener;

    void setOnDownLoadCompleteListener(OnDownloadCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected ArrayList<Post> doInBackground(String... params) {
        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            Scanner s = new Scanner(inputStream);
            String str = "";
            while (s.hasNext()) {
                str += s.nextLine();
            }

            Log.i("FetchedString ", str);
            return parsePosts(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Post> parsePosts(String str) {
        ArrayList<Post> postList = new ArrayList<>();

        try {
            JSONArray postArray = new JSONArray(str);
            for (int i = 0; i < postArray.length(); i++) {
                JSONObject postJSON = (JSONObject) postArray.get(i);
                int id = postJSON.getInt("id");
                int userId = postJSON.getInt("userId");
                String title = postJSON.getString("title");
                String body = postJSON.getString("body");
                Post post = new Post(userId, id, title, body);
                postList.add(post);
            }

            return postList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);
        if (mListener != null) {
            mListener.onDownloadComplete(posts);
        }

    }


}
interface OnDownloadCompleteListener {

    void onDownloadComplete(ArrayList<Post> postList);
}
