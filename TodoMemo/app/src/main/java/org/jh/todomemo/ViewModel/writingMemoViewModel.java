package org.jh.todomemo.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jh.todomemo.db.entity.writingMemo;
import org.jh.todomemo.Repository.writingMemoRepository;

import java.util.List;

/* UI에 데이터를 제공하고 변경된 사항들을 업데이트 */
public class writingMemoViewModel extends AndroidViewModel {
    //Repository 클래스에서 필요한 부분을 ViewModel에 연결.
    //- word리스트 불러오기, word insert하기 부분
    private writingMemoRepository mwritingMemoRepository;

    private LiveData<List<writingMemo>> mAllWritingMemos;

    public writingMemoViewModel(Application application) {
        super(application);
        mwritingMemoRepository = new writingMemoRepository(application);
        mAllWritingMemos = mwritingMemoRepository.getAllWritingMemos();
    }

    public LiveData<List<writingMemo>> getAllWritingMemos() {
        return mAllWritingMemos;
    }

    public void insert(writingMemo writingMemo) {
        mwritingMemoRepository.insert(writingMemo);
    }

    public void delete(writingMemo writingMemo) {
        mwritingMemoRepository.delete(writingMemo);
    }

    public void update(int id, String newTitle, String newContent) {
        mwritingMemoRepository.update(id, newTitle, newContent);
    }
}
