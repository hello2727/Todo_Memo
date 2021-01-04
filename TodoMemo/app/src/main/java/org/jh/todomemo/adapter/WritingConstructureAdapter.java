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
import org.jh.todomemo.listener.OnWritingItemClickListener;
import java.util.List;

public class WritingConstructureAdapter extends RecyclerView.Adapter<WritingConstructureAdapter.ViewHolder> implements OnWritingItemClickListener {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        public ViewHolder(View itemView) {
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

        public void setItem(writingMemo wMemo) {
            title.setText(wMemo.getWritingTitle());
            contents.setText(wMemo.getWritingContent());
        }
    }

    private final LayoutInflater mInflater;
    private List<writingMemo> mWritingMemos;
    static OnWritingItemClickListener listener;

    public WritingConstructureAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        writingMemo wMemo = mWritingMemos.get(position);
        holder.setItem(wMemo);
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
    }

    public writingMemo getItem(int position) {
        return mWritingMemos.get(position);
    }

    public void setOnItemClickListener(OnWritingItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
}
