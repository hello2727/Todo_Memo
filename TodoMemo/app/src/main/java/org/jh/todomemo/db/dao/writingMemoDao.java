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

    //delete all query
    @Delete
    void reset(List<writingMemo> writingMemos);

    //update query
    @Query("UPDATE writingMemo_table SET writing_Title = :title AND writing_content = :content WHERE ID = :order")
    void updateWritingMemo(int order, String title, String content);

    //get all data query
    @Query("SELECT * FROM writingMemo_table")
    List<writingMemo> getAllMemo();

    //load query
    @Query("SELECT * FROM writingMemo_table WHERE writing_Title = :wTitle AND writing_content = :content")
    writingMemo[] loadWritingMemo(String wTitle, String content);
}
