package com.lifetime.csdl_pj4.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.fragment.HotelFragment;
import com.lifetime.csdl_pj4.fragment.PlaygroundFragment;
import com.lifetime.csdl_pj4.fragment.RestaurantFragment;
import com.lifetime.csdl_pj4.model.Place;
import com.lifetime.csdl_pj4.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailPlaceActivity extends AppCompatActivity {

    Button hotelButton,playgroundButton,restaurantButton;

    String queryKey;

    PlaceViewModel placeViewModel;

    HotelFragment hotelFragment;
    RestaurantFragment restaurantFragment;
    PlaygroundFragment playgroundFragment;

    TextView placeNameText;
    TextView cityNameText;
    TextView placeTypeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_detail_screen);

        placeNameText = findViewById(R.id.place_name);
        cityNameText = findViewById(R.id.detail_city_name);
        placeTypeText = findViewById(R.id.detail_city_type);

        hotelButton = findViewById(R.id.hotelButton);
        playgroundButton = findViewById(R.id.playgroundButton);
        restaurantButton = findViewById(R.id.restaurantButton);

        queryKey = getIntent().getStringExtra("query_key");

        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);

        placeViewModel.searchSinglePlaceByPlaceName(queryKey);
        placeViewModel.getSinglePlaceByPlaceName().observe(this, new Observer<Place>() {
            @Override
            public void onChanged(Place place) {
                setView(place);
            }
        });

        initFirstTimeFragment();

        loadFragment(hotelFragment);

        setUpFragmentFeature();
    }

    private void setView(Place place){
        placeNameText.setText("TÊN ĐỊA ĐIỂM: "+place.getPlaceName());
        cityNameText.setText("THÀNH PHỐ: "+place.getCityName());
        placeTypeText.setText("LOẠI HÌNH DU LỊCH: "+place.getType());
    }

    private void initFirstTimeFragment(){
        hotelFragment = HotelFragment.instance(queryKey);
        restaurantFragment = RestaurantFragment.instance(queryKey);
        playgroundFragment = PlaygroundFragment.instance(queryKey);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void setUpFragmentFeature() {
        final List<Button> buttons = new ArrayList<>();
        buttons.add(hotelButton);
        buttons.add(restaurantButton);
        buttons.add(playgroundButton);

        playgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(2).setSelected(true);
                buttons.get(0).setSelected(false);
                buttons.get(1).setSelected(false);
                queryButton(buttons);

                loadFragment(playgroundFragment);
            }
        });

        hotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(0).setSelected(true);
                buttons.get(1).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);

                loadFragment(hotelFragment);
            }
        });

        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttons.get(1).setSelected(true);
                buttons.get(0).setSelected(false);
                buttons.get(2).setSelected(false);
                queryButton(buttons);

                loadFragment(restaurantFragment);
            }
        });
    }

    private void queryButton(List<Button> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            Button currentButton = buttons.get(i);
            if (currentButton.isSelected()) {
                currentButton.setBackground(getResources().getDrawable(R.drawable.button_box));
                currentButton.setTextColor(getResources().getColor(R.color.white_two));
            } else {
                currentButton.setBackground(getResources().getDrawable(R.drawable.background_box_2));
                currentButton.setTextColor(getResources().getColor(R.color.dark_blue));
            }
        }
    }
}
