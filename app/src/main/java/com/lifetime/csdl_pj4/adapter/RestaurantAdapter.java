package com.lifetime.csdl_pj4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private Context mCtx;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public RestaurantAdapter(ArrayList<Restaurant> restaurantInfors){
        this.restaurantList = restaurantInfors;
    }

    public RestaurantAdapter(Context mCtx, List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_view, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        holder.bindView(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    public void setHotels(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView restaurantName;
        TextView restaurantPhone;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantPhone = itemView.findViewById(R.id.restaurant_phone);
        }

        void bindView(Restaurant restaurant) {
            restaurantName.setText(restaurant.getRestaurantName());
            restaurantPhone.setText(restaurant.getRestaurantPhone());
        }
    }
}
