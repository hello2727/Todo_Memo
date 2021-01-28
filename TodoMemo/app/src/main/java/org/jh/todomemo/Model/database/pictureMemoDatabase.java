package org.jh.todomemo.Model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.jh.todomemo.Model.dao.pictureMemoDao;
import org.jh.todomemo.Model.entity.pictureMemo;

@Database(entities = {pictureMemo.class}, version = 2, exportSchema = false)
public abstract class pictureMemoDatabase extends RoomDatabase {
    public abstract pictureMemoDao pictureMemoDao();

    //singleton pattern, room database는 한개만 존재
    private static pictureMemoDatabase INSTANCE;

    public static pictureMemoDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            //동시에 2개의 INSTANCE가 생성되는 것을 막기위한 synchronized
            synchronized (pictureMemoDatabase.class){
                if(INSTANCE == null){
                    //데이터베이스 생성부분
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            pictureMemoDatabase.class, "pictureMemo_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
