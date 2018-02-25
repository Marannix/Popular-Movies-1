package com.example.tobi.popular_movies_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;
    private GridAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
    }
}
