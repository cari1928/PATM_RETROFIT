package me.apexjcl.retrofitsample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import me.apexjcl.retrofitsample.adapters.PostRealmRecyclerAdapter;
import me.apexjcl.retrofitsample.models.Post;

/**
 * Created by radog on 04/05/2017.
 */

public class RealmActivity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView mRecycler;
    private EditText mPostTitle;
    private PostRealmRecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_realm_showcase);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        mPostTitle = (EditText) findViewById(R.id.post_title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        realm.getDefaultInstance();
        mAdapter = new PostRealmRecyclerAdapter(
                getApplicationContext(),
                realm.where(Post.class).findAll(), false);
        mRecycler.setAdapter(mAdapter);
    }

    public void addPost(View v) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Post p = realm.createObject(Post.class);
                p.setTitle(mPostTitle.getText().toString());
                p.setBody("fdklasññj");
                realm.copyToRealm(p);
            }
        });
    }

    @Override
    protected void onStop() {
        if (realm != null) {
            realm.close();
        }
        realm = null;
        super.onStop();
    }
}
