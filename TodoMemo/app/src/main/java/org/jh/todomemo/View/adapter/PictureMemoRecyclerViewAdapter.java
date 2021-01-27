package org.jh.todomemo.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jh.todomemo.Model.entity.pictureMemo;
import org.jh.todomemo.View.listener.OnPictureItemClickListener;
import org.jh.todomemo.R;
import org.jh.todomemo.View.listener.OnWritingItemClickListener;

import java.util.List;

public class PictureMemoRecyclerViewAdapter extends RecyclerView.Adapter<PictureMemoRecyclerViewAdapter.ViewHolder> implements OnPictureItemClickListener {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pictureContent;

        public ViewHolder(View itemView){
            super(itemView);
            pictureContent = itemView.findViewById(R.id.pi_iv_pictureContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemLongClick(ViewHolder.this, view, position);
                    }
                    return true;
                }
            });
        }

        public void setItem(pictureMemo pMemo) {
//            pictureContent.setImageBitmap(pMemo.getPictureContent()); //방법1

            //blob -> bitmap으로 변환함수 필요
            Glide.with(itemView) //방법2
                 .load(pMemo.getPictureContent())
                 .into(pictureContent);
        }
    }

    private final LayoutInflater mInflater;
    private List<pictureMemo> mPictureMemos;
    static OnPictureItemClickListener listener;

    public PictureMemoRecyclerViewAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mpicture_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mPictureMemos != null){
            pictureMemo current = mPictureMemos.get(position);
//            holder.pictureContent.setImageBitmap(current.getPictureContent()); //방법1

//            Glide.with(itemView) //방법2
//                 .load(current.getPictureContent())
//                 .into(holder.pictureContent);
        }else{
            holder.pictureContent.setImageResource(R.drawable.pleasepicture);
        }

        pictureMemo pMemo = mPictureMemos.get(position);
        holder.setItem(pMemo);
    }

    public void setPictureMemos(List<pictureMemo> pictureMemos){
        mPictureMemos = pictureMemos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mPictureMemos != null){
            return mPictureMemos.size();
        }else {
            return 0;
        }
    }

    public pictureMemo getItem(int position){
        return mPictureMemos.get(position);
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

    @Override
    public void onItemLongClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemLongClick(holder, view, position);
        }
    }
}
