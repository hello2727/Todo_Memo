package org.jh.todomemo.View.main.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jh.todomemo.R;
import org.jh.todomemo.View.adapter.PictureMemoRecyclerViewAdapter;
import org.jh.todomemo.data.PictureConstructure;
import org.jh.todomemo.View.listener.OnPictureItemClickListener;

public class MPicture extends Fragment {
    RecyclerView recyclerView;
    PictureMemoRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_m_picture, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewPicture);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PictureMemoRecyclerViewAdapter();

        adapter.addItem(new PictureConstructure(R.drawable.pleasepicture, "첫글", "안녕하세요"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPictureItemClickListener() {
            @Override
            public void onItemClick(PictureMemoRecyclerViewAdapter.ViewHolder holder, View view, int position) {
                PictureConstructure item = (PictureConstructure) adapter.getItem(position);
                Toast.makeText(getContext(), item.getPtitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
