package com.threeguys.scrummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

public class ViewSessionActivity extends AppCompatActivity {

    private Session session;
    private RecyclerView recyclerView;
    private LoadTopicItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session);

        String sessionJson = (String)getIntent().getExtras().get(MainActivity.SESSION_KEY);
        Gson gson = new Gson();
        session = gson.fromJson(sessionJson, Session.class);

        adapter = new LoadTopicItemAdapter(session.getTopics());

        recyclerView = findViewById(R.id._viewSessionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void display() {

    }
}
