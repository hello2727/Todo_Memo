/*여러 데이터에 대한 접근을 할 수 있는 class.
 주로 로컬 데이터(앱 내장데이터)를 가져올지 네트워크에서 데이터를 가져올지를 다룸.*/
package org.jh.todomemo.Repository.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.jh.todomemo.Model.dao.writingMemoDao;
import org.jh.todomemo.Model.database.writingMemoDatabase;
import org.jh.todomemo.Model.entity.writingMemo;

import java.util.List;

public class writingMemoRepository {
    //Dao의 멤버변수와 메모를 넣을 list변수를 만들어줌
    private writingMemoDao mWritingMemoDao;
    private LiveData<List<writingMemo>> mAllWritingMemo;

    public writingMemoRepository(Application application) {
        writingMemoDatabase db = writingMemoDatabase.getDatabase(application);
        //RoomDatabase에 있는 Dao를 가져온다.
        mWritingMemoDao = db.writingMemoDao();
        //Dao의 쿼리를 이용하여 저장되어있는 모든 메모를 가져온다.
        mAllWritingMemo = mWritingMemoDao.getAllWritingMemos();
    }

    public LiveData<List<writingMemo>> getAllWritingMemos() {
        return mAllWritingMemo;
    }

    //메모를 추가하는 함수
    public void insert(writingMemo writingMemo) {
        new insertAsyncTask(mWritingMemoDao).execute(writingMemo);
    }

    //리사이클러뷰 어댑터 선언된 곳에서 메모를 삭제하는 함수
    public void delete(writingMemo writingMemo) {
        new deleteAsyncTask(mWritingMemoDao).execute(writingMemo);
    }

    //리사이클러뷰 어댑터 선언되지 않은 곳에서 메모를 삭제하는 함수
    public void deleteSpecificWritingMemo(int id){
        new deleteSpecificRowAsyncTask(mWritingMemoDao).execute(id);
    }

    //메모를 수정하는 함수
    public void update(int id, String newTitle, String newContent) {
        new updateAsyncTask(mWritingMemoDao).execute(id, newTitle, newContent);
    }

    /* UI-thread에서 할 경우 앱의 오류 없애기 */
    private static class insertAsyncTask extends AsyncTask<writingMemo, Void, Void> {
        private writingMemoDao mAsyncTaskDao;

        insertAsyncTask(writingMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final writingMemo... writingMemos) {
            mAsyncTaskDao.insertWritingMemos(writingMemos[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<writingMemo, Void, Void> {
        private writingMemoDao mAsyncTaskDao;

        deleteAsyncTask(writingMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final writingMemo... writingMemos) {
            mAsyncTaskDao.deleteWritingMemos(writingMemos[0]);
            return null;
        }
    }

    private static class deleteSpecificRowAsyncTask extends AsyncTask<Integer, Void, Void> {
        private writingMemoDao mAsyncTaskDao;

        deleteSpecificRowAsyncTask(writingMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer ...id) {
            mAsyncTaskDao.deleteSpecificWritingMemo(id[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Object, Void, Void> {
        private writingMemoDao mAsyncTaskDao;

        updateAsyncTask(writingMemoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Object... params) {
            int id = (Integer) params[0];
            String title = (String) params[1];
            String content = (String) params[2];

            mAsyncTaskDao.updateWritingMemo(id, title, content);
            return null;
        }
    }
}
