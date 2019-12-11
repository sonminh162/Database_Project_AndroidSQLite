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
import com.lifetime.csdl_pj4.adapter.RestaurantAdapter;
import com.lifetime.csdl_pj4.model.Restaurant;
import com.lifetime.csdl_pj4.viewmodel.PlaceDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment {

    private View view;

    private String searchKey;

    public static RestaurantFragment instance(String searchKey) {
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        restaurantFragment.searchKey = searchKey;
        return restaurantFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.restaurant_fragment, container, false);

            PlaceDetailViewModel placeDetailViewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel.class);

            placeDetailViewModel.getRestaurantListByPlaceName().observe(this, new Observer<List<Restaurant>>() {
                @Override
                public void onChanged(List<Restaurant> restaurantList) {
                    initView(restaurantList);
                }
            });

            placeDetailViewModel.searchRestaurantListByPlaceName(searchKey);

//            placeDetailViewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
//                @Override
//                public void onChanged(List<Restaurant> restaurantList) {
//                    initView(restaurantList);
//                }
//            });
        }

        return view;
    }

    private void initView(List<Restaurant> restaurantList) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_restaurant);
        recyclerView.hasFixedSize();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(restaurantList);

        RestaurantAdapter adapter = new RestaurantAdapter(restaurantArrayList);
        recyclerView.setAdapter(adapter);
    }
}
