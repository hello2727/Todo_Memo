package org.jh.todomemo.Repository.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.jh.todomemo.Model.dao.pictureMemoDao;
import org.jh.todomemo.Model.database.pictureMemoDatabase;
import org.jh.todomemo.Model.entity.pictureMemo;

import java.util.List;

public class pictureMemoRepository {
    //Dao의 멤버변수와 메모를 넣을 list변수를 만들어줌
    private pictureMemoDao mPictureMemoDao;
    private LiveData<List<pictureMemo>> mAllPictureMemo;

    public pictureMemoRepository(Application application){
        pictureMemoDatabase db = pictureMemoDatabase.getDatabase(application);
        //RoomDatabase에 있는 Dao를 가져온다.
        mPictureMemoDao = db.pictureMemoDao();
        //Dao의 쿼리를 이용하여 저장되어있는 모든 메모를 가져온다.
        mAllPictureMemo = mPictureMemoDao.getAllPictureMemos();
    }

    public LiveData<List<pictureMemo>> getAllPictureMemos() {
        return mAllPictureMemo;
    }

    //메모를 추가하는 함수
    public void insert(pictureMemo pictureMemo){
        new insertAsyncTask(mPictureMemoDao).execute(pictureMemo);
    }

    //리사이클러뷰 어댑터 선언된 곳에서 메모를 삭제하는 함수
    public void delete(pictureMemo pictureMemo) {
        new deleteAsyncTask(mPictureMemoDao).execute(pictureMemo);
    }

    //리사이클러뷰 어댑터 선언되지 않은 곳에서 메모를 삭제하는 함수
    public void deleteSpecificPictureMemo(int id){
        new deleteSpecificRowAsyncTask(mPictureMemoDao).execute(id);
    }

    private static class insertAsyncTask extends AsyncTask<pictureMemo, Void, Void> {
        private pictureMemoDao mAsyncTaskDao;

        insertAsyncTask(pictureMemoDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final pictureMemo... pictureMemos) {
            mAsyncTaskDao.insertPictureMemos(pictureMemos[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<pictureMemo, Void, Void> {
        private pictureMemoDao mAsyncTaskDao;

        deleteAsyncTask(pictureMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final pictureMemo... pictureMemos) {
            mAsyncTaskDao.deletePictureMemos(pictureMemos[0]);
            return null;
        }
    }

    private static class deleteSpecificRowAsyncTask extends AsyncTask<Integer, Void, Void> {
        private pictureMemoDao mAsyncTaskDao;

        deleteSpecificRowAsyncTask(pictureMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer ...id) {
            mAsyncTaskDao.deleteSpecificPictureMemo(id[0]);
            return null;
        }
    }
}
