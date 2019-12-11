package com.lifetime.csdl_pj4.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lifetime.csdl_pj4.dao.AppDao;
import com.lifetime.csdl_pj4.db.AppDB;
import com.lifetime.csdl_pj4.model.Place;

import java.util.List;

public class PlaceRepository {

    private AppDao appDao;
    private LiveData<List<Place>> allPlaces;
    private MutableLiveData<List<Place>> searchResultList = new MutableLiveData<>();
    private MutableLiveData<List<Place>> sortAToZResultList = new MutableLiveData<>();
    private MutableLiveData<List<Place>> sortZToAResultList = new MutableLiveData<>();

    private MutableLiveData<Place> placeResult = new MutableLiveData<>();

    public PlaceRepository(Application application){
        AppDB db = AppDB.getInstance(application);

        appDao = db.studentDao();

        allPlaces = appDao.getAllPlace();

        searchPlace("");

        initSortData("");
    }

    public void sortDataAtoZ(){
        sortAz();
    }

    public void sortAz(){
        class SortAzAsyncTask extends AsyncTask<Void,Void,List<Place>>{

            @Override
            protected List<Place> doInBackground(Void... voids) {
                return appDao.sortPlaceAtoZ();
            }

            @Override
            protected void onPostExecute(List<Place> places) {
                super.onPostExecute(places);
                sortAToZResultList.setValue(places);
            }
        }
        SortAzAsyncTask sortAzAsyncTask = new SortAzAsyncTask();
        sortAzAsyncTask.execute();
    }

    public void sortDataZtoA(){
        sortZa();
    }

    public void sortZa(){
        class SortZaAsyncTask extends AsyncTask<Void,Void,List<Place>>{

            @Override
            protected List<Place> doInBackground(Void... voids) {
                return appDao.sortPlaceZtoA();
            }

            @Override
            protected void onPostExecute(List<Place> places) {
                super.onPostExecute(places);
                sortAToZResultList.setValue(places);
            }
        }
        new SortZaAsyncTask().execute();
    }

    public LiveData<List<Place>> getResultSortAtoZ(){
        return sortAToZResultList;
    }

    public LiveData<List<Place>> getResultSortZtoA(){
        return sortZToAResultList;
    }

    public LiveData<List<Place>> getSearchResultList(){
        return searchResultList;
    }

    public void initSortData(final String placeName){
        class InitSortDataAsyncTask extends AsyncTask<Void,Void,List<Place>> {

            @Override
            protected List<Place> doInBackground(Void... voids) {
                return appDao.searchPlaceByName(placeName);
            }

            @Override
            protected void onPostExecute(List<Place> places) {
                super.onPostExecute(places);
                sortAToZResultList.setValue(places);
                sortZToAResultList.setValue(places);
            }
        }
        InitSortDataAsyncTask initSortDataAsyncTask = new InitSortDataAsyncTask();
        initSortDataAsyncTask.execute();
    }

    private void searchPlace(final String placeName){

        class SearchPlaceTestAsyncTask extends AsyncTask<Void,Void,List<Place>>{
            @Override
            protected List<Place> doInBackground(Void... voids) {
                return appDao.searchPlaceByName(placeName);
            }

            @Override
            protected void onPostExecute(List<Place> placesReceived) {
                super.onPostExecute(placesReceived);
                searchResultList.setValue(placesReceived);
            }
        }

        SearchPlaceTestAsyncTask searchPlaceTestAsyncTask = new SearchPlaceTestAsyncTask();
        searchPlaceTestAsyncTask.execute();

    }

    public void insertPlace(Place place){
        new InsertPlaceAsyncTask(appDao).execute(place);
    }

    public static class InsertPlaceAsyncTask extends AsyncTask<Place,Void,Void> {
        private AppDao appDao;
        private InsertPlaceAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Place... places) {
            appDao.insertPlace(places[0]);
            return null;
        }

    }

    public void searchPlaceByName(String placeName){
        searchPlace(placeName);
    }

    public void searchPlaceByCityName(final String cityName){
        class SearchPlaceTestAsyncTask extends AsyncTask<Void,Void,List<Place>>{
            @Override
            protected List<Place> doInBackground(Void... voids) {
                return appDao.searchPlaceByCityName(cityName);
            }

            @Override
            protected void onPostExecute(List<Place> placesReceived) {
                super.onPostExecute(placesReceived);
                searchResultList.setValue(placesReceived);
            }
        }

        SearchPlaceTestAsyncTask searchPlaceTestAsyncTask = new SearchPlaceTestAsyncTask();
        searchPlaceTestAsyncTask.execute();
    }

    public void deletePlace(Place place){
        new DeletePlaceAsyncTask(appDao).execute(place);
    }

    public static class DeletePlaceAsyncTask extends AsyncTask<Place,Void,Void>{

        private AppDao appDao;
        private DeletePlaceAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Place... places) {
            appDao.deletePlace(places[0]);
            return null;
        }
    }

    public LiveData<List<Place>> getAllPlaces(){
        return allPlaces;
    }

    public void setSinglePlace(final String placeName){
        class singlePlaceAsyncTask extends AsyncTask<Void,Void,Place>{

            @Override
            protected Place doInBackground(Void... voids) {
                return appDao.searchSinglePlaceByName(placeName);
            }

            @Override
            protected void onPostExecute(Place place) {
                super.onPostExecute(place);
                placeResult.setValue(place);
            }
        }
        singlePlaceAsyncTask singlePlaceAsyncTask = new singlePlaceAsyncTask();
        singlePlaceAsyncTask.execute();
    }

    public LiveData<Place> getSinglePlaceResult(){
        return placeResult;
    }
}

