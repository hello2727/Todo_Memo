package org.jh.todomemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jh.todomemo.data.PictureConstructure;

import java.util.ArrayList;

public class PictureConstructureAdapter extends RecyclerView.Adapter<PictureConstructureAdapter.ViewHolder> implements OnPictureItemClickListener {
    ArrayList<PictureConstructure> items = new ArrayList<PictureConstructure>();

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
        PictureConstructure item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PictureConstructure item) {
        items.add(item);
    }

    public void setItems(ArrayList<PictureConstructure> items) {
        this.items = items;
    }

    public PictureConstructure getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, PictureConstructure item) {
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

    static class ViewHolder extends RecyclerView.ViewHolder {
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

        public void setItem(PictureConstructure item) {
            picture.setImageResource(item.getPicture());
            ptitle.setText(item.getPtitle());
            pcontents.setText(item.getPcontents());
        }
    }
}
