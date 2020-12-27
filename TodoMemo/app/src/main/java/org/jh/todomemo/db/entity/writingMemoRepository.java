/*여러 데이터에 대한 접근을 할 수 있는 class.
 주로 로컬 데이터(앱 내장데이터)를 가져올지 네트워크에서 데이터를 가져올지를 다룸.*/
package org.jh.todomemo.db.entity;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.jh.todomemo.db.dao.writingMemoDao;
import org.jh.todomemo.db.database.writingMemoDatabase;

import java.util.List;

public class writingMemoRepository {
    //Dao의 멤버변수와 word를 넣을 list변수를 만들어줌
    private writingMemoDao mWritingMemoDao;
    private LiveData<List<writingMemo>> mAllWritingMemo;

    public writingMemoRepository(Application application) {
        writingMemoDatabase db = writingMemoDatabase.getDatabase(application);
        //RoomDatabase에 있는 Dao를 가져온다.
        mWritingMemoDao = db.writingMemoDao();
        //Dao의 쿼리를 이용하여 저장되어있는 모든 word를 가져온다.
        mAllWritingMemo = mWritingMemoDao.getAllWritingMemos();
    }

    public LiveData<List<writingMemo>> getAllWritingMemos() {
        return mAllWritingMemo;
    }

    //word를 추가하는 함수
    public void insert(writingMemo writingMemo) {
        new insertAsyncTask(mWritingMemoDao).execute(writingMemo);
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
}
