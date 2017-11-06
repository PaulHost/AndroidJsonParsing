package ph.hostev.paul.androidjsonparsing.httpClient;

import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.androidjsonparsing.httpClient.http.Http;
import ph.hostev.paul.androidjsonparsing.httpClient.http.ISuccess;
import ph.hostev.paul.androidjsonparsing.httpClient.json.Json;
import ph.hostev.paul.androidjsonparsing.httpClient.modules.Post;

public class HttpClient {
    private Http http;
    private Json json;
    private ArrayList<Post> posts;
    private static final String TAG = "HttpClient";
    private static final String GET_POST = "https://jsonplaceholder.typicode.com/posts?userId=1";
    private static final String POST_POST = "https://jsonplaceholder.typicode.com/posts";

    public HttpClient() {
        http = new Http();
        json = new Json();
        posts = new ArrayList<>();
    }

    public void getPosts(final ISuccess<List<Post>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                http.get(GET_POST, new ISuccess<String>() {
                    @Override
                    public void onSuccess(String s) {
                        try {

                            callback.onSuccess(json.parsPosts(s));

                        } catch (JSONException e) {
                            Log.d(TAG, e.getMessage());
                        }
                    }
                });
            }
        })
                .start();
    }

    public void postPost(final Post post, final ISuccess<Boolean> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    http.post(POST_POST, json.createPost(post), new ISuccess<String>() {
                        @Override
                        public void onSuccess(String s) {
                            //todo post response correct callback
                            callback.onSuccess(true);
                        }
                    });
                } catch (JSONException e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        })
                .start();
    }
}
