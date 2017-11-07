package ph.hostev.paul.androidjsonparsing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.androidjsonparsing.R;
import ph.hostev.paul.androidjsonparsing.httpClient.modules.Post;

public class PostsAdapter extends Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<Post> posts;

    public PostsAdapter(List<Post> posts) {
        this.posts = (ArrayList<Post>) posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.post_title);
            body = (TextView) itemView.findViewById(R.id.post_body);
        }
    }
}
