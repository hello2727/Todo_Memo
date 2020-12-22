package org.jh.todomemo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.jh.todomemo.db.entity.writingMemo;

import java.util.List;

@Dao
public interface writingMemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWritingMemos(writingMemo... writingMemos);

    @Update
    void updateWritingMemos(writingMemo... writingMemos);

    @Delete
    void deleteWritingMemos(writingMemo... writingMemos);

    @Query("SELECT * FROM writingMemo_table")
    List<writingMemo> getAllMemo();

    @Query("SELECT * FROM writingMemo_table WHERE writing_Title = :wTitle AND writing_content = :content")
    writingMemo[] loadWritingMemo(String wTitle, String content);
}
