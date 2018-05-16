package com.catchy.denzicam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.BasicViewHolder> {

    private ArrayList<Result> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemDelete(int position);
    }

    public static class BasicViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        TextView result;
        public TextView date;
        ImageView delete;

        BasicViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.cardViewTitle);
            result = itemView.findViewById(R.id.cardViewResult);
            date = itemView.findViewById(R.id.cardViewDate);
            delete = itemView.findViewById(R.id.cardViewDelete);

            delete.setOnClickListener(v -> {
                if ( listener != null){
                    int position = getAdapterPosition();
                    if( position != RecyclerView.NO_POSITION){
                        listener.onItemDelete(position);
                    }
                }
            });
        }
    }

    BasicAdapter(ArrayList<Result> l) {
        list = l;
    }

    public void setOnItemClickListener(OnItemClickListener l){
        listener = l;
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_cardview, parent, false);
        return new BasicViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {
        Result current = list.get(position);

        holder.title.setText(current.getName());
        holder.result.setText(current.getStringResult());
        holder.date.setText(current.getStringDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
