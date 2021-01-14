package org.jh.todomemo.View.listener;

import android.view.View;

import org.jh.todomemo.View.adapter.WritingMemoRecyclerViewAdapter;

public interface OnWritingItemClickListener {
    void onItemClick(WritingMemoRecyclerViewAdapter.ViewHolder holder, View view, int position);
    void onItemLongClick(WritingMemoRecyclerViewAdapter.ViewHolder holder, View view, int position);
}
