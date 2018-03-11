package com.example.tobi.popular_movies_1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tobi on 25-Feb-18.
 */

public class Movie implements Parcelable {

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
  
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.voteCount);
    dest.writeInt(this.id);
    dest.writeString(this.title);
    dest.writeFloat(this.popularity);
    dest.writeString(this.posterPath);
  }

  protected Movie(Parcel in) {
    this.voteCount = in.readInt();
    this.id = in.readInt();
    this.title = in.readString();
    this.popularity = in.readFloat();
    this.posterPath = in.readString();
  }

  public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
    @Override
    public Movie createFromParcel(Parcel source) {
      return new Movie(source);
    }

    @Override
    public Movie[] newArray(int size) {
      return new Movie[size];
    }
  };
}
