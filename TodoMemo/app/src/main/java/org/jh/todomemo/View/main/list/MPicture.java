package org.jh.todomemo.View.main.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.R;
import org.jh.todomemo.View.adapter.PictureMemoRecyclerViewAdapter;
import org.jh.todomemo.View.listener.OnPictureItemClickListener;
import org.jh.todomemo.View.SubPreviewPicture;
import org.jh.todomemo.ViewModel.pictureMemoViewModel;
import org.jh.todomemo.util.MySharedPreferences;
import java.util.List;

public class MPicture extends Fragment {
    RecyclerView recyclerView;
    PictureMemoRecyclerViewAdapter adapter;
    pictureMemoViewModel mpictureMemoViewModel;

    MySharedPreferences mySharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_picture, container, false);

        //RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewPicture);
        adapter = new PictureMemoRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        //Model Provider
        mpictureMemoViewModel = ViewModelProviders.of(this).get(pictureMemoViewModel.class);

        //observe : model의 LiveData를 관찰한다.
        mpictureMemoViewModel.getAllPictureMemos().observe(this, new Observer<List<pictureMemo>>() {
            @Override
            public void onChanged(List<pictureMemo> pictureMemos) {
                // Update the cached copy of the words in the adapter.
                adapter.setPictureMemos(pictureMemos);
            }
        });

        mySharedPreferences = new MySharedPreferences();

        adapter.setOnItemClickListener(new OnPictureItemClickListener() {
            //각 메모를 클릭했을때 메모의 상세보기 액티비티가 나온다.
            @Override
            public void onItemClick(PictureMemoRecyclerViewAdapter.ViewHolder holder, View view, int position) {
                pictureMemo pMemo = adapter.getItem(position);

                //사진메모 저장하기
                mySharedPreferences.setPictureContent(getContext(), pMemo.getPictureContent());

                Intent intent = new Intent(getContext(), SubPreviewPicture.class);
                startActivity(intent);
            }

            //각 메모를 롱클릭을 하면 메모의 삭제여부를 묻는 다이얼로그 메시지가 뜬다.
            @Override
            public void onItemLongClick(PictureMemoRecyclerViewAdapter.ViewHolder holder, View view, final int position) {
                AlertDialog.Builder IsDelete = new AlertDialog.Builder(getContext());
                IsDelete.setTitle("메모 삭제여부")
                        .setMessage("메모를 삭제할까요?")
                        // "예" 버튼을 누르면 실행되는 리스너
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pictureMemo pMemo = adapter.getItem(position);

                                mpictureMemoViewModel.delete_picture(pMemo); //특정 글메모 삭제
                            }
                        })
                        // "아니오" 버튼을 누르면 실행되는 리스너
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return; //아무것도 하지 않는다.
                            }
                        });
                IsDelete.show();
            }
        });

        return rootView;
    }
}
