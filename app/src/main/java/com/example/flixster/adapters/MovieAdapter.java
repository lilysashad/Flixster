package com.example.flixster.adapters;

import android.content.res.Configuration;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.DetailActivity;
import com.example.flixster.models.Movie;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import org.parceler.Parcels;
import com.bumptech.glide.Glide;
import android.util.Log;
import com.bumptech.glide.request.target.Target;
import android.widget.RelativeLayout;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.example.flixster.R;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

        Context context;
        List<Movie> movies;

        public MovieAdapter(Context context, List<Movie> movies){

            this.context = context;
            this.movies = movies;

        }

        //Usually involves inflating layout from XML and returning holder
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("MovieAdapter", "onCreateViewHolder");
            View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            return new ViewHolder(movieView);
        }

        //Involves populating data into item through holder
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Log.d("MovieAdapter", "onBindViewHolder" + position);
            //Get movie at passed in position
            Movie movie = movies.get(position);
            //Bind movie data into VH
            holder.bind(movie);
        }

        //Returns total count of items in list
        @Override
        public int getItemCount() {
            return movies.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

            public void bind(Movie movie) {

                tvTitle.setText(movie.getTitle());
                tvOverview.setText(movie.getOverview());
                String imageUrl;

                //if in landscape mode, imageUrl = back drop image
                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                    imageUrl = movie.getBackdropPath();

                }
                //else imageUrl = poster image

                else{

                    imageUrl= movie.getPosterPath();

                }

                Glide.with(context).load(imageUrl).override(1000, Target.SIZE_ORIGINAL).placeholder(R.mipmap.placeholder).into(ivPoster);

                // 1. Register click listener on whole row
                container.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v){

                        // 2. Navigate to new activity on tap
                        Intent i = new Intent(context, DetailActivity.class);
                        i.putExtra("movie", Parcels.wrap(movie));
                        context.startActivity(i);

                    }

                });

            }

        }

}
