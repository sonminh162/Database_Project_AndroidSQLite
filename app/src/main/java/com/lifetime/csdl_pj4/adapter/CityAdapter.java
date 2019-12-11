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
import com.lifetime.csdl_pj4.activity.PlaceListScreen;
import com.lifetime.csdl_pj4.model.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private Context mCtx;
    private List<City> cityList;

    public CityAdapter(Context mCtx){
        this.mCtx = mCtx;
    }

    public CityAdapter(Context mCtx, List<City> cityList){
        this.cityList = cityList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_city,parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
         holder.bindView(cityList.get(position));
    }

    @Override
    public int getItemCount() {
        return cityList != null ? cityList.size() : 0;
    }

    public void setCities(List<City> cityList){
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        TextView cityName;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.cityName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCtx.startActivity(new Intent(mCtx, PlaceListScreen.class).putExtra("query_key",cityList.get(getAdapterPosition()).getCityName()));
                }
            });
        }

        void bindView(City city){
            cityName.setText(city.getCityName());
        }
    }
}
