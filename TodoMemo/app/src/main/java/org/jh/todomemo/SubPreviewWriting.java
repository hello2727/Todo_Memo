package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubPreviewWriting extends AppCompatActivity {
    TextView previewWTitle, previewWContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_preview_writing);

        previewWTitle = findViewById(R.id.previewWTitle);
        previewWContents = findViewById(R.id.previewWContents);

        Intent intent = getIntent();
        String wtitle = intent.getExtras().getString("wtitle");
        String wcontents = intent.getExtras().getString("wcontents");

        previewWTitle.setText(wtitle);
        previewWContents.setText(wcontents);
    }
}
