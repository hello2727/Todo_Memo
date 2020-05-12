package org.jh.todomemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WritingConstructureAdapter extends RecyclerView.Adapter<WritingConstructureAdapter.ViewHolder> {
    ArrayList<WritingConstructure> items = new ArrayList<WritingConstructure>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.mwriting_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        WritingConstructure item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(WritingConstructure item) {
        items.add(item);
    }

    public void setItems(ArrayList<WritingConstructure> items) {
        this.items = items;
    }

    public WritingConstructure getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, WritingConstructure item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView);
            contents = itemView.findViewById(R.id.textView2);
        }

        public void setItem(WritingConstructure item) {
            title.setText(item.getTitle());
            contents.setText(item.getContents());
        }
    }
}
