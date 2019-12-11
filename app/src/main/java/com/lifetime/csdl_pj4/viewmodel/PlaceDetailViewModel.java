package com.lifetime.csdl_pj4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.model.Playground;
import com.lifetime.csdl_pj4.model.Restaurant;
import com.lifetime.csdl_pj4.repository.PlaceDetailRepository;

import java.util.List;

public class PlaceDetailViewModel extends AndroidViewModel {

    private PlaceDetailRepository placeDetailRepository;
    private LiveData<List<Hotel>> allHotels;
    private LiveData<List<Restaurant>> allRestaurants;
    private LiveData<List<Playground>> allPlaygrounds;
    private LiveData<List<Hotel>> hotelListReceived;
    private LiveData<List<Restaurant>> restaurantListReceived;
    private LiveData<List<Playground>> playgroundListReceived;

    public PlaceDetailViewModel(@NonNull Application application) {
        super(application);

        placeDetailRepository = new PlaceDetailRepository(application);
        allHotels = placeDetailRepository.getAllHotels();
        allRestaurants = placeDetailRepository.getAllRestaurants();
        allPlaygrounds = placeDetailRepository.getAllPlaygrounds();
        hotelListReceived = placeDetailRepository.getHotelListByPlaceName();
        restaurantListReceived = placeDetailRepository.getRestaurantListByPlaceName();
        playgroundListReceived = placeDetailRepository.getPlaygroundListByPlaceName();
    }

    public void insertHotel(Hotel hotel){
        placeDetailRepository.insertHotel(hotel);
    }

    public LiveData<List<Hotel>> getAllHotels(){
        return allHotels;
    }

    public void deleteHotel(Hotel hotel){
        placeDetailRepository.deleteHotel(hotel);
    }

    public void searchHotelListByPlaceName(String placeName){
        placeDetailRepository.searchHotelListByPlaceName(placeName);
    }

    public LiveData<List<Hotel>> getHotelListByPlaceName(){
        return hotelListReceived;
    }

    //------------------

    public void insertRestaurant(Restaurant restaurant){
        placeDetailRepository.insertRestaurant(restaurant);
    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return allRestaurants;
    }

    public void deleteRestaurant(Restaurant restaurant){
        placeDetailRepository.deleteRestaurant(restaurant);
    }

    public void searchRestaurantListByPlaceName(String placeName){
        placeDetailRepository.searchRestaurantListByPlaceName(placeName);
    }

    public LiveData<List<Restaurant>> getRestaurantListByPlaceName(){
        return restaurantListReceived;
    }

    //------------------

    public void insertPlayground(Playground playground){
        placeDetailRepository.insertPlayground(playground);
    }

    public LiveData<List<Playground>> getAllPlaygrounds(){
        return allPlaygrounds;
    }

    public void deletePlayground(Playground playground){
        placeDetailRepository.deletePlayground(playground);
    }

    public void searchPlaygroundListByPlaceName(String placeName){
        placeDetailRepository.searchPlaygroundListByPlaceName(placeName);
    }

    public LiveData<List<Playground>> getPlaygroundListByPlaceName(){
        return playgroundListReceived;
    }
}
