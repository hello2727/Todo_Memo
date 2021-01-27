package org.jh.todomemo.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.Repository.model.pictureMemoRepository;

import java.util.List;

/* UI에 데이터를 제공하고 변경된 사항들을 업데이트 */
public class pictureMemoViewModel extends AndroidViewModel {
    //Repository 클래스에서 필요한 부분을 ViewModel에 연결.
    //- memo리스트 불러오기, memo insert하기 부분
    private pictureMemoRepository mpictureMemoReposiory;

    private LiveData<List<pictureMemo>> mAllPictureMemos;

    public pictureMemoViewModel(Application application) {
        super(application);
        mpictureMemoReposiory = new pictureMemoRepository(application);
        mAllPictureMemos = mpictureMemoReposiory.getAllPictureMemos();
    }

    public LiveData<List<pictureMemo>> getmAllPictureMemos() {
        return mAllPictureMemos;
    }

    public void insert_picture(pictureMemo pictureMemo) {
        mpictureMemoReposiory.insert_picture(pictureMemo);
    }

    public void delete_picture(pictureMemo pictureMemo) {
        mpictureMemoReposiory.delete_picture(pictureMemo);
    }

    public void deleteSpecificPictureMemo(int id) {
        mpictureMemoReposiory.deleteSpecificPictureMemo(id);
    }
}
