package org.jh.todomemo.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.azeesoft.lib.colorpicker.ColorPickerDialog;
import com.bumptech.glide.Glide;

import org.jh.todomemo.R;

public class CreatePictureMemoActivity extends AppCompatActivity {
    Toolbar cpm_toolbar;
    ImageView iv_captured;
    Uri captured_uri;

    //글씨 넣는데 필요한 요소
    PencilView pencilView;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);

        //글씨 쓸 수 있는 뷰 추가
        container = findViewById(R.id.cpm_container);
        pencilView = new PencilView(CreatePictureMemoActivity.this);
        container.addView(pencilView);

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
                //지우개 기능 Off
                pencilView.IsEraserOn = false;
                //팔레트 등장
                ColorPickerDialog colorPickerDialog = ColorPickerDialog.createColorPickerDialog(this);
                colorPickerDialog.setOnColorPickedListener(new ColorPickerDialog.OnColorPickedListener() {
                    @Override
                    public void onColorPicked(int color, String hexVal) {
                        pencilView.setColor(color);
                    }
                });
                colorPickerDialog.show();
                
                return true;
            case R.id.scm_eraser:
                //지우개 기능 On
                pencilView.IsEraserOn = true;
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