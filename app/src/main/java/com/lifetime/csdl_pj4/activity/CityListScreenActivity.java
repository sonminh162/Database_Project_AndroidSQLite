package com.lifetime.csdl_pj4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.csdl_pj4.R;
import com.lifetime.csdl_pj4.adapter.CityAdapter;
import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.viewmodel.CityViewModel;

import java.util.List;

public class CityListScreenActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        recyclerView = findViewById(R.id.studentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CityAdapter cityAdapter = new CityAdapter(CityListScreenActivity.this);
        recyclerView.setAdapter(cityAdapter);

        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);

        cityViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                cityAdapter.setCities(cities);
            }
        });

        findViewById(R.id.moveToPlaceScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CityListScreenActivity.this,PlaceListScreen.class));
            }
        });
    }

}

