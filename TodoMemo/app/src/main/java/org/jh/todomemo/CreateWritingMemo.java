package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CreateWritingMemo extends AppCompatActivity {
    EditText cwm_previewWTitle, cwm_previewWContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_writing_memo);

        cwm_previewWTitle = findViewById(R.id.cwm_insertTitle);
        cwm_previewWContents = findViewById(R.id.cwm_insertContents);

    }
}