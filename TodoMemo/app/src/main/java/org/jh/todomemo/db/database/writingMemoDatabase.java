package org.jh.todomemo.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.jh.todomemo.db.dao.writingMemoDao;
import org.jh.todomemo.db.entity.writingMemo;

@Database(entities = {writingMemo.class}, version = 1)
public abstract class writingMemoDatabase extends RoomDatabase {
    public abstract writingMemoDao writingMemoDao();
}
