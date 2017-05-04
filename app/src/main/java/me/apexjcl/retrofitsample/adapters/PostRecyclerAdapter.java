package me.apexjcl.retrofitsample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.models.Post;

/**
 * Created by radog on 04/05/2017.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>(0);

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mBody;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void update(Post post) {
            mTitle.setText(post.getTitle());
            mBody.setText(post.getBody());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = (ArrayList<Post>) posts;
    }
}
