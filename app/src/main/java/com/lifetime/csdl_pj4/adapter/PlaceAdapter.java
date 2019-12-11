package com.lifetime.csdl_pj4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.activity.DetailPlaceActivity;
import com.lifetime.csdl_pj4.activity.PlaceListScreen;
import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    private Context mCtx;
    private List<Place> placeList;

    public PlaceAdapter(Context mCtx){
        this.mCtx = mCtx;
    }

    public PlaceAdapter(Context mCtx, List<Place> placeList){
        this.placeList = placeList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_place,parent,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.PlaceViewHolder holder, int position) {
        holder.bindView(placeList.get(position));
    }

    @Override
    public int getItemCount() {
        return placeList != null ? placeList.size() : 0;
    }

    public void setPlaces(List<Place> placeList){
        this.placeList = placeList;
        notifyDataSetChanged();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView placeName;

        PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            placeName = itemView.findViewById(R.id.placeName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCtx.startActivity(new Intent(mCtx, DetailPlaceActivity.class).putExtra("query_key",placeList.get(getAdapterPosition()).getPlaceName()));
                }
            });
        }

        void bindView(Place place){
            placeName.setText(place.getPlaceName());
        }
    }
}
