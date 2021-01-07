package org.jh.todomemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.jh.todomemo.R;

public class SubPreviewWriting extends AppCompatActivity {
    TextView previewWTitle, previewWContents;
    EditText et_previewWTitle, et_previewWContents;
    View sbw_line;
    InputMethodManager imm; //키보드

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

        //메모 상세내용 표시하기
        Intent intent = getIntent();
        final String wtitle = intent.getExtras().getString("wtitle");
        String wcontents = intent.getExtras().getString("wcontents");
        previewWTitle.setText(wtitle);
        previewWContents.setText(wcontents);

        //제목을 클릭할 경우 메모의 제목 수정하기 기능이 나타난다.
        previewWTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewWTitle.setVisibility(View.GONE);
                sbw_line.setVisibility(View.GONE);

                et_previewWTitle.setVisibility(View.VISIBLE); //화면에 나타내고
                et_previewWTitle.setText(wtitle); //값 설정하고
                et_previewWTitle.requestFocus(); //포커스 주고
                //키보드 올라오게 하기
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });
        previewWContents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //키보드 사라지게 하기
        imm.hideSoftInputFromWindow(previewWTitle.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
