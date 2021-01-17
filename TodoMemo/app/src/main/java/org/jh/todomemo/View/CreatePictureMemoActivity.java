package org.jh.todomemo.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.jh.todomemo.R;

public class CreatePictureMemoActivity extends AppCompatActivity {
    Toolbar cpm_toolbar;
    ImageView iv_captured;
    Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);

        cpm_toolbar = findViewById(R.id.cpm_toolbar);
        iv_captured = findViewById(R.id.cpm_iv_captured);

        setSupportActionBar(cpm_toolbar);

        //카메라에서 찍은 사진
        Intent intent = getIntent();
        bitmap = (Bitmap)intent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);

        iv_captured.setImageBitmap(rotate(bitmap, 0));
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
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