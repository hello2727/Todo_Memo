package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jh.todomemo.R;
import org.jh.todomemo.ViewModel.writingMemoViewModel;
import org.jh.todomemo.adapter.WritingConstructureAdapter;
import org.jh.todomemo.db.entity.writingMemo;

import java.util.ArrayList;
import java.util.List;

public class SubPreviewWriting extends AppCompatActivity {
    TextView previewWTitle, previewWContents;
    EditText et_previewWTitle, et_previewWContents;
    View sbw_line;
    InputMethodManager imm; //키보드
    writingMemoViewModel mwritingMemoViewModel;

    static int position;
    String fixedTitle, fixedContent;
    String wtitle, wcontents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_preview_writing);

        previewWTitle = findViewById(R.id.previewWTitle);
        previewWContents = findViewById(R.id.previewWContents);
        et_previewWTitle = findViewById(R.id.et_previewWTitle);
        et_previewWContents = findViewById(R.id.et_previewWContents);
        sbw_line = findViewById(R.id.sbw_line);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //Model Provider
        mwritingMemoViewModel = ViewModelProviders.of(this).get(writingMemoViewModel.class);

        //메모 상세내용 표시하기
        Intent intent = getIntent();
        wtitle = intent.getExtras().getString("wtitle");
        wcontents = intent.getExtras().getString("wcontents");
        position = intent.getExtras().getInt("position");
        previewWTitle.setText(wtitle);
        previewWContents.setText(wcontents);

        //제목을 클릭할 경우 메모의 제목 수정하기 기능이 나타난다.
        previewWTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewWTitle.setVisibility(View.GONE);
                sbw_line.setVisibility(View.GONE);

                if(et_previewWContents.requestFocus()){
                    imm.hideSoftInputFromWindow(et_previewWContents.getWindowToken(), 0);
                    et_previewWContents.clearFocus();
                }

                et_previewWTitle.setVisibility(View.VISIBLE); //화면에 나타내고
                et_previewWTitle.setText(wtitle); //값 설정하고
                et_previewWTitle.requestFocus(); //포커스 주고
                //키보드 올라오게 하기
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                fixedTitle = et_previewWTitle.getText().toString();
            }
        });
        //내용을 클릭할 경우 메모의 내용 수정하기 기능이 나타난다.
        previewWContents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_previewWTitle.requestFocus()){
                    imm.hideSoftInputFromWindow(et_previewWTitle.getWindowToken(), 0); //기존 키보드 내리고
                    et_previewWTitle.clearFocus();
                }

                previewWContents.setVisibility(View.GONE); //내용 텍스트뷰는 안보이게 하고
                et_previewWContents.setVisibility(View.VISIBLE); //내용 에디트텍스트는 화면에 나타내고
                et_previewWContents.setText(wcontents); //값 설정하고
                et_previewWContents.requestFocus(); //포커스 주고
                //키보드 올라오게 하기
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                fixedContent = et_previewWContents.getText().toString();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //키보드 사라지게 하기
        if(et_previewWTitle.requestFocus()){
            imm.hideSoftInputFromWindow(et_previewWTitle.getWindowToken(), 0);
        }else if(et_previewWContents.requestFocus()){
            imm.hideSoftInputFromWindow(et_previewWContents.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mwritingMemoViewModel.update2(wtitle, wcontents, fixedTitle, fixedContent); //동작이 이상함(데이터베이스 문법 확인 및 에디트텍스트와 텍스트뷰 고치기 필요)
        Toast.makeText(this, wtitle+","+wcontents+","+fixedTitle+","+fixedContent, Toast.LENGTH_SHORT).show();
    }
}
