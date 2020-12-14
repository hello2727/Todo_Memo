package org.jh.todomemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jh.todomemo.data.WritingConstructure;

import java.util.ArrayList;

public class WritingConstructureAdapter extends RecyclerView.Adapter<WritingConstructureAdapter.ViewHolder> implements OnWitingItemClickListener {
    ArrayList<WritingConstructure> items = new ArrayList<WritingConstructure>();

    OnWitingItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.mwriting_item, viewGroup, false);

        return new ViewHolder(itemView, this);
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

    public void setOnItemClickListener(OnWitingItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        public ViewHolder(View itemView, final OnWitingItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.textView);
            contents = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(WritingConstructure item) {
            title.setText(item.getTitle());
            contents.setText(item.getContents());
        }
    }
}
