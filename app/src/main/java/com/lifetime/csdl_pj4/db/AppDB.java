package com.lifetime.csdl_pj4.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lifetime.csdl_pj4.dao.AppDao;
import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.model.Place;
import com.lifetime.csdl_pj4.model.Playground;
import com.lifetime.csdl_pj4.model.Restaurant;
import com.lifetime.csdl_pj4.model.Student;


@Database(entities = {Student.class,City.class,Hotel.class,Place.class,Playground.class,Restaurant.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    private static final String DB_NAME = "TRAVEL_APP";
    private static AppDB instance;

    public static synchronized AppDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDBAsyncTask(instance).execute();
        }
    };

    public abstract AppDao studentDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private AppDao appDao;

        public PopulateDBAsyncTask(AppDB instance){
            this.appDao = instance.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDao.insertCity(new City("CAO BẰNG"));
            appDao.insertCity(new City("BẮC CẠN"));
            appDao.insertCity(new City("LẠNG SƠN"));
            appDao.insertCity(new City("THÁI NGUYÊN"));
            appDao.insertCity(new City("HÀ NỘI"));
            appDao.insertCity(new City("PHÚ THỌ"));
            appDao.insertCity(new City("NAM ĐỊNH"));
            appDao.insertCity(new City("VŨNG TÀU"));
            appDao.insertCity(new City("LÀO CAI"));
            appDao.insertCity(new City("YÊN BÁI"));
            appDao.insertCity(new City("THÀNH PHỐ HỒ CHÍ MINH"));
            appDao.insertCity(new City("THÁI BÌNH"));
            appDao.insertCity(new City("QUẢNG NINH"));

            appDao.insertPlace(new Place("HỒ TÂY","HÀ NỘI","CÔNG VIÊN"));
            appDao.insertPlace(new Place("LĂNG BÁC","HÀ NỘI","DI TÍCH LỊCH SỬ"));
            appDao.insertPlace(new Place("BẢO TÀNG HÀ NỘI","HÀ NỘI","DI TÍCH LỊCH SỬ"));
            appDao.insertPlace(new Place("HANG PÁC BÓ ","CAO BẰNG","DI TÍCH LỊCH SỬ"));
            appDao.insertPlace(new Place("THÁC BẢN GIỐC","CAO BẰNG","DI TÍCH LỊCH SỬ"));
            appDao.insertPlace(new Place("CỔNG TRỜI","CAO BẰNG","DI TÍCH LỊCH SỬ"));

            appDao.insertPlayground(new Playground("CÔNG VIÊN NƯỚC HỒ TÂY","HỒ TÂY","CÔNG VIÊN GIẢI TRÍ"));
            appDao.insertHotel(new Hotel("KHÁCH SẠN 5 SAO","HỒ TÂY","043-888-222"));
            appDao.insertRestaurant(new Restaurant("NHÀ HÀNG BUFFET SEN TÂY HỒ","HỒ TÂY","BUFFET","043-999-676"));

            return null;
        }
    }
}
