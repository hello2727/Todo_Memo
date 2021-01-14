package org.jh.todomemo.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import org.jh.todomemo.R;
import org.jh.todomemo.ViewModel.writingMemoViewModel;
import org.jh.todomemo.Model.entity.writingMemo;

public class CreateWritingMemo extends AppCompatActivity {
    EditText cwm_previewWTitle, cwm_previewWContents;

    writingMemoViewModel mwritingMemoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_writing_memo);

        cwm_previewWTitle = findViewById(R.id.cwm_insertTitle);
        cwm_previewWContents = findViewById(R.id.cwm_insertContents);

        //Model Provider
        mwritingMemoViewModel = ViewModelProviders.of(this).get(writingMemoViewModel.class);
    }

    @Override
    public void onBackPressed() {
        if(TextUtils.isEmpty(cwm_previewWTitle.getText()) && TextUtils.isEmpty(cwm_previewWContents.getText())) {

        }else{
            String wTitle = cwm_previewWTitle.getText().toString();
            String wContent = cwm_previewWContents.getText().toString();
            if(TextUtils.isEmpty(cwm_previewWTitle.getText())){
                wTitle = null;
            }else if(TextUtils.isEmpty(cwm_previewWContents.getText())){
                wContent = null;
            }

            writingMemo mwritingMemo = new writingMemo(wTitle, wContent);
            mwritingMemoViewModel.insert(mwritingMemo);
        }
        finish();
    }
}