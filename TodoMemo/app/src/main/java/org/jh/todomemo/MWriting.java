package org.jh.todomemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            @Override
            public void onItemClick(WritingConstructureAdapter.ViewHolder holder, View view, int position) {
                writingMemo wMemo = (writingMemo) adapter.getItem(position);

                Intent intent = new Intent(getContext(), SubPreviewWriting.class);
                intent.putExtra("wtitle", wMemo.getWritingTitle());
                intent.putExtra("wcontents", wMemo.getWritingContent());
                startActivity(intent);
            }
        });
        return rootView;
    }
}
