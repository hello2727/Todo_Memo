package org.jh.todomemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jh.todomemo.db.entity.writingMemo;
import org.jh.todomemo.R;

import java.util.List;

public class WritingConstructureAdapter extends RecyclerView.Adapter<WritingConstructureAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            contents = itemView.findViewById(R.id.textView2);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//
//                    if (listener != null) {
//                        listener.onItemClick(ViewHolder.this, view, position);
//                    }
//                }
//            });
        }

//        public void setItem(WritingConstructure item) {
//            title.setText(item.getTitle());
//            contents.setText(item.getContents());
//        }
    }

//    ArrayList<WritingConstructure> items = new ArrayList<WritingConstructure>();
//    OnWritingItemClickListener listener;
    private final LayoutInflater mInflater;
    private List<writingMemo> mWritingMemos;

    public WritingConstructureAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = mInflater.inflate(R.layout.mwriting_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mWritingMemos != null){
            writingMemo current = mWritingMemos.get(position);
            holder.title.setText(current.getWritingTitle());
            holder.contents.setText(current.getWritingContent());
        }else{
            holder.title.setText("Empty");
            holder.contents.setText("Empty");
        }
//        WritingConstructure item = items.get(position);
//        viewHolder.setItem(item);
    }

    public void setWritingMemos(List<writingMemo> writingMemos) {
        mWritingMemos = writingMemos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWritingMemos != null){
            return mWritingMemos.size();
        }else{
            return 0;
        }
//        return items.size();
    }
//
//    public void addItem(WritingConstructure item) {
//        items.add(item);
//    }
//
//    public void setItems(ArrayList<WritingConstructure> items) {
//        this.items = items;
//    }
//
//    public WritingConstructure getItem(int position) {
//        return items.get(position);
//    }
//
//    public void setItem(int position, WritingConstructure item) {
//        items.set(position, item);
//    }
//
//    public void setOnItemClickListener(OnWritingItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    @Override
//    public void onItemClick(ViewHolder holder, View view, int position) {
//        if (listener != null) {
//            listener.onItemClick(holder, view, position);
//        }
//    }
}
