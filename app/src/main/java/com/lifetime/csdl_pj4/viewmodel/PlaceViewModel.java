package com.lifetime.csdl_pj4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.model.Place;
import com.lifetime.csdl_pj4.repository.CityRepository;
import com.lifetime.csdl_pj4.repository.PlaceRepository;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository placeRepository;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> searchResultList;
    private LiveData<List<Place>> AzResult;
    private LiveData<List<Place>> ZaResult;

    public PlaceViewModel(@NonNull Application application) {
        super(application);

        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAllPlaces();
        searchResultList = placeRepository.getSearchResultList();
        AzResult = placeRepository.getResultSortAtoZ();
        ZaResult = placeRepository.getResultSortZtoA();
    }

    public void insertCity(Place place){
        placeRepository.insertPlace(place);
    }

    public LiveData<List<Place>> getAllPlaces(){
        return allPlaces;
    }

    public void deletePlace(Place place){
        placeRepository.deletePlace(place);
    }

    public void searchPlaceListByName(String placeName){
        placeRepository.searchPlaceByName(placeName);
    }

    public void searchPlaceListByCityName(String cityName){
        placeRepository.searchPlaceByCityName(cityName);
    }

    public void searchSinglePlaceByPlaceName(String placeName){
        placeRepository.setSinglePlace(placeName);
    }

    public LiveData<Place> getSinglePlaceByPlaceName(){
        return placeRepository.getSinglePlaceResult();
    }

    public void sortAtoZ(){
        placeRepository.sortDataAtoZ();
    }

    public void sortZtoA(){
        placeRepository.sortDataZtoA();
    }

    public LiveData<List<Place>> getSearchResultList(){
        return searchResultList;
    }

    public LiveData<List<Place>> sortAToZResultList(){
        return AzResult;
    }

    public LiveData<List<Place>> sortZToAResultList(){
        return ZaResult;
    }
}
