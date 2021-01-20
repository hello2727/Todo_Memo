package org.jh.todomemo.Model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.jh.todomemo.Model.dao.writingMemoDao;
import org.jh.todomemo.Model.entity.writingMemo;

@Database(entities = {writingMemo.class}, version = 1, exportSchema = false)
public abstract class writingMemoDatabase extends RoomDatabase {
    public abstract writingMemoDao writingMemoDao();

    //singleton pattern, room database는 한개만 존재
    private static writingMemoDatabase INSTANCE;

    public static writingMemoDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            //동시에 2개의 INSTANCE가 생성되는 것을 막기위한 synchronized
            synchronized (writingMemoDatabase.class) {
                if(INSTANCE == null){
                    //데이터베이스 생성부분
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            writingMemoDatabase.class, "writingMemo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
