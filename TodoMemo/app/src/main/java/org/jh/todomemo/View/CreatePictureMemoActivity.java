package org.jh.todomemo.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.azeesoft.lib.colorpicker.ColorPickerDialog;
import com.bumptech.glide.Glide;

import org.jh.todomemo.R;

public class CreatePictureMemoActivity extends AppCompatActivity {
    Toolbar cpm_toolbar;
    ImageView iv_captured;
    Uri captured_uri;

    public static Context pictureContext;
    PencilView pencilView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);
        pencilView = new PencilView(this);
        addContentView(pencilView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        pictureContext = this;
        cpm_toolbar = findViewById(R.id.cpm_toolbar);
        iv_captured = findViewById(R.id.cpm_iv_captured);

        setSupportActionBar(cpm_toolbar);

        //카메라에서 찍은 사진uri
        Intent intent = getIntent();
        captured_uri = intent.getParcelableExtra("uri");

        //찍은 사진을 이미지뷰에 삽입하기
        Glide.with(this)
                .load(captured_uri)
                .into(iv_captured);
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
                //팔레트 등장
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.createColorPickerDialog(this);
                colorPickerDialog.setOnColorPickedListener(new ColorPickerDialog.OnColorPickedListener() {
                    @Override
                    public void onColorPicked(int color, String hexVal) {
                        //색상 선택했을 때
                        Toast.makeText(CreatePictureMemoActivity.this, hexVal+Integer.toString(color), Toast.LENGTH_LONG).show();
                    }
                });
                colorPickerDialog.show();
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder IsSave = new AlertDialog.Builder(this);
        IsSave.setTitle("그림메모 저장")
              .setMessage("메모를 저장하시겠습니까?")
              .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      //그림메모가 데이터베이스에 저장되는 기능
                      return;
                  }
              })
              .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      //그림메모를 저장하지 않고 액티비티 벗어나기
                      finish();
                  }
              });
        IsSave.show();
    }
}