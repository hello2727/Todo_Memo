package org.jh.todomemo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import org.jh.todomemo.R;

public class CreatePictureMemoActivity extends AppCompatActivity {
    ImageView iv_captured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);

        iv_captured = findViewById(R.id.iv_captured);
    }
}