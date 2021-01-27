package org.jh.todomemo.View.listener;

import android.view.View;

import org.jh.todomemo.View.adapter.PictureMemoRecyclerViewAdapter;

public interface OnPictureItemClickListener {
    void onItemClick(PictureMemoRecyclerViewAdapter.ViewHolder holder, View view, int position);
    void onItemLongClick(PictureMemoRecyclerViewAdapter.ViewHolder holder, View view, int position);
}
