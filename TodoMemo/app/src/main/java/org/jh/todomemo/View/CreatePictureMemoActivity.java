package org.jh.todomemo.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.azeesoft.lib.colorpicker.ColorPickerDialog;
import com.bumptech.glide.Glide;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.R;
import org.jh.todomemo.ViewModel.pictureMemoViewModel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class CreatePictureMemoActivity extends AppCompatActivity {
    Toolbar cpm_toolbar;
    ImageView iv_captured;
    Uri captured_uri;

    //글씨 넣는데 필요한 요소
    PencilView pencilView;
    LinearLayout container;
    ConstraintLayout pictureMemo;

    pictureMemoViewModel mpictureMemoViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_picture_memo);

        //카메라에서 찍은 사진uri
        Intent intent = getIntent();
        captured_uri = intent.getParcelableExtra("uri");

        //글씨 쓸 수 있는 뷰 추가
        container = findViewById(R.id.cpm_container);
        pencilView = new PencilView(CreatePictureMemoActivity.this);
        container.addView(pencilView);

        cpm_toolbar = findViewById(R.id.cpm_toolbar);
        iv_captured = findViewById(R.id.cpm_iv_captured);
        pictureMemo = findViewById(R.id.cpm_pictureMemo);

        //Model Provider
        mpictureMemoViewModel = ViewModelProviders.of(this).get(pictureMemoViewModel.class);

        setSupportActionBar(cpm_toolbar);

        //찍은 사진을 이미지뷰에 삽입하기
        Glide.with(this)
                .load(captured_uri)
                .into(iv_captured);

        //사진저장 권한체크
        checkPermission();
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
                      /* 사진메모가 데이터베이스에 저장되는 기능
                      * 1. 사진메모를 데이터베이스에 저장
                      * 2. 사진메모 생성 액티비티 종료 */
                      savePictureMemo();
                      finish();
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

    //폴더생성 + 사진저장 권한체크
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    // 새파일 이름 생성
    private String newFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String filename = sdf.format(System.currentTimeMillis());

        return filename + ".png";
    }

    //사진메모를 데이터베이스에 저장하기
    private void savePictureMemo(){
        pictureMemo.buildDrawingCache();
        Bitmap bitmap = pictureMemo.getDrawingCache();

        //데이터베이스에 저장하기
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        byte[] imageToByte = stream.toByteArray();

        pictureMemo mpictureMemo = new pictureMemo(imageToByte);
        mpictureMemoViewModel.insert_picture(mpictureMemo);

//        //스마트폰 갤러리에 저장하기
//        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        String savePath = path + "/todoMemo";
//        String filename = "memo" + "_" + newFileName();
//        File file = new File(savePath);
//
//        if(!file.isDirectory()){
//            file.mkdirs();
//        }

//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(savePath + "/" + filename);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}