package org.jh.todomemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MWriting extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_writing, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewWriting);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        WritingConstructureAdapter adapter = new WritingConstructureAdapter();

        adapter.addItem(new WritingConstructure("첫글", "안녕하세요"));

        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
