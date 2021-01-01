package org.jh.todomemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.jh.todomemo.ViewModel.writingMemoViewModel;
import org.jh.todomemo.adapter.WritingConstructureAdapter;
import org.jh.todomemo.db.entity.writingMemo;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final int NEW_MEMO_ACTIVITY_REQUEST_CODE = 1;

    Toolbar toolbar;

    Fragment fragment1;
    Fragment fragment2;

    FloatingActionButton fab1, fab2, fab3;
    Animation fab_open, fab_close, fab_clockwise, fab_anticlockwise;
    Boolean isFabOpen = false;

    writingMemoViewModel mwritingMemoViewModel;

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

        fab1 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab3 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
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
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createWritingMemo = new Intent(MainActivity.this, CreateWritingMemo.class);
                startActivityForResult(createWritingMemo, NEW_MEMO_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_MEMO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            writingMemo mwritingMemo = new writingMemo(data.getStringExtra(CreateWritingMemo.EXTRA_REPLY1), data.getStringExtra(CreateWritingMemo.EXTRA_REPLY2));
            mwritingMemoViewModel.insert(mwritingMemo); //에러나는 부분
        }
    }
}
