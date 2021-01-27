package org.jh.todomemo.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.View.listener.OnPictureItemClickListener;
import org.jh.todomemo.R;

import java.util.ArrayList;

public class PictureConstructureAdapter extends RecyclerView.Adapter<PictureConstructureAdapter.ViewHolder> implements OnPictureItemClickListener {
    ArrayList<pictureMemo> items = new ArrayList<pictureMemo>();

    OnPictureItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.mpicture_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        pictureMemo item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(pictureMemo item) {
        items.add(item);
    }

    public void setItems(ArrayList<pictureMemo> items) {
        this.items = items;
    }

    public pictureMemo getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, pictureMemo item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnPictureItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView ptitle;
        TextView pcontents;

        public ViewHolder(View itemView, final OnPictureItemClickListener listener) {
            super(itemView);

            picture = itemView.findViewById(R.id.imageView);
            ptitle = itemView.findViewById(R.id.ptextView);
            pcontents = itemView.findViewById(R.id.ptextView2);

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

        public void setItem(pictureMemo item) {
            picture.setImageResource(item.getPictureContent());
        }
    }
}
