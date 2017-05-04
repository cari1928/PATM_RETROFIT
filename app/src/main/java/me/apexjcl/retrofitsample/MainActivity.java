package me.apexjcl.retrofitsample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.apexjcl.retrofitsample.adapters.PostRecyclerAdapter;
import me.apexjcl.retrofitsample.api.PostAPI;
import me.apexjcl.retrofitsample.api.ServiceGenerator;
import me.apexjcl.retrofitsample.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        Callback<List<Post>> {

    private SwipeRefreshLayout mLayout;
    private RecyclerView mRecyclerView;
    private PostRecyclerAdapter mAdapter;
    private PostAPI mPostApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_referesh_layout);
        mLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new PostRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //Se inicializa el servicio para consumir la API de Posts
        mPostApi = ServiceGenerator.generate(PostAPI.class);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        mLayout.setRefreshing(false);
        mAdapter.setPosts(response.body());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        mLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        //sincrono enquie
        //asincrono execute
        mPostApi.getPosts().enqueue(this);
    }
}
