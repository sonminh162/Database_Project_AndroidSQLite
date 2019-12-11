package com.lifetime.csdl_pj4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.repository.CityRepository;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private CityRepository cityRepository;
    private LiveData<List<City>> allCities;
    private LiveData<List<City>> searchResultList;
    private LiveData<List<City>> AzResult;
    private LiveData<List<City>> ZaResult;

    public CityViewModel(@NonNull Application application) {
        super(application);

        cityRepository = new CityRepository(application);
        allCities = cityRepository.getAllCities();
        searchResultList = cityRepository.getSearchResultList();
        AzResult = cityRepository.getResultSortAtoZ();
        ZaResult = cityRepository.getResultSortZtoA();
    }

    public void insertCity(City city){
        cityRepository.insertCity(city);
    }

    public LiveData<List<City>> getAllCities(){
        return allCities;
    }

    public void deleteCity(City city){
        cityRepository.deleteCity(city);
    }

    public void searchCityByName(String cityName){
        cityRepository.searchCityByName(cityName);
    }

    public void sortAtoZ(){
        cityRepository.sortDataAtoZ();
    }

    public void sortZtoA(){
        cityRepository.sortDataZtoA();
    }

    public LiveData<List<City>> getSearchResultList(){
        return searchResultList;
    }

    public LiveData<List<City>> sortAToZResultList(){
        return AzResult;
    }

    public LiveData<List<City>> sortZToAResultList(){
        return ZaResult;
    }
}
