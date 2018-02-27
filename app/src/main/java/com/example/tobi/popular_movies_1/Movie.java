package com.example.tobi.popular_movies_1;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tobi on 25-Feb-18.
 */

public class Movie {

  @SerializedName("vote_count") private int voteCount;
  private int id;
  private String title;
  private float popularity;
  @SerializedName("poster_path") private String posterPath;

  public Movie(int voteCount, int id, String title, float popularity, String posterPath) {
    this.voteCount = voteCount;
    this.id = id;
    this.title = title;
    this.popularity = popularity;
    this.posterPath = posterPath;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public float getPopularity() {
    return popularity;
  }

  public String getPosterPath() {
    return posterPath;
  }
}
