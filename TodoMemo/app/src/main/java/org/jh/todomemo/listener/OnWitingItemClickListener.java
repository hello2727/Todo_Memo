package org.jh.todomemo.listener;

import android.view.View;

import org.jh.todomemo.adapter.WritingConstructureAdapter;

public interface OnWitingItemClickListener {
    public void onItemClick(WritingConstructureAdapter.ViewHolder holder, View view, int position);
}