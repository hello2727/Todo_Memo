package org.jh.todomemo.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.jh.todomemo.Model.entity.pictureMemo;

import java.util.List;

@Dao
public interface pictureMemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPictureMemos(pictureMemo pictureMemos);

    @Update
    void updatePictureMemos(pictureMemo pictureMemos);

    @Delete
    void deletePictureMemos(pictureMemo pictureMemos);

    //get all data query
    @Query("SELECT * FROM pictureMemo_table")
    LiveData<List<pictureMemo>> getAllPictureMemos();

    //delete specific query
    @Query("DELETE FROM pictureMemo_table WHERE ID IS :id")
    void deleteSpecificPictureMemo(int id);
}
