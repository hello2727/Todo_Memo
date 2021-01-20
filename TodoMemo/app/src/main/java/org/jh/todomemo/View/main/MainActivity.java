package org.jh.todomemo.View.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import org.jh.todomemo.R;
import org.jh.todomemo.View.CreatePictureMemoActivity;
import org.jh.todomemo.View.CreateWritingMemo;
import org.jh.todomemo.View.main.list.MPicture;
import org.jh.todomemo.View.main.list.MWriting;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAPTURE = 0;
    Uri photoURI;
    Uri capturedUri;

    Toolbar toolbar;

    Fragment fragment1;
    Fragment fragment2;

    FloatingActionButton fab1, fab2, fab3;
    Animation fab_open, fab_close, fab_clockwise, fab_anticlockwise;
    Boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new MWriting();
        fragment2 = new MPicture();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.writing));
        tabs.getTabAt(0).setText("글 메모");
        tabs.addTab(tabs.newTab().setIcon(R.drawable.picture));
        tabs.getTabAt(1).setText("사진 메모");

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if(position == 0) {
                    selected = fragment1;
                }else if(position == 1) {
                    selected = fragment2;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        fab1 = findViewById(R.id.floatingActionButton);
        fab2 = findViewById(R.id.floatingActionButton2);
        fab3 = findViewById(R.id.floatingActionButton3);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fab_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabOpen){
                    fab3.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);
                    fab1.startAnimation(fab_anticlockwise);
                    fab3.setClickable(false);
                    fab2.setClickable(false);
                    isFabOpen = false;
                }else{
                    fab3.startAnimation(fab_open);
                    fab2.startAnimation(fab_open);
                    fab1.startAnimation(fab_clockwise);
                    fab3.setClickable(true);
                    fab2.setClickable(true);
                    isFabOpen = true;
                }
            }
        });
        //사진찍기 위해 카메라모드로 이동
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(capture.resolveActivity(getPackageManager()) != null){
                    photoURI = createImageUri(newFileName(), "image/jpg");
                    Log.d(" 주소", photoURI.toString());
                    capture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(capture, REQUEST_CAPTURE);
                }
            }
        });
        //글메모 생성 액티비티로 이동
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createWritingMemo = new Intent(MainActivity.this, CreateWritingMemo.class);
                startActivity(createWritingMemo);
            }
        });


        //카메라 권한체크
        checkPermission();
    }

    void checkPermission(){
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(cameraPermission == PackageManager.PERMISSION_GRANTED) {
            //카메라 실행 로직
        }else{
            String[] permissions = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, permissions, 321);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 321){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //카메라 실행 로직
            }else{
                finish();
            }
        }
    }

    //이미지 Uri 생성
    private Uri createImageUri(String filename, String mimeType) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType);

        return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }
    // 새파일 이름 생성
    private String newFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String filename = sdf.format(System.currentTimeMillis());

        return filename + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CAPTURE){
                capturedUri = photoURI;

                //사진uri를 사진메모 생성 액티비티로 넘기기
                Intent createPictureMemo = new Intent(this, CreatePictureMemoActivity.class);
                createPictureMemo.putExtra("uri", capturedUri);
                startActivity(createPictureMemo);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isFabOpen){
            fab3.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.startAnimation(fab_anticlockwise);
            fab3.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        }
    }
}
