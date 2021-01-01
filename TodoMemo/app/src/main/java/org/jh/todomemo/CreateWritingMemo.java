package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

import org.jh.todomemo.db.database.writingMemoDatabase;

public class CreateWritingMemo extends AppCompatActivity {
    public static final String EXTRA_REPLY1 = "wTitle";
    public static final String EXTRA_REPLY2 = "wContent";
    EditText cwm_previewWTitle, cwm_previewWContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_writing_memo);

        cwm_previewWTitle = findViewById(R.id.cwm_insertTitle);
        cwm_previewWContents = findViewById(R.id.cwm_insertContents);

//        /* 글메모 데이터베이스 인스턴스 가져오기 */
//        writingMemoDatabase db = Room.databaseBuilder(getApplicationContext(),
//                writingMemoDatabase.class, "writingMemoDB").build();
    }

    @Override
    public void onBackPressed() {
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(cwm_previewWTitle.getText()) && TextUtils.isEmpty(cwm_previewWContents.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        }else{
            String wTitle = cwm_previewWTitle.getText().toString();
            String wContent = cwm_previewWContents.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY1, wTitle);
            replyIntent.putExtra(EXTRA_REPLY2, wContent);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    /* 글메모 DB에 메모 저장 */
    public void saveDB(writingMemoDatabase db, Editable title, Editable content){
//        db.writingMemoDao().insertWritingMemos();
    }
}