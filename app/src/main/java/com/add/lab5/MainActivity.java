package com.add.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
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

        GridLayout gridLayout = findViewById(R.id.table_layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        gridLayout.removeAllViews();

        int n = 1;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView im = (ImageView)inflater.inflate(R.layout.cell_item, gridLayout, false);

                String st = "bib" + n;
                im.setImageResource(
                    getResources().getIdentifier(st, "drawable", getPackageName())
                );
                im.setTag(st);
                if (++n > 20) n = 1;

                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("res", (String)v.getTag());
                        startActivity(intent);
                    }
                });

                GridLayout.LayoutParams params = (GridLayout.LayoutParams) im.getLayoutParams();
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                im.setLayoutParams(params);

                gridLayout.addView(im);
            }
        }
    }
}