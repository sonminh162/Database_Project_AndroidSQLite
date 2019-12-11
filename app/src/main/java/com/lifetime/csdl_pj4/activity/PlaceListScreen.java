package com.lifetime.csdl_pj4.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.adapter.PlaceAdapter;
import com.lifetime.csdl_pj4.model.Place;
import com.lifetime.csdl_pj4.viewmodel.PlaceViewModel;

import java.util.List;

public class PlaceListScreen extends AppCompatActivity {

    TextView placeTitle;

    private RecyclerView recyclerView;

    private PlaceViewModel placeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_screen);

        placeTitle = findViewById(R.id.placeScreenTitle);

        recyclerView = findViewById(R.id.studentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PlaceAdapter placeAdapter = new PlaceAdapter(PlaceListScreen.this);
        recyclerView.setAdapter(placeAdapter);

        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);

        placeViewModel.getSearchResultList().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setPlaces(places);
            }
        });

        String queryKey = getIntent().getStringExtra("query_key");

        if (queryKey != null) {
            placeViewModel.searchPlaceListByCityName(queryKey);
            placeTitle.setText("ĐỊA ĐIỂM DU LỊCH TẠI "+queryKey);
        } else {
            placeViewModel.searchPlaceListByCityName("");
        }

        findViewById(R.id.moveBackCityList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}


