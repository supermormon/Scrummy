package com.threeguys.scrummy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class LoadSessionItemAdapter extends RecyclerView.Adapter<LoadSessionItemAdapter.LoadSessionItemsViewHolder> {

    private List<Session> sessions;
    private Context context;

    public LoadSessionItemAdapter(Context context, List<Session> sessions) {
        this.sessions = sessions;
        this.context = context;
    }

    @Override
    public LoadSessionItemAdapter.LoadSessionItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_session_item, parent, false);
        return new LoadSessionItemAdapter.LoadSessionItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LoadSessionItemAdapter.LoadSessionItemsViewHolder holder, final int position) {

        holder.sessionTitle.setText(sessions.get(position).getTitle());
        holder.sessionDate.setText(sessions.get(position).getDate());
        holder.sessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call onClickSession in LoadActivity.java
                if(context instanceof LoadActivity)
                    ((LoadActivity)context).onClickSession(position);
                //Send it "position" data
            }
        });
        holder.popUpMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open the pop up menu
                if(context instanceof LoadActivity)
                    ((LoadActivity)context).onClickPopup(holder.popUpMenu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    class LoadSessionItemsViewHolder extends RecyclerView.ViewHolder {

        Button sessionButton;
        TextView sessionTitle;
        TextView sessionDate;
        ImageButton popUpMenu;

        public LoadSessionItemsViewHolder(View itemView) {
            super(itemView);
            sessionButton = itemView.findViewById(R.id._viewSessionButton);
            sessionTitle = itemView.findViewById(R.id._viewSessionTitleTextView);
            sessionDate = itemView.findViewById(R.id._viewSessionDateTextView);
            popUpMenu = itemView.findViewById(R.id._sessionItemPopupImageButton);
        }
    }
}
