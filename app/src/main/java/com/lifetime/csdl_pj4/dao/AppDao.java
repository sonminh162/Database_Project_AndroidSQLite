package com.lifetime.csdl_pj4.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lifetime.csdl_pj4.model.City;
import com.lifetime.csdl_pj4.model.Hotel;
import com.lifetime.csdl_pj4.model.Place;
import com.lifetime.csdl_pj4.model.Playground;
import com.lifetime.csdl_pj4.model.Restaurant;
import com.lifetime.csdl_pj4.model.Student;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAll();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Query("SELECT * FROM Student WHERE id_field LIKE :studentId")
    LiveData<Student> getStudentById(int studentId);

    @Query("SELECT * FROM Student WHERE id_field LIKE :studentId")
    Student getStudentByIdReturnStudent(int studentId);

    @Query("SELECT * FROM Student ORDER BY name_filed DESC")
    List<Student> sortDataAtoZ();

    @Query("SELECT * FROM Student ORDER BY name_filed ASC")
    List<Student> sortDataZtoA();

    @Query("SELECT * FROM Student WHERE name_filed LIKE '%' || :studentName || '%'")
    List<Student>
    searchStudentByStudentName(String studentName);

//    --------------------City-----------------

    @Query("SELECT * FROM City")
    LiveData<List<City>> getAllCity();

    @Insert
    void insertCity(City city);

    @Delete
    void deleteCity(City city);

    @Query("SELECT * FROM City WHERE city_name_field LIKE '%' || :cityName || '%'")
    List<City> searchCityByCityName(String cityName);

    @Query("SELECT * FROM City ORDER BY city_name_field DESC")
    List<City> sortCityAtoZ();

    @Query("SELECT * FROM City ORDER BY city_name_field ASC")
    List<City> sortCityZtoA();

//    --------------------Place-----------------

    @Query("SELECT * FROM place")
    LiveData<List<Place>> getAllPlace();

    @Insert
    void insertPlace(Place place);

    @Delete
    void deletePlace(Place place);

    @Update
    void update(Place place);

    @Query("SELECT * FROM Place WHERE place_name_field LIKE '%' || :placeName || '%'")
    List<Place> searchPlaceByName(String placeName);

    @Query("SELECT * FROM Place WHERE place_name_field = :placeName")
    Place searchSinglePlaceByName(String placeName);

    @Query("SELECT * FROM Place WHERE city_name_field LIKE '%' || :cityName || '%'")
//    @Query("SELECT * FROM Place,City WHERE Place.city_name_field = City.city_name_field AND city_name_field LIKE '%' || :cityName || '%'")
    List<Place> searchPlaceByCityName(String cityName);

    @Query("SELECT * FROM Place ORDER BY place_name_field DESC")
    List<Place> sortPlaceAtoZ();

    @Query("SELECT * FROM Place ORDER BY place_name_field ASC")
    List<Place> sortPlaceZtoA();

//    --------------------PlaceDetail-----------------

    //--------------------Hotel-----------------------

    @Query("SELECT * FROM Hotel")
    LiveData<List<Hotel>> getAllHotel();

    @Query("SELECT * FROM Hotel WHERE place_name_field LIKE '%' || :placeName || '%'")
    List<Hotel> getHotelListByPlaceName(String placeName);

    @Insert
    void insertHotel(Hotel hotel);

    @Delete
    void deleteHotel(Hotel hotel);

    @Update
    void updateHotel(Hotel hotel);

    //--------------------Playground------------------

    @Query("SELECT * FROM Playground")
    LiveData<List<Playground>> getAllPlayground();

    @Insert
    void insertPlayground(Playground playground);

    @Delete
    void deletePlayground(Playground playground);

    @Update
    void updatePlayground(Playground playground);

    @Query("SELECT * FROM Playground WHERE place_name_field LIKE '%' || :placeName || '%'")
    List<Playground> getPlaygroundListByPlaceName(String placeName);

    //--------------------Restaurant------------------

    @Query("SELECT * FROM Restaurant")
    LiveData<List<Restaurant>> getAllRestaurant();

    @Insert
    void insertRestaurant(Restaurant restaurant);

    @Delete
    void deleteRestaurant(Restaurant restaurant);

    @Update
    void updateRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM Restaurant WHERE place_name_field LIKE '%' || :placeName || '%'")
    List<Restaurant> getRestaurantListByPlaceName(String placeName);

}

