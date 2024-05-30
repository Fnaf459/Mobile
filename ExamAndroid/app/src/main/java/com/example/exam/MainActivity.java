package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements CarAddFragment.OnCarAddedListener,
                    CarUpdateFragment.OnCarUpdatedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, CarFragment.newInstance(1))
                    .commit();
        }

        Button buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CarAddFragment.newInstance()).commit();
        });

        Button buttonReport = findViewById(R.id.buttonReport);
        buttonReport.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onCarAdded() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, CarFragment.newInstance(1))
                .commit();
    }

    @Override
    public void onCarUpdated(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, CarFragment.newInstance(1))
                .commit();
    }
}
