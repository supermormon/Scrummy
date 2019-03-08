package com.threeguys.scrummy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.google.gson.Gson;

import static com.threeguys.scrummy.MainActivity.ACTIVITY_KEY;
import static com.threeguys.scrummy.MainActivity.CONTINUE_KEY;
import static com.threeguys.scrummy.MainActivity.TEMP_SAVE_PREF;

public class SprintActivity extends AppCompatActivity {

    Session session;
    private int topicNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprint);

        // Initialize Member Data
        Gson gson = new Gson();
        String gsonSession = (String)getIntent().getExtras().get(MainActivity.SESSION_KEY);
        session = gson.fromJson(gsonSession, Session.class);
        topicNumber = 0;

        TextView currentTopic = findViewById(R.id._currentTopicTextView);
        Button nextTopic = findViewById(R.id._nextTopicButton);

        currentTopic.setText(session.getTopics().get(0).getTitle());
        // Is there more than one topic?
        if (session.getTopics().size() > 1) {
            nextTopic.setText(session.getTopics().get(1).getTitle());
        } else {
            nextTopic.setText(R.string.save_and_quit_button);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sp = this.getSharedPreferences(TEMP_SAVE_PREF, MODE_PRIVATE);
        Gson gson = new Gson();

        String sessionJson = gson.toJson(session, Session.class);
        String activityJson = "SprintActivity";

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CONTINUE_KEY, sessionJson);
        editor.putString(ACTIVITY_KEY, activityJson);
        editor.apply();
    }

    public void onClickNextTopic(View view) {
        TextView currentTopic = findViewById(R.id._currentTopicTextView);
        Button nextTopic = findViewById(R.id._nextTopicButton);
        MultiAutoCompleteTextView actionsView = findViewById(R.id._actionsMultiAutoCompleteTextView);

        session.getTopics().get(topicNumber).setActions(actionsView.getText().toString());
        actionsView.setText("");

        topicNumber++;

        // No more topics, save and quit
        if (topicNumber > session.getTopics().size()) {
            //TODO save
            return;
        }

        // Go to next Topic
        currentTopic.setText(session.getTopics().get(topicNumber).getTitle());

        // Check if there is a next topic
        if (topicNumber + 1 <= session.getTopics().size()) {
            nextTopic.setText(session.getTopics().get(topicNumber + 1).getTitle());
        } else {
            nextTopic.setText(R.string.save_and_quit_button);
        }
    }
}
