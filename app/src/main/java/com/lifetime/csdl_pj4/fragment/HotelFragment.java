package com.lifetime.csdl_pj4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.adapter.HotelAdapter;
import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.viewmodel.PlaceDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class HotelFragment extends Fragment {

    private View view;

    private String searchKey;

    public static HotelFragment instance(String searchKey) {
        HotelFragment hotelFragment = new HotelFragment();
        hotelFragment.searchKey = searchKey;
        return hotelFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.hotel_fragment, container, false);

            PlaceDetailViewModel placeDetailViewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel.class);


            placeDetailViewModel.getHotelListByPlaceName().observe(this, new Observer<List<Hotel>>() {
                @Override
                public void onChanged(List<Hotel> hotels) {
                    initView(hotels);
                }
            });

            placeDetailViewModel.searchHotelListByPlaceName(searchKey);

//            placeDetailViewModel.getAllHotels().observe(this, new Observer<List<Hotel>>() {
//                @Override
//                public void onChanged(List<Hotel> hotels) {
//                    initView(hotels);
//                }
//            });
        }

        return view;
    }

    private void initView(List<Hotel> hotels) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_hotel);
        recyclerView.hasFixedSize();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Hotel> hotelInfors = new ArrayList<>(hotels);

        HotelAdapter adapter = new HotelAdapter(hotelInfors);
        recyclerView.setAdapter(adapter);
    }
}