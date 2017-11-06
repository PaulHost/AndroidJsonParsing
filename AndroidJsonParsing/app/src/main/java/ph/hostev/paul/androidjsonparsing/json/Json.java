package ph.hostev.paul.androidjsonparsing.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.androidjsonparsing.modules.Post;

public class Json {
    private ArrayList<Post> posts = new ArrayList<Post>();

    public List<Post> parsPosts(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        Post post;
        for (int i = 0; i < jsonArray.length(); i++) {
            post = new Post();
            JSONObject object = jsonArray.getJSONObject(i);
            post.setUserId(object.getInt("userId"));
            post.setId(object.getInt("id"));
            post.setTitle(object.getString("title"));
            post.setBody(object.getString("body"));
            posts.add(post);
        }
        return posts;
    }

    public String createPost(Post post) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("userId", post.getUserId());
        object.put("id", post.getId());
        object.put("title", post.getTitle());
        object.put("body", post.getBody());
        return object.toString();
    }
}
