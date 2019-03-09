package com.threeguys.scrummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.threeguys.scrummy.MainActivity.SESSION_KEY;

public class LoadActivity extends AppCompatActivity {

    List<Session> sessions;
    private RecyclerView recyclerView;
    private LoadSessionItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        sessions = new ArrayList<>();

        //TEST Dummy code
        Topic t1 = new Topic();
        t1.setTitle("Good Test1");
        t1.setUsername("Bretton");
        t1.setCategory(Topic.Category.GOOD);

        Topic t2 = new Topic();
        t2.setTitle("Neutral Test1");
        t2.setUsername("Bretton");
        t2.setCategory(Topic.Category.NEUTRAL);
        t2.setActions("Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action");

        Topic t3 = new Topic();
        t3.setTitle("Bad Test1");
        t3.setUsername("Bretton");
        t3.setCategory(Topic.Category.BAD);
        t3.setActions("Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action");

        Session session1 = new Session();
        session1.setDate("First");
        session1.addTopic(t1);
        session1.addTopic(t2);
        session1.addTopic(t3);

        Topic t4 = new Topic();
        t4.setTitle("Good Test2");
        t4.setUsername("Bretton");
        t4.setCategory(Topic.Category.GOOD);
        t4.setActions("Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action");

        Topic t5 = new Topic();
        t5.setTitle("Neutral Test2");
        t5.setUsername("Bretton");
        t5.setCategory(Topic.Category.NEUTRAL);
        t5.setActions("Action Action Action Action Action Action Action Action Action Action Action Action Action Action");

        Topic t6 = new Topic();
        t6.setTitle("Bad Test2");
        t6.setUsername("Bretton");
        t6.setCategory(Topic.Category.BAD);
        t6.setActions("Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action Action");

        Session session2 = new Session();
        session2.setDate("Second");
        session2.addTopic(t4);
        session2.addTopic(t5);
        session2.addTopic(t6);

        sessions.add(session1);
        sessions.add(session2);
        //End dummy code

        adapter = new LoadSessionItemAdapter(this, sessions);

        recyclerView = findViewById(R.id._loadSessionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onClickSession(int index) {
        Log.i("onClickSession", "Session clicked: " + sessions.get(index).getDate());

        Gson gson = new Gson();
        String sessionJson = gson.toJson(sessions.get(index), Session.class);

        Intent viewIntent = new Intent(this, ViewSessionActivity.class);
        viewIntent.putExtra(SESSION_KEY, sessionJson);
        startActivity(viewIntent);
    }

    public void onClickPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.load_item_popup, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        getApplicationContext(),
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
    }
}
