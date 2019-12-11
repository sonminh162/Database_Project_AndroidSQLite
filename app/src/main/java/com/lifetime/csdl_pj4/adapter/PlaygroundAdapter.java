package com.lifetime.csdl_pj4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.model.Playground;

import java.util.ArrayList;
import java.util.List;

public class PlaygroundAdapter extends RecyclerView.Adapter<PlaygroundAdapter.PlaygroundViewHolder> {

    private Context mCtx;
    private List<Playground> playgroundList;

    public PlaygroundAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public PlaygroundAdapter(ArrayList<Playground> playgroundList){
        this.playgroundList = playgroundList;
    }

    public PlaygroundAdapter(Context mCtx, List<Playground> playgroundList) {
        this.playgroundList = playgroundList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public PlaygroundAdapter.PlaygroundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playground_item_view, parent, false);
        return new PlaygroundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaygroundAdapter.PlaygroundViewHolder holder, int position) {
        holder.bindView(playgroundList.get(position));
    }

    @Override
    public int getItemCount() {
        return playgroundList != null ? playgroundList.size() : 0;
    }

    public void setPlaygroundList(List<Playground> playgroundList) {
        this.playgroundList = playgroundList;
        notifyDataSetChanged();
    }

    class PlaygroundViewHolder extends RecyclerView.ViewHolder {

        TextView playgroundName;
        TextView playgroundType;

        PlaygroundViewHolder(@NonNull View itemView) {
            super(itemView);

            playgroundName = itemView.findViewById(R.id.playground_name);
            playgroundType = itemView.findViewById(R.id.playground_type);
        }

        void bindView(Playground playground) {
            playgroundName.setText(playground.getPlaygroundName());
            playgroundType.setText(playground.getType());
        }
    }
}
