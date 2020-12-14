package org.jh.todomemo;

import android.view.View;

import org.jh.todomemo.PictureConstructureAdapter;

public interface OnPictureItemClickListener {
    public void onItemClick(PictureConstructureAdapter.ViewHolder holder, View view, int position);
}
