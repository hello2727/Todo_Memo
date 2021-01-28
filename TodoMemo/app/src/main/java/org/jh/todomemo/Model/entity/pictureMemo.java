package org.jh.todomemo.Model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "pictureMemo_table")
public class pictureMemo {
    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name = "picture_content")
//    public Blob pictureContent;
    public int pictureContent;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

//    public Blob getPictureContent() {
//        return pictureContent;
//    }
//
//    public void setPictureContent(Blob pictureContent) {
//        this.pictureContent = pictureContent;
//    }

    public int getPictureContent() {
        return pictureContent;
    }

    public void setPictureContent(int pictureContent) {
        this.pictureContent = pictureContent;
    }
}
