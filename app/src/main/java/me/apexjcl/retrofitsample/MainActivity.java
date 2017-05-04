package me.apexjcl.retrofitsample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.apexjcl.retrofitsample.api.PostAPI;
import me.apexjcl.retrofitsample.api.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        Callback<List<POST>>{

    private SwipeRefreshLayout mLayout;
    private RecyclerView mRecyclerView;
    private PostAPI mPostApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_referesh_layout);
        mLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //Se inicializa el servicio para consumir la API de Posts
        mPostApi = ServiceGenerator.generate(PostAPI.class);
    }

    @Override
    public void onRefresh() {
        //sincrono enquie
        //asincrono execute
        mPostApi.getPosts().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<POST>> call, Response<List<POST>> response) {
        mLayout.setRefreshing(false);

    }

    @Override
    public void onFailure(Call<List<POST>> call, Throwable t) {
        mLayout.setRefreshing(false);
    }
}
