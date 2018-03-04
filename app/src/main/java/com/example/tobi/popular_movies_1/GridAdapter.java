package com.example.tobi.popular_movies_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Tobi on 25-Feb-18.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
  private Context context;
  private List<Movie> movies;
  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String phoneSize = "w500";

  public void setMovieData(List<Movie> movies, Context context) {
    this.movies = movies;
    this.context = context;
    this.notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final Movie movie = movies.get(position);
    String path = movieUrl + phoneSize + movie.getPosterPath();
    //https://developers.themoviedb.org/3/configuration/get-api-configuration
    // example -> https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg

    // image not being passed, sucks to be me
    Picasso.with(context).load(path).into(holder.imageView);
    Log.d("URL", "onBindViewHolder: " + path);
  }

  @Override public int getItemCount() {
    return movies != null ? movies.size() : 0;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView) ImageView imageView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
