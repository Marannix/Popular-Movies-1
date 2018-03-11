package com.example.tobi.popular_movies_1;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_MENU_POPULAR_MOVIES = "menuPopularMovies";
    private static final String BUNDLE_KEY_MENU_TOP_RATED_MOVIES = "menuTopRatedMovies";
    private static final String BUNDLE_KEY_LAYOUT_MANAGER_STATE = "layoutManagerState";

    private RecyclerView recyclerView;
    private Context context;
    private GridAdapter adapter;
    //private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager layoutManager;
    private ApiModule apiModule;
    private MovieView movieView;

    private boolean shouldShowPopularMovies = true;
    private boolean shouldShowTopRatedMovies;
    private Parcelable layoutManagerInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        adapter = new GridAdapter();
        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        movieView = new MovieView(adapter);

        if (savedInstanceState != null) {
            layoutManagerInstanceState = savedInstanceState.getParcelable(BUNDLE_KEY_LAYOUT_MANAGER_STATE);
            shouldShowPopularMovies = savedInstanceState.getBoolean(BUNDLE_KEY_MENU_POPULAR_MOVIES);
            shouldShowTopRatedMovies = savedInstanceState.getBoolean(BUNDLE_KEY_MENU_TOP_RATED_MOVIES);
        }

        if (shouldShowPopularMovies) {
            retrievePopularMovies();
        } else {
            retrieveTopRatedMovies();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popularMovies) {
            if (item.isChecked()) {
                return true;
            }
            item.setChecked(!item.isChecked());
            retrievePopularMovies();
            shouldShowPopularMovies = true;
            shouldShowTopRatedMovies = false;
            return true;
        } else if (item.getItemId() == R.id.topRated) {
            if (item.isChecked()) {
                return true;
            }
            item.setChecked(!item.isChecked());
            retrieveTopRatedMovies();
            shouldShowPopularMovies = false;
            shouldShowTopRatedMovies = true;
            return true;
        }
            return super.onOptionsItemSelected(item);
    }

    private void retrievePopularMovies() {
        Call<MovieResponse> popularMovies = apiModule.movieApi().getPopularMovies();


        popularMovies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call,
                                   Response<MovieResponse> response) {
                MovieResponse movieResults = response.body();
                movieView.setMovieData(movieResults.getResults(), context);
                if (layoutManagerInstanceState != null) {
                    layoutManager.onRestoreInstanceState(layoutManagerInstanceState);
                    layoutManagerInstanceState = null;
                }
                for (int i = 0; i < movieResults.getResults().size(); i++) {
                    Log.d("URL", "onResponse: " + movieResults.getResults().get(i).getPosterPath());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("failed", "failed");
                Log.d("failed", t.getMessage());
            }
        });
    }

    private void retrieveTopRatedMovies() {
        Call<MovieResponse> topRated = apiModule.movieApi().getTopRatedMovies();
        topRated.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResults = response.body();
                movieView.setMovieData(movieResults.getResults(), context);
                for (int i = 0; i < movieResults.getResults().size(); i++) {
                    Log.d("URL", "onResponse: " + movieResults.getResults().get(i).getPosterPath());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void init() {
        apiModule = new ApiModule();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_KEY_LAYOUT_MANAGER_STATE, layoutManager.onSaveInstanceState());
        outState.putBoolean(BUNDLE_KEY_MENU_POPULAR_MOVIES, shouldShowPopularMovies);
        outState.putBoolean(BUNDLE_KEY_MENU_TOP_RATED_MOVIES, shouldShowTopRatedMovies);
    }
}
