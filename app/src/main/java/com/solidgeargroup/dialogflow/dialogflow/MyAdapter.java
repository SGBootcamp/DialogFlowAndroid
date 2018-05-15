package com.solidgeargroup.dialogflow.dialogflow;

import android.content.Context;
import android.os.Debug;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by siro on 15/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Film> mFilms;
    private OnRecyclerViewItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;
        public TextView descTextView;
        public ImageView imageImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            descTextView = (TextView) itemView.findViewById(R.id.description);
            imageImageView = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemListener.onItemClick(null, view, getAdapterPosition());
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Film> films, OnRecyclerViewItemListener itemListener) {
        mFilms = films;
        mItemListener = itemListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View filmView = inflater.inflate(R.layout.film_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(filmView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Film film = mFilms.get(position);

        holder.titleTextView.setText(film.getTitle());
        holder.descTextView.setText(film.getDescription());
        holder.imageImageView.setImageResource(film.getImage());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFilms.size();
    }
}