package org.jh.todomemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PictureConstructureAdapter extends RecyclerView.Adapter<PictureConstructureAdapter.ViewHolder> {
    ArrayList<PictureConstructure> items = new ArrayList<PictureConstructure>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.mpicture_item, viewGroup, false);

        return new ViewHolder(itemView);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView ptitle;
        TextView pcontents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.imageView);
            ptitle = itemView.findViewById(R.id.ptextView);
            pcontents = itemView.findViewById(R.id.ptextView2);

        }

        public void setItem(PictureConstructure item) {
            picture.setImageResource(item.getPicture());
            ptitle.setText(item.getPtitle());
            pcontents.setText(item.getPcontents());
        }
    }
}
