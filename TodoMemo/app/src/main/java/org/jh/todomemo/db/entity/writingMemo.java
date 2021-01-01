package org.jh.todomemo.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writingMemo_table")
public class writingMemo {
    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name = "writing_Title")
    public String writingTitle;

    @ColumnInfo(name = "writing_content")
    public String writingContent;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public writingMemo(String writingTitle, String writingContent){
        this.writingTitle = writingTitle;
        this.writingContent = writingContent;
    }

    public String getWritingTitle() {
        return writingTitle;
    }

    public void setWritingTitle(String writingTitle) {
        this.writingTitle = writingTitle;
    }

    public String getWritingContent() {
        return writingContent;
    }

    public void setWritingContent(String writingContent) {
        this.writingContent = writingContent;
    }
}
