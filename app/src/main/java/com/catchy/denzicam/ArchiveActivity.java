package com.catchy.denzicam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArchiveActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private BasicAdapter adapter;
    private Button btnEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        btnEmpty = findViewById(R.id.btnArchiveEmpty);

        btnEmpty.setVisibility(View.INVISIBLE);
        btnEmpty.setOnClickListener(view -> finish());

        loadResults();
    }

    private void loadResults() {

        results = new ArrayList<>();

        String raw = IO.load(this);
        List<String> data = Arrays.asList(raw.split("\\s*;\\s*"));


        if(!raw.equals("") && data.size() > 0){
            for(String r : data){
                Result toAdd = new Result(r);
                results.add(toAdd);
            }
            buildView();
        }
        else{
            btnEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void buildView(){

        RecyclerView view = findViewById(R.id.rvArchive);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new BasicAdapter(results);

        view.setLayoutManager(manager);
        view.setAdapter(adapter);

        adapter.setOnItemClickListener(this::removeItem);

    }

    public void removeItem(int position){
        results.remove(position);
        adapter.notifyItemRemoved(position);

        StringBuilder fresh = new StringBuilder();

        if( results.size() < 1) {
            btnEmpty.setVisibility(View.VISIBLE);
            IO.save(this,"");
        }else {
            for (Result r : results) {
                fresh.append(r.toString());
            }
            IO.save(this, fresh.toString());
        }
    }
}
