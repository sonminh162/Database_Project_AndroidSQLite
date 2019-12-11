package com.lifetime.csdl_pj4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private Context mCtx;
    private List<Hotel> hotelList;

    public HotelAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public HotelAdapter(ArrayList<Hotel> hotelInfors){
        this.hotelList = hotelInfors;
    }

    public HotelAdapter(Context mCtx, List<Hotel> hotelList) {
        this.hotelList = hotelList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public HotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item_view, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.HotelViewHolder holder, int position) {
        holder.bindView(hotelList.get(position));
    }

    @Override
    public int getItemCount() {
        return hotelList != null ? hotelList.size() : 0;
    }

    public void setHotels(List<Hotel> hotelList) {
        this.hotelList = hotelList;
        notifyDataSetChanged();
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {

        TextView hotelName;
        TextView hotelPhone;

        HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelPhone = itemView.findViewById(R.id.hotel_phone);
        }

        void bindView(Hotel hotel) {
            hotelName.setText(hotel.getHotelName());
            hotelPhone.setText(hotel.getHotelPhone());
        }
    }
}
