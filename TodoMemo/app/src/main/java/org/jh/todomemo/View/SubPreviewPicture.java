package org.jh.todomemo.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.jh.todomemo.R;
import org.jh.todomemo.ViewModel.pictureMemoViewModel;
import org.jh.todomemo.util.MySharedPreferences;

public class SubPreviewPicture extends AppCompatActivity {
    ImageView spp_iv_pictureContent;
    pictureMemoViewModel mpictureMemoViewModel;

    MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_preview_picture);

        spp_iv_pictureContent = findViewById(R.id.spp_iv_pictureContent);
        //Model Provider
        mpictureMemoViewModel = ViewModelProviders.of(this).get(pictureMemoViewModel.class);

        mySharedPreferences = new MySharedPreferences();

        //메모 상세내용 표시하기
        byte[] bitImg = mySharedPreferences.getPictureContent(this);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitImg, 0, bitImg.length);
        Glide.with(this)
             .load(bitmap)
             .into(spp_iv_pictureContent);
    }
}