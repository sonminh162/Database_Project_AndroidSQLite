package com.lifetime.csdl_pj4.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lifetime.csdl_pj4.dao.AppDao;
import com.lifetime.csdl_pj4.db.AppDB;
import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.model.Playground;
import com.lifetime.csdl_pj4.model.Restaurant;

import java.util.List;

public class PlaceDetailRepository {

    private AppDao appDao;
    private LiveData<List<Hotel>> allHotels;
    private LiveData<List<Playground>> allPlaygrounds;
    private LiveData<List<Restaurant>> allRestaurants;

    private MutableLiveData<List<Hotel>> searchHotelResults = new MutableLiveData<>();
    private MutableLiveData<List<Restaurant>> searchRestaurantResults = new MutableLiveData<>();
    private MutableLiveData<List<Playground>> searchPlaygroundResults = new MutableLiveData<>();

    public PlaceDetailRepository(Application application){
        AppDB db = AppDB.getInstance(application);

        appDao = db.studentDao();

        allHotels = appDao.getAllHotel();
        allPlaygrounds = appDao.getAllPlayground();
        allRestaurants = appDao.getAllRestaurant();

    }

    public void insertHotel(Hotel hotel){
        new InsertHotelAsyncTask(appDao).execute(hotel);
    }

    public static class InsertHotelAsyncTask extends AsyncTask<Hotel,Void,Void> {
        private AppDao appDao;
        private InsertHotelAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Hotel... hotels) {
            appDao.insertHotel(hotels[0]);
            return null;
        }
    }

    public void deleteHotel(Hotel hotel){
        new DeleteHotelAsyncTask(appDao).execute(hotel);
    }

    public static class DeleteHotelAsyncTask extends AsyncTask<Hotel,Void,Void>{

        private AppDao appDao;
        private DeleteHotelAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Hotel... hotels) {
            appDao.deleteHotel(hotels[0]);
            return null;
        }
    }

    public LiveData<List<Hotel>> getAllHotels(){
        return allHotels;
    }

    public void updateHotel(Hotel hotel){
        new UpdateHotelAsyncTask(appDao).execute(hotel);
    }

    public static class UpdateHotelAsyncTask extends AsyncTask<Hotel,Void,Void>{

        private AppDao appDao;
        private UpdateHotelAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Hotel... hotels) {
            appDao.updateHotel(hotels[0]);
            return null;
        }
    }

    public void searchHotelListByPlaceName(final String placeName){
        class SearchHotelListByPlaceNameAsyncTask extends AsyncTask<Void,Void,List<Hotel>>{

            private AppDao appDao;
            private SearchHotelListByPlaceNameAsyncTask(AppDao appDao){
                this.appDao = appDao;
            }
            @Override
            protected List<Hotel> doInBackground(Void... voids) {
                return appDao.getHotelListByPlaceName(placeName);
            }

            @Override
            protected void onPostExecute(List<Hotel> hotelList) {
                super.onPostExecute(hotelList);
                searchHotelResults.setValue(hotelList);
            }
        }

        new SearchHotelListByPlaceNameAsyncTask(appDao).execute();
    }

    public LiveData<List<Hotel>> getHotelListByPlaceName(){
        return searchHotelResults;
    }

//    ------------

    public void insertPlayground(Playground playground){
        new InsertPlaygroundAsyncTask(appDao).execute(playground);
    }

    public static class InsertPlaygroundAsyncTask extends AsyncTask<Playground,Void,Void> {
        private AppDao appDao;
        private InsertPlaygroundAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Playground... playgrounds) {
            appDao.insertPlayground(playgrounds[0]);
            return null;
        }
    }

    public void deletePlayground(Playground playground){
        new DeletePlaygroundAsyncTask(appDao).execute(playground);
    }

    public static class DeletePlaygroundAsyncTask extends AsyncTask<Playground,Void,Void>{

        private AppDao appDao;
        private DeletePlaygroundAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Playground... playgrounds) {
            appDao.deletePlayground(playgrounds[0]);
            return null;
        }
    }

    public LiveData<List<Playground>> getAllPlaygrounds(){
        return allPlaygrounds;
    }

    public void updatePlayground(Playground playground){
        new UpdatePlaygroundAsyncTask(appDao).execute(playground);
    }

    public static class UpdatePlaygroundAsyncTask extends AsyncTask<Playground,Void,Void>{

        private AppDao appDao;
        private UpdatePlaygroundAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Playground... playgrounds) {
            appDao.updatePlayground(playgrounds[0]);
            return null;
        }
    }

    public LiveData<List<Playground>> getPlaygroundListByPlaceName(){
        return searchPlaygroundResults;
    }

    public void searchPlaygroundListByPlaceName(final String placeName){
        class SearchPlaygroundListByPlaceNameAsyncTask extends AsyncTask<Void,Void,List<Playground>>{

            private AppDao appDao;
            private SearchPlaygroundListByPlaceNameAsyncTask(AppDao appDao){
                this.appDao = appDao;
            }
            @Override
            protected List<Playground> doInBackground(Void... voids) {
                return appDao.getPlaygroundListByPlaceName(placeName);
            }

            @Override
            protected void onPostExecute(List<Playground> playgroundList) {
                super.onPostExecute(playgroundList);
                searchPlaygroundResults.setValue(playgroundList);
            }
        }

        new SearchPlaygroundListByPlaceNameAsyncTask(appDao).execute();
    }

    //    ------------

    public void insertRestaurant(Restaurant restaurant){
        new InsertRestaurantAsyncTask(appDao).execute(restaurant);
    }

    public static class InsertRestaurantAsyncTask extends AsyncTask<Restaurant,Void,Void> {
        private AppDao appDao;
        private InsertRestaurantAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Restaurant... restaurants) {
            appDao.insertRestaurant(restaurants[0]);
            return null;
        }
    }

    public void deleteRestaurant(Restaurant restaurant){
        new DeleteRestaurantAsyncTask(appDao).execute(restaurant);
    }

    public static class DeleteRestaurantAsyncTask extends AsyncTask<Restaurant,Void,Void>{

        private AppDao appDao;
        private DeleteRestaurantAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Restaurant... restaurants) {
            appDao.deleteRestaurant(restaurants[0]);
            return null;
        }
    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return allRestaurants;
    }

    public void updateRestaurant(Restaurant restaurant){
        new UpdateRestaurantAsyncTask(appDao).execute(restaurant);
    }

    public static class UpdateRestaurantAsyncTask extends AsyncTask<Restaurant,Void,Void>{

        private AppDao appDao;
        private UpdateRestaurantAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Restaurant... restaurants) {
            appDao.updateRestaurant(restaurants[0]);
            return null;
        }
    }

    public void searchRestaurantListByPlaceName(final String placeName){
        class SearchRestaurantListByPlaceNameAsyncTask extends AsyncTask<Void,Void,List<Restaurant>>{

            private AppDao appDao;
            private SearchRestaurantListByPlaceNameAsyncTask(AppDao appDao){
                this.appDao = appDao;
            }
            @Override
            protected List<Restaurant> doInBackground(Void... voids) {
                return appDao.getRestaurantListByPlaceName(placeName);
            }

            @Override
            protected void onPostExecute(List<Restaurant> restaurantList) {
                super.onPostExecute(restaurantList);
                searchRestaurantResults.setValue(restaurantList);
            }
        }

        new SearchRestaurantListByPlaceNameAsyncTask(appDao).execute();
    }

    public LiveData<List<Restaurant>> getRestaurantListByPlaceName(){
        return searchRestaurantResults;
    }
}

