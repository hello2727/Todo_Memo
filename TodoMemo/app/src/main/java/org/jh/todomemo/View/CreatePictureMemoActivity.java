package org.jh.todomemo.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.jh.todomemo.R;

public class CreatePictureMemoActivity extends AppCompatActivity {
    Toolbar cpm_toolbar;
    ImageView iv_captured;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);

        cpm_toolbar = findViewById(R.id.cpm_toolbar);
        iv_captured = findViewById(R.id.cpm_iv_captured);

        setSupportActionBar(cpm_toolbar);
    }

    //툴바에 옵션메뉴 추가하기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.select_color_menu, menu);
        return true;
    }
    //툴바의 옵션메뉴에 클릭이벤트 추가하기
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.scm_pickingColor:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}