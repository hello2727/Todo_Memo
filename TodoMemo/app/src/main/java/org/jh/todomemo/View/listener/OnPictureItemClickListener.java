package org.jh.todomemo.View.listener;

import android.view.View;

import org.jh.todomemo.View.adapter.PictureConstructureAdapter;

public interface OnPictureItemClickListener {
    public void onItemClick(PictureConstructureAdapter.ViewHolder holder, View view, int position);
}
