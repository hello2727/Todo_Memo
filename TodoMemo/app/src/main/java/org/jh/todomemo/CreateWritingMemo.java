package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.EditText;

import org.jh.todomemo.db.database.writingMemoDatabase;

public class CreateWritingMemo extends AppCompatActivity {
    EditText cwm_previewWTitle, cwm_previewWContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_writing_memo);

        cwm_previewWTitle = findViewById(R.id.cwm_insertTitle);
        cwm_previewWContents = findViewById(R.id.cwm_insertContents);

        /* 글메모 데이터베이스 인스턴스 가져오기 */
        writingMemoDatabase db = Room.databaseBuilder(getApplicationContext(),
                writingMemoDatabase.class, "writingMemoDB").build();
    }

    public void saveDB(){

    }
}