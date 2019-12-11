package com.lifetime.csdl_pj4.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.lifetime.csdl_pj4.dao.AppDao;
import com.lifetime.csdl_pj4.db.AppDB;
import com.lifetime.csdl_pj4.model.Student;

import java.util.List;

public class StudentRepository {

    private AppDao appDao;
    private LiveData<List<Student>> allStudents;
    private MutableLiveData<List<Student>> searchStudentResultList = new MutableLiveData<>();
    private MutableLiveData<List<Student>> sortAToZResultList = new MutableLiveData<>();
    private MutableLiveData<List<Student>> sortZToAResultList = new MutableLiveData<>();

    public StudentRepository(Application application){
        AppDB db = AppDB.getInstance(application);

        appDao = db.studentDao();

        allStudents = appDao.getAll();

        searchStudent("");

        initSortData("");
    }

    public void initSortData(final String studentName){
        class InitSortDataAsyncTask extends AsyncTask<Void,Void,List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                return appDao.searchStudentByStudentName(studentName);
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                sortAToZResultList.setValue(students);
                sortZToAResultList.setValue(students);
            }
        }
        InitSortDataAsyncTask initSortDataAsyncTask = new InitSortDataAsyncTask();
        initSortDataAsyncTask.execute();
    }

    public void sortDataAtoZ(){
        sortAz();
    }

    public void sortAz(){
        class SortAzAsyncTask extends AsyncTask<Void,Void,List<Student>>{

            @Override
            protected List<Student> doInBackground(Void... voids) {
                return appDao.sortDataAtoZ();
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                sortAToZResultList.setValue(students);
            }
        }
        SortAzAsyncTask sortAzAsyncTask = new SortAzAsyncTask();
        sortAzAsyncTask.execute();
    }

    public void sortDataZtoA(){
        sortZa();
    }

    public void sortZa(){
        class SortZaAsyncTask extends AsyncTask<Void,Void,List<Student>>{

            @Override
            protected List<Student> doInBackground(Void... voids) {
                return appDao.sortDataZtoA();
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                sortAToZResultList.setValue(students);
            }
        }
        new SortZaAsyncTask().execute();
    }

    public void searchStudent(final String studentName){

        class SearchStudentTestAsyncTask extends AsyncTask<Void,Void,List<Student>>{
            @Override
            protected List<Student> doInBackground(Void... voids) {
                return appDao.searchStudentByStudentName(studentName);
            }

            @Override
            protected void onPostExecute(List<Student> studentsReceived) {
                super.onPostExecute(studentsReceived);
                searchStudentResultList.setValue(studentsReceived);
            }
        }

        SearchStudentTestAsyncTask searchStudentTestAsyncTask = new SearchStudentTestAsyncTask();
        searchStudentTestAsyncTask.execute();

    }

    public LiveData<List<Student>> getSearchResultList(){
        return searchStudentResultList;
    }

    public LiveData<List<Student>> getResultSortAtoZ(){
        return sortAToZResultList;
    }

    public LiveData<List<Student>> getResultSortZtoA(){
        return sortZToAResultList;
    }

    public void insertStudent(Student student){
        new InsertStudentAsyncTask(appDao).execute(student);
    }

    public void updateStudent(Student student){
        new UpdateStudentAsyncTask(appDao).execute(student);
    }

    public void deleteStudent(Student student){
        new DeleteStudentAsyncTask(appDao).execute(student);
    }

    public void searchStudentByName(String studentName){
        searchStudent(studentName);
    }

    public static class DeleteStudentAsyncTask extends AsyncTask<Student,Void,Void>{

        private AppDao appDao;
        private DeleteStudentAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            appDao.delete(students[0]);
            return null;
        }


    }
    public static class UpdateStudentAsyncTask extends AsyncTask<Student,Void,Void>{
        private AppDao appDao;
        private UpdateStudentAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            appDao.update(students[0]);
            return null;
        }


    }
    public static class InsertStudentAsyncTask extends AsyncTask<Student,Void,Void> {
        private AppDao appDao;
        private InsertStudentAsyncTask(AppDao appDao){
            this.appDao = appDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            appDao.insert(students[0]);
            return null;
        }
    }

    public LiveData<List<Student>> getAllStudent(){
        return allStudents;
    }

    // -----------chỉ để đọc và hiểu lý do vì sao không dùng

    public LiveData<Student> getStudentById(int id){
        return appDao.getStudentById(id);
    }
    // nếu làm cách dưới này sẽ bị lỗi thread
    // còn làm cách đang bị khóa comment kia ... thì phải thực hiện ngoài màn hình để gửi hám xử lý trong AsyncTask
    // nhưng vấn đề là bài tập đang muốn tách logic ra ngoài viewModel
    // Nên chỉ có cách get dữ liệu qua intent từ viewholder của bên adapter sang..

    // câu hỏi: có cách nào khác ko dùng intent đảm bảo áp dụng viewmodel và không bị lỗi thread?
    // có phải đây là lý do anh mentor ko yêu cầu get Item bằng Id để xử lý sửa xóa giống như bài tập retrofit hay ko?

    public Student getStudentByIdReturnOrigin(int id){
//        new GetStudentOldWay(appDao).execute(id);
//        return null;
        return appDao.getStudentByIdReturnStudent(id);
        //-----------------------------------------------------------


    }
}
