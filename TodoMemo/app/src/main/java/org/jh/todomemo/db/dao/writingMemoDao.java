package org.jh.todomemo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.jh.todomemo.db.entity.writingMemo;

@Dao
public interface writingMemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWritingMemos(writingMemo... writingMemos);

    @Update
    public void updateWritingMemos(writingMemo... writingMemos);

    @Delete
    public void deleteWritingMemos(writingMemo... writingMemos);

    @Query("SELECT :position FROM writingMemo")
    public writingMemo[] loadWritingMemo(int position);
}
