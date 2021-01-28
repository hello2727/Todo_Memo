package org.jh.todomemo.View.main.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.R;
import org.jh.todomemo.View.adapter.PictureMemoRecyclerViewAdapter;
import org.jh.todomemo.ViewModel.pictureMemoViewModel;
import java.util.List;

public class MPicture extends Fragment {
    RecyclerView recyclerView;
    PictureMemoRecyclerViewAdapter adapter;
    pictureMemoViewModel mpictureMemoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_picture, container, false);

        //RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewPicture);
        adapter = new PictureMemoRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
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

        return rootView;
    }
}
