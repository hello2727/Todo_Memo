package org.jh.todomemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jh.todomemo.ViewModel.writingMemoViewModel;
import org.jh.todomemo.adapter.WritingConstructureAdapter;
import org.jh.todomemo.data.WritingConstructure;
import org.jh.todomemo.db.entity.writingMemo;
import org.jh.todomemo.listener.OnWritingItemClickListener;

import java.util.List;

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

//        adapter = new WritingConstructureAdapter();
//
//        adapter.addItem(new WritingConstructure("첫글", "안녕하세요, 여러분들 만나뵈서 반갑습니다. 이거 줄 1개만 있는거 맞죠?? 확인차 여쭤봅니다."));
//
//        recyclerView.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnWritingItemClickListener() {
//            @Override
//            public void onItemClick(WritingConstructureAdapter.ViewHolder holder, View view, int position) {
//                WritingConstructure item = (WritingConstructure) adapter.getItem(position);
//                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getContext(), SubPreviewWriting.class);
//                intent.putExtra("wtitle", item.getTitle());
//                intent.putExtra("wcontents", item.getContents());
//                startActivity(intent);
//            }
//        });

        return rootView;
    }
}
