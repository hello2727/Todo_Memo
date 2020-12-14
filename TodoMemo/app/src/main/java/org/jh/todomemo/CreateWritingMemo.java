package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CreateWritingMemo extends AppCompatActivity {
    TextView cwm_previewWTitle, cwm_previewWContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_writing_memo);

        cwm_previewWTitle = findViewById(R.id.cwm_previewWTitle);
        cwm_previewWContents = findViewById(R.id.cwm_previewWContents);

    }
}