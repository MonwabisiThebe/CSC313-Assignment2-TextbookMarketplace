package com.peter.textbookmarketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.textbookmarketplace.model.Textbook;
import com.peter.textbookmarketplace.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private BookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        repository = BookRepository.getInstance();

        EditText etSearch = findViewById(R.id.etSearch);
        Button btnSearch = findViewById(R.id.btnDoSearch);
        TextView tvResults = findViewById(R.id.tvResults);

        btnSearch.setOnClickListener(v -> {

            String query = etSearch.getText().toString().trim();

            // VALIDATION
            if (query.isEmpty()) {
                Toast.makeText(this,
                        "Enter title or seller name",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // SEARCH BY TITLE
            List<Textbook> results =
                    repository.searchByTitle(query);

            // SEARCH BY SELLER
            List<Textbook> sellerResults =
                    repository.searchBySeller(query);

            // COMBINE RESULTS
            results.addAll(sellerResults);

            // REMOVE DUPLICATES
            List<Textbook> uniqueResults = new ArrayList<>();

            for (Textbook b : results) {

                if (!uniqueResults.contains(b)) {
                    uniqueResults.add(b);
                }
            }

            // NO RESULTS
            if (uniqueResults.isEmpty()) {

                tvResults.setText(
                        "No textbooks found for:\n" + query
                );

                return;
            }

            // DISPLAY RESULTS
            StringBuilder builder = new StringBuilder();

            builder.append(" SEARCH RESULTS\n\n");

            for (Textbook b : uniqueResults) {

                builder.append("Title: ")
                        .append(b.getTitle())
                        .append("\n");

                builder.append("Seller: ")
                        .append(b.getSellerName())
                        .append("\n");

                builder.append("Copies: ")
                        .append(b.getCopies())
                        .append("\n");

                builder.append("Price: ZAR ")
                        .append(b.getPrice())
                        .append("\n");

                // NULL PROTECTION
                if (b.getBankingInfo() != null
                        && !b.getBankingInfo().isEmpty()) {

                    builder.append("Payment: ")
                            .append(b.getBankingInfo())
                            .append("\n");

                } else {

                    builder.append("Payment: Not provided\n");
                }

                builder.append(
                        "\n-----------------------------\n\n"
                );
            }

            tvResults.setText(builder.toString());
        });
    }
}