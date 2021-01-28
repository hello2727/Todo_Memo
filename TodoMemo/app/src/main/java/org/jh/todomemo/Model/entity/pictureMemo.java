package org.jh.todomemo.Model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pictureMemo_table")
public class pictureMemo {
    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name = "picture_content")
    public byte[] pictureContent;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public pictureMemo(byte[] pictureContent){
        this.pictureContent = pictureContent;
    }

    public byte[] getPictureContent() {
        return pictureContent;
    }

    public void setPictureContent(byte[] pictureContent) {
        this.pictureContent = pictureContent;
    }
}
