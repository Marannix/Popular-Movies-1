package com.example.tobi.popular_movies_1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tobi on 25-Feb-18.
 */

public interface MovieApi {

  String apiKey = "";

  String authentication = "api_key=" + apiKey;
  // popular movies
  @GET("movie/popular?" + authentication) Call<MovieResponse> getPopularMovies();

  // top rated movies -> this is most likely the wrong response
  //@GET("movie/top_rated" + apiKey) Call<List<MovieResponse>> topRatedMovies();

}
