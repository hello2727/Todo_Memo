package org.jh.todomemo.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.jh.todomemo.Model.entity.writingMemo;

import java.util.List;

@Dao
public interface writingMemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWritingMemos(writingMemo writingMemos);

    @Update
    void updateWritingMemos(writingMemo writingMemos);

    @Delete
    void deleteWritingMemos(writingMemo writingMemos);

    //delete all query
    @Query("DELETE FROM writingMemo_table")
    void deleteAllWritingMemos();

    //delete specific query
    @Query("DELETE FROM writingMemo_table WHERE ID IS :id")
    void deleteSpecificWritingMemo(int id);

    //update query
    @Query("UPDATE writingMemo_table SET writing_Title = :newTitle, writing_content = :newContent WHERE ID IS :id")
    void updateWritingMemo(int id, String newTitle, String newContent);

    //get all data query
    @Query("SELECT * FROM writingMemo_table")
    LiveData<List<writingMemo>> getAllWritingMemos();

    //load query
    @Query("SELECT * FROM writingMemo_table WHERE writing_Title = :wTitle AND writing_content = :content")
    writingMemo[] loadWritingMemo(String wTitle, String content);
}
