package org.jh.todomemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

/* 사진메모 저장 및 불러오기(사진 데이터가 큰 관계로 SharedPreferences 사용) */

public class MySharedPreferences {
    final String KIND = "memo";

    public void setPictureContent(Context context, byte[] content){
        SharedPreferences spf = context.getSharedPreferences(KIND, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        String s = Base64.encodeToString(content, Base64.DEFAULT);
        editor.putString("pictureContent", s);
        editor.commit();
    }

    public byte[] getPictureContent(Context context) {
        SharedPreferences spf = context.getSharedPreferences(KIND, Context.MODE_PRIVATE);
        byte[] content = Base64.decode(spf.getString("pictureContent", ""), Base64.DEFAULT);
        return content;
    }

    public void clearMemoData(Context context) {
        SharedPreferences spf = context.getSharedPreferences(KIND, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.clear();
        editor.commit();
    }
}
