package com.rovin.pokharel.myshow.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rovin.pokharel.myshow.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rovin on 8/7/2018.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<Movie> movieList;

    public ShowAdapter(Context mContext, ArrayList<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowitem_nowshowing, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.name.setText(movie.getMovieName());
        holder.description.setText(movie.getMovieDescription());
        Picasso.with(mContext).load(movie.getMovieImage()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name, description, date;
        private ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = (TextView)itemView.findViewById(R.id.nowshowing_movie_name);
            description = (TextView)itemView.findViewById(R.id.nowshowing_movie_desc);
            thumbnail = (ImageView)itemView.findViewById(R.id.nowshowing_movie_image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
