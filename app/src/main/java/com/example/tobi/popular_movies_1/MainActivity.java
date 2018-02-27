package com.example.tobi.popular_movies_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;
    private GridAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ApiModule apiModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);

        // 2 is the number of grid layout columns
        layoutManager = new GridLayoutManager(context, 2);

        // Do i need the context?
        adapter = new GridAdapter(context);

        recyclerView.setAdapter(adapter);

        init();

        Call <MovieResponse> call = apiModule.movieApi().getPopularMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override public void onResponse(Call<MovieResponse> call,
                Response<MovieResponse> response) {
                MovieResponse movieResults = response.body();

                for (int i = 0; i < 5; i++) {
                    Log.d("name", movieResults.getResults().get(i).getTitle());
                }

            }

            @Override public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("failed", "failed");
                Log.d("failed", t.getMessage());
            }
        });
    }

    private void init() {
        apiModule = new ApiModule();
    }


}
