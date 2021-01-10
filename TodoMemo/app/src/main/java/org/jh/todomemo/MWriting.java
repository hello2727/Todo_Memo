package org.jh.todomemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jh.todomemo.ViewModel.writingMemoViewModel;
import org.jh.todomemo.adapter.WritingConstructureAdapter;
import org.jh.todomemo.db.entity.writingMemo;
import org.jh.todomemo.listener.OnWritingItemClickListener;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MWriting extends Fragment {
    RecyclerView recyclerView;
    WritingConstructureAdapter adapter;
    writingMemoViewModel mwritingMemoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_writing, container, false);

        //RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewWriting);
        adapter = new WritingConstructureAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Model Provider
        mwritingMemoViewModel = ViewModelProviders.of(this).get(writingMemoViewModel.class);

        //observe : model의 LiveData를 관찰한다.
        mwritingMemoViewModel.getAllWritingMemos().observe(this, new Observer<List<writingMemo>>() {
            @Override
            public void onChanged(@Nullable final List<writingMemo> writingMemos) {
                // Update the cached copy of the words in the adapter.
                adapter.setWritingMemos(writingMemos);
            }
        });

        adapter.setOnItemClickListener(new OnWritingItemClickListener() {
            //각 메모를 클릭했을때 메모의 상세보기 액티비티가 나온다.
            @Override
            public void onItemClick(WritingConstructureAdapter.ViewHolder holder, View view, int position) {
                writingMemo wMemo = adapter.getItem(position);

                Intent intent = new Intent(getContext(), SubPreviewWriting.class);
                intent.putExtra("wtitle", wMemo.getWritingTitle());
                intent.putExtra("wcontents", wMemo.getWritingContent());
                intent.putExtra("ID", wMemo.getID());
                startActivity(intent);
            }

            //각 메모를 롱클릭을 하면 메모의 삭제여부를 묻는 다이얼로그 메시지가 뜬다.
            @Override
            public void onItemLongClick(WritingConstructureAdapter.ViewHolder holder, View view, final int position) {
                AlertDialog.Builder IsDelete = new AlertDialog.Builder(getContext());
                IsDelete.setTitle("메모 삭제여부")
                        .setMessage("메모를 삭제할까요?")
                        // "예" 버튼을 누르면 실행되는 리스너
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                writingMemo wMemo = adapter.getItem(position);

                                mwritingMemoViewModel.delete(wMemo); //특정 글메모 삭제
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
