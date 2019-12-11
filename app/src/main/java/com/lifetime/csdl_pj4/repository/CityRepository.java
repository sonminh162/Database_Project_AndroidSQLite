package com.lifetime.csdl_pj4.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lifetime.csdl_pj4.dao.AppDao;
import com.lifetime.csdl_pj4.db.AppDB;
import com.lifetime.csdl_pj4.model.City;

import java.util.List;

public class CityRepository {

    private AppDao appDao;
    private LiveData<List<City>> allCities;
    private MutableLiveData<List<City>> searchCityResultList = new MutableLiveData<>();
    private MutableLiveData<List<City>> sortAToZResultList = new MutableLiveData<>();
    private MutableLiveData<List<City>> sortZToAResultList = new MutableLiveData<>();

    public CityRepository(Application application){
        AppDB db = AppDB.getInstance(application);

        appDao = db.studentDao();

        allCities = appDao.getAllCity();

        searchCity("");

        initSortData("");
    }

    public void initSortData(final String cityName){
        class InitSortDataAsyncTask extends AsyncTask<Void,Void,List<City>> {

            @Override
            protected List<City> doInBackground(Void... voids) {
                return appDao.searchCityByCityName(cityName);
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                sortAToZResultList.setValue(cities);
                sortZToAResultList.setValue(cities);
            }
        }
        InitSortDataAsyncTask initSortDataAsyncTask = new InitSortDataAsyncTask();
        initSortDataAsyncTask.execute();
    }

    public void sortDataAtoZ(){
        sortAz();
    }

    public void sortAz(){
        class SortAzAsyncTask extends AsyncTask<Void,Void,List<City>>{

            @Override
            protected List<City> doInBackground(Void... voids) {
                return appDao.sortCityAtoZ();
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                sortAToZResultList.setValue(cities);
            }
        }
        SortAzAsyncTask sortAzAsyncTask = new SortAzAsyncTask();
        sortAzAsyncTask.execute();
    }

    public void sortDataZtoA(){
        sortZa();
    }

    public void sortZa(){
        class SortZaAsyncTask extends AsyncTask<Void,Void,List<City>>{

            @Override
            protected List<City> doInBackground(Void... voids) {
                return appDao.sortCityZtoA();
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                sortAToZResultList.setValue(cities);
            }
        }
        new SortZaAsyncTask().execute();
    }

    public LiveData<List<City>> getResultSortAtoZ(){
        return sortAToZResultList;
    }

    public LiveData<List<City>> getResultSortZtoA(){
        return sortZToAResultList;
    }

    public LiveData<List<City>> getSearchResultList(){
        return searchCityResultList;
    }

    public void searchCity(final String cityName){

        class SearchStudentTestAsyncTask extends AsyncTask<Void,Void,List<City>>{
            @Override
            protected List<City> doInBackground(Void... voids) {
                return appDao.searchCityByCityName(cityName);
            }

            @Override
            protected void onPostExecute(List<City> studentsReceived) {
                super.onPostExecute(studentsReceived);
                searchCityResultList.setValue(studentsReceived);
            }
        }

        SearchStudentTestAsyncTask searchStudentTestAsyncTask = new SearchStudentTestAsyncTask();
        searchStudentTestAsyncTask.execute();

    }

    public void insertCity(City city){
        new InsertCityAsyncTask(appDao).execute(city);
    }

    public static class InsertCityAsyncTask extends AsyncTask<City,Void,Void> {
        private AppDao appDao;
        private InsertCityAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(City... cities) {
            appDao.insertCity(cities[0]);
            return null;
        }

    }

    public void searchCityByName(String cityName){
        searchCity(cityName);
    }

    public void deleteCity(City city){
        new DeleteCityAsyncTask(appDao).execute(city);
    }

    public static class DeleteCityAsyncTask extends AsyncTask<City,Void,Void>{

        private AppDao appDao;
        private DeleteCityAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(City... cities) {
            appDao.deleteCity(cities[0]);
            return null;
        }
    }

    public LiveData<List<City>> getAllCities(){
        return allCities;
    }

}
