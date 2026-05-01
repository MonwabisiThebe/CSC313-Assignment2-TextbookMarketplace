package com.peter.textbookmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button btnBrowse = findViewById(R.id.btnBrowse);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSearch = findViewById(R.id.btnSearch);

        btnBrowse.setOnClickListener(v ->
                startActivity(new Intent(this, ViewBooksActivity.class)));

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddBookActivity.class)));

        btnSearch.setOnClickListener(v ->
                startActivity(new Intent(this, SearchActivity.class)));
    }
}
