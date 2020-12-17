package org.jh.todomemo.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class writingMemo {
    @PrimaryKey(autoGenerate = true)
    public int index;

    @ColumnInfo(name = "writing_Title")
    public String writingTitle;

    @ColumnInfo(name = "writing_content")
    public String writingContent;
}
